package Schach.controller

import java.awt.Color

import Schach.model.{ChessGameFieldBuilder, GameField}
import Schach.util.{Caretaker, Observable, Originator, UndoManager}

class Controller() extends Observable with Originator{
  val builder = new ChessGameFieldBuilder
  var gameField : GameField = builder.getNewGameField
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

  def movePiece(newPos: Vector[Int]): Unit = {
    undoManager.doStep(new MoveCommand(newPos(0), newPos(1), newPos(2), newPos(3), this))
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

}