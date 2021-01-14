package Schach.controller.controllerComponent

import java.awt.Color

import Schach.model.figureComponent.Figure
import Schach.model.gameFieldComponent.GameFieldInterface
import Schach.util.{Caretaker, Observable, Originator, UndoManager}

trait ControllerInterface extends Observable with Originator{
  val undoManager : UndoManager
  val caretaker : Caretaker
  var gameField : GameFieldInterface

  def createGameField(): Unit
  def controlInput(line: String): Boolean
  def gameFieldToString: String
  def getGameField: Vector[Figure]
  def movePiece(newPos: Vector[Int]): Unit
  def getGameStatus() : Int
  def moveIsValid(newPos: Vector[Int]): Boolean
  def setPlayer(color : Color): Color
  def getPlayer() : Color
  def changePlayer(): Unit
  def isChecked(): Boolean
  def isCheckmate(): Boolean
  def undo(): Unit
  def redo(): Unit
  def save(): Unit
  def restore(): Unit
  def caretakerIsCalled(): Boolean
  def saveGame(): Unit
  def loadGame(): Unit
}
