package Schach.util

import java.awt.Color

import Schach.model.Figure

trait Memento {
  def getFigures: Vector[Figure]
  def getPlayer: Color
}
