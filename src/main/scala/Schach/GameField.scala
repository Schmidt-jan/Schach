package Schach

import java.awt.Color


case class GameField() {
  var gameField: Array[Array[Option[Figure]]] = Array.ofDim(8, 8)

  def init() {
    val white = initWhite()
    gameField(0) = white(0)
    gameField(1) = white(1)

    for (x <- Range(2, 6))
      gameField(x) = Array(None, None, None, None, None, None, None, None)

    val black = initBlack()
    gameField(6) = black(0)
    gameField(7) = black(1)
  }

  def initWhite(): Array[Array[Option[Figure]]] = {
    Array(Array[Option[Figure]](
      Option(Rook(0, 0, Color.WHITE)),
      Option(Knight(1, 0, Color.WHITE)),
      Option(Bishop(2, 0, Color.WHITE)),
      Option(King(3, 0, Color.WHITE)),
      Option(Queen(4, 0, Color.WHITE)),
      Option(Bishop(5, 0, Color.WHITE)),
      Option(Knight(6, 0, Color.WHITE)),
      Option(Rook(7, 0, Color.WHITE))),

      Array[Option[Figure]](
        Option(Pawn(0, 1, Color.WHITE)),
        Option(Pawn(1, 1, Color.WHITE)),
        Option(Pawn(2, 1, Color.WHITE)),
        Option(Pawn(3, 1, Color.WHITE)),
        Option(Pawn(4, 1, Color.WHITE)),
        Option(Pawn(5, 1, Color.WHITE)),
        Option(Pawn(6, 1, Color.WHITE)),
        Option(Pawn(7, 1, Color.WHITE))))
  }

  def initBlack(): Array[Array[Option[Figure]]] = {
    Array(Array[Option[Figure]](
      Option(Pawn(0, 6, Color.BLACK)),
      Option(Pawn(1, 6, Color.BLACK)),
      Option(Pawn(2, 6, Color.BLACK)),
      Option(Pawn(3, 6, Color.BLACK)),
      Option(Pawn(4, 6, Color.BLACK)),
      Option(Pawn(5, 6, Color.BLACK)),
      Option(Pawn(6, 6, Color.BLACK)),
      Option(Pawn(7, 6, Color.BLACK))),

      Array[Option[Figure]](
        Option(Rook(0, 7, Color.BLACK)),
        Option(Knight(1, 7, Color.BLACK)),
        Option(Bishop(2, 7, Color.BLACK)),
        Option(King(3, 7, Color.BLACK)),
        Option(Queen(4, 7, Color.BLACK)),
        Option(Bishop(5, 7, Color.BLACK)),
        Option(Knight(6, 7, Color.BLACK)),
        Option(Rook(7, 7, Color.BLACK))))

  }

  def moveValid(): Boolean = true

  def moveTo(posXNow: Int, posYNow: Int, posXNext: Int, posYNext: Int) : Boolean = {
    if (moveValid()) {
      gameField(posXNow)(posYNow) match {
        case Some(value) =>  value.moveTo(posXNext, posYNext)
                      gameField(posXNext)(posYNext) = Option(value)
                      gameField(posXNow)(posYNow) = None
                      return true
        case None => return false
      }
    }
    false
  }

  def isSet(xPos: Int, yPos: Int) : Boolean = {
    gameField(xPos)(yPos) match {
      case Some(value) => true
      case None => false
    }
  }

  override def toString: String = {
    val build = new StringBuilder
    build.append("\tA\tB\tC\tD\tE\tF\tG\tH\n")
    build.append("\t──────────────────────────────\n")

    for (y <- Range(7, -1, -1)) {
      build.append(y + 1).append(" │\t")

      for (x <- 0 to 7) {
        gameField(y)(x) match {
          case Some(value) => build.append(value.toString + "\t")
          case None => build.append("─\t")
        }
      }
      build.append("\n")
    }
    build.toString
  }
}
