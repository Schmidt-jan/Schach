package Schach.util


trait Originator {
  def save(): Unit
  def restore(): Unit
}
