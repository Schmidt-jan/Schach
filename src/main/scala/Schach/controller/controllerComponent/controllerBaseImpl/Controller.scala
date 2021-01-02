package Schach.controller.controllerComponent.controllerBaseImpl

import Schach.controller.controllerComponent._
import Schach.model.figureComponent.Figure
import Schach.model.gameFieldComponent.gameFieldBaseImpl.ChessGameFieldBuilder
import Schach.model.gameFieldComponent.{ChessGameFieldBuilderInterface, GameFieldInterface}
import Schach.util.{Caretaker, UndoManager}

import java.awt.Color

class Controller() extends ControllerInterface {
  val builder : ChessGameFieldBuilderInterface = new ChessGameFieldBuilder
  var gameField : GameFieldInterface = builder.getNewGameField
  val undoManager = new UndoManager
  val caretaker = new Caretaker


  def createGameField() : Unit = {
    gameField = builder.getNewGameField
    notifyObservers
  }

  def controlInput(line: String): Boolean = {
    line.matches("[A-H][1-8]")
  }

  def gameFieldToString: String = gameField.toString

  def getGameField: Vector[Figure] = gameField.getFigures

  def movePiece(newPos: Vector[Int]): Unit = {
    undoManager.doStep(new MoveCommand(newPos(0), newPos(1), newPos(2), newPos(3), this))
    changePlayer()
    notifyObservers
  }

  def moveIsValid(newPos: Vector[Int]): Boolean = {
    gameField.moveValid(newPos(0), newPos(1), newPos(2), newPos(3))
  }

  def setPlayer(color : Color): Color = {
    gameField.setPlayer(color)
  }

  def getPlayer() : Color = {
    gameField.getPlayer
  }

  def changePlayer(): Unit = {
    gameField.changePlayer()
  }
  def undo(): Unit = {
    //TODO when undo checked figure is not shown
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

}
