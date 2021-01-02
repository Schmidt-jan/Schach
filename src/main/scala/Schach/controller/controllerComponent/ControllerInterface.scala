package Schach.controller.controllerComponent

import Schach.model.figureComponent.Figure
import Schach.util.{Observable, Originator}

import java.awt.Color

trait ControllerInterface extends Observable with Originator{
  def createGameField(): Unit
  def controlInput(line: String): Boolean
  def gameFieldToString: String
  def getGameField: Vector[Figure]
  def movePiece(newPos: Vector[Int]): Unit
  def moveIsValid(newPos: Vector[Int]): Boolean
  def setPlayer(color : Color): Color
  def getPlayer() : Color
  def changePlayer(): Unit
  def undo(): Unit
  def redo(): Unit
  def save(): Unit
  def restore(): Unit
  def caretakerIsCalled(): Boolean
}
