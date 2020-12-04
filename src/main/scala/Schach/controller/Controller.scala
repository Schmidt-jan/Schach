package Schach.controller

import Schach.model.{ChessGameFieldBuilder, GameField}
import Schach.util.{Caretaker, Observable, Originator, UndoManager}

class Controller() extends Observable with Originator{
  var gameField : GameField = GameField.getInstance
  val undoManager = new UndoManager
  val caretaker = new Caretaker


  def createGameField() : Unit = {
    val builder = new ChessGameFieldBuilder
    gameField = builder.getNewGameField()
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

  def undo(): Unit = {
    undoManager.undoStep()
    notifyObservers
  }

  def redo(): Unit = {
    undoManager.redoStep()
    notifyObservers
  }

  def save(): Unit = {
    val memento = new GameFieldMemento(gameField.getFigures())
    caretaker.called = true
    caretaker.addMemento(memento)
  }

  def restore(): Unit = {
    gameField.clear()
    gameField.addFigures(caretaker.getMemento.getFigures)
    notifyObservers
  }

}