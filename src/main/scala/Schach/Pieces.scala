package Schach

case class Pieces(value: Int) {
  val pieces = List("pawn", "rook", "knight", "bishop", "queen", "king")
  def getPiece(): String ={
    pieces(value)
  }
}