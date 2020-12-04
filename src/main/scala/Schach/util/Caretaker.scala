package Schach.util

class Caretaker {
  private var memStack: List[Memento]= Nil
  var called = false

  def addMemento(mem: Memento): Unit = {
    memStack = mem::memStack
  }

  def getMemento: Memento = {
    memStack.head
  }

}
