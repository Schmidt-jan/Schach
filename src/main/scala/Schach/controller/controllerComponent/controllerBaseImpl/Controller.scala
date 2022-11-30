package Schach.controller.controllerComponent.controllerBaseImpl

import java.awt.Color
import Schach.GameFieldModule
import Schach.controller.controllerComponent._
import Schach.model.figureComponent.{Bishop, Figure, Knight, Queen, Rook}
import Schach.model.fileIOComponent.FileIOInterface
import Schach.model.gameFieldComponent.GameFieldInterface
import Schach.util.{Caretaker, UndoManager}
import com.google.inject.name.Names
import com.google.inject.{Guice, Inject, Injector}
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector

class Controller @Inject() extends ControllerInterface {
  var injector: Injector = Guice.createInjector(new GameFieldModule)
  val undoManager = new UndoManager
  val caretaker = new Caretaker
  var gameField: GameFieldInterface = injector.instance[GameFieldInterface](Names.named("Chess"))
  val fileIo: FileIOInterface = injector.instance[FileIOInterface]


  def createGameField(): Unit = {
    injector = Guice.createInjector(new GameFieldModule)
    gameField = injector.instance[GameFieldInterface](Names.named("Chess"))
    notifyObservers
  }

  def controlInput(line: String): Boolean = {
    line.matches("[A-H][1-8]")
  }

  def gameFieldToString: String = gameField.toString

  def getGameField: Vector[Figure] = gameField.getFigures

  def movePiece(newPos: Vector[Int]): Unit = {
    if (moveIsValid(newPos)) {
      undoManager.doStep(new MoveCommand(newPos(0), newPos(1), newPos(2), newPos(3), this))
      changePlayer()
      checkStatus()

    }
    notifyObservers
  }

  def checkStatus(): Unit = {
    if (isChecked()) {
      gameField.setStatus(gameField.CHECKED)
      if (isCheckmate())
        gameField.setStatus(gameField.CHECKMATE)
    }

    if (gameField.pawnHasReachedEnd())
      gameField.setStatus(gameField.PAWN_REACHED_END)
  }

  def moveIsValid(newPos: Vector[Int]): Boolean = {
    val valid = gameField.moveValid(newPos(0), newPos(1), newPos(2), newPos(3))

    if (valid) gameField.setStatus(gameField.RUNNING)
    else gameField.setStatus(gameField.MOVE_ILLEGAL)

    valid
  }

  def getGameStatus() : Int = {
    gameField.getStatus()
  }

  def setPlayer(color: Color): Color = {
    gameField.setPlayer(color)
  }

  def getPlayer(): Color = {
    gameField.getPlayer
  }

  def changePlayer(): Unit = {
    gameField.changePlayer()
  }

  def convertPawn(figureType : String): Unit = {
    val result = gameField.getPawnAtEnd()

    result match {
      case Some(pawn) =>
        figureType match {
        case "queen" => gameField.convertFigure(pawn, Queen(pawn.x, pawn.y, pawn.color))
        case "rook" => gameField.convertFigure(pawn, Rook(pawn.x, pawn.y, pawn.color))
        case "knight" => gameField.convertFigure(pawn, Knight(pawn.x, pawn.y, pawn.color))
        case "bishop" => gameField.convertFigure(pawn, Bishop(pawn.x, pawn.y, pawn.color))
        case _ => gameField.setStatus(gameField.INVALID_CONVERSION)
      }
      case None => gameField.setStatus(gameField.INVALID_CONVERSION);
    }
    notifyObservers
  }

  def isChecked(): Boolean = {
    gameField.isChecked(getPlayer())
  }

  def isCheckmate(): Boolean = {
    gameField.isCheckmate(getPlayer())
  }

  def undo(): Unit = {
    undoManager.undoStep()
    notifyObservers
  }

  def redo(): Unit = {
    undoManager.redoStep()
    notifyObservers
  }

  def save(): Unit = {
    val memento = new GameFieldMemento(gameField.getFigures, gameField.getPlayer)
    caretaker.called = true
    caretaker.addMemento(memento)
  }

  def restore(): Unit = {
    gameField.clear()
    gameField.addFigures(caretaker.getMemento.getFigures)
    notifyObservers
  }

  def caretakerIsCalled(): Boolean = {
    caretaker.called
  }

  def saveGame(): Unit = {
    fileIo.saveGame(gameField)
  }

  def loadGame(): Unit = {
    gameField.clear()
    val (vec, col) = fileIo.loadGame
    gameField.addFigures(vec)
    gameField.setPlayer(col)
    notifyObservers
  }


}
