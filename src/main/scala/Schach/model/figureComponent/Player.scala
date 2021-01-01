package Schach.model.figureComponent

case class Player(name: String) {
  override def toString: String = name

  val isActive = false
  val isCheck = false
  val isCheckmate = false
}
