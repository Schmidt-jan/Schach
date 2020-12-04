package Schach.util

import Schach.model.GameField

trait Originator {
  def save(): Unit
  def restore(): Unit
}
