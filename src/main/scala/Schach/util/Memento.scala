package Schach.util

import Schach.model.Figure

trait Memento {
  def getFigures: Vector[Figure]
}
