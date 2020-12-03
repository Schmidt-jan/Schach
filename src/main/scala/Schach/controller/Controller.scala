package Schach.controller

import Schach.model.{ChessGameFieldBuilder, GameField}
import Schach.util.{Caretaker, Memento, Observable, Originator, UndoManager}

class Controller(var gameField: GameField) extends Observable with Originator{
  val undoManager = new UndoManager
  val caretaker = new Caretaker


  def createGameField: Unit = {
    val builder = new ChessGameFieldBuilder
    builder.makeGameField()
    gameField = builder.getGameField
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
    undoManager.undoStep
    notifyObservers
  }

  def redo(): Unit = {
    undoManager.redoStep
    notifyObservers
  }

  def save(): Unit = {
    val memento = new GameFieldMemento(gameField)
    caretaker.called = true
    caretaker.addMemento(memento)
  }

  def restore(): Unit = {
    gameField = caretaker.getMemento.getField
    notifyObservers
  }

}
