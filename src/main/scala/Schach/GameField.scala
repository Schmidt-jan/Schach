package Schach

import java.awt.Color


case class GameField() {
  val gameField = Array.ofDim[Figure](8, 8)

  def init() {
    initBlack()
    initWhite()

  }

  def initWhite(): Unit = {
    gameField(0)(0) = new Rook('A', 1, Color.WHITE)
    gameField(1)(0) = new Knight('B', 1, Color.WHITE)
    gameField(2)(0) = new Bishop('C', 1, Color.WHITE)
    gameField(3)(0) = new King('D', 1, Color.WHITE)
    gameField(4)(0) = new Queen('E', 1, Color.WHITE)
    gameField(5)(0) = new Bishop('F', 1, Color.WHITE)
    gameField(6)(0) = new Knight('G', 1, Color.WHITE)
    gameField(7)(0) = new Rook('H', 1, Color.WHITE)

    gameField(0)(1) = new Pawn('A', 2, Color.WHITE)
    gameField(1)(1) = new Pawn('B', 2, Color.WHITE)
    gameField(2)(1) = new Pawn('C', 2, Color.WHITE)
    gameField(3)(1) = new Pawn('D', 2, Color.WHITE)
    gameField(4)(1) = new Pawn('E', 2, Color.WHITE)
    gameField(5)(1) = new Pawn('F', 2, Color.WHITE)
    gameField(6)(1) = new Pawn('G', 2, Color.WHITE)
    gameField(7)(1) = new Pawn('H', 2, Color.WHITE)

  }

  def initBlack(): Unit = {
    gameField(0)(7) = new Rook('A', 8, Color.BLACK)
    gameField(1)(7) = new Knight('B', 8, Color.BLACK)
    gameField(2)(7) = new Bishop('C', 8, Color.BLACK)
    gameField(3)(7) = new King('D', 8, Color.BLACK)
    gameField(4)(7) = new Queen('E', 8, Color.BLACK)
    gameField(5)(7) = new Bishop('F', 8, Color.BLACK)
    gameField(6)(7) = new Knight('G', 8, Color.BLACK)
    gameField(7)(7) = new Rook('H', 8, Color.BLACK)

    gameField(0)(6) = new Pawn('A', 7, Color.BLACK)
    gameField(1)(6) = new Pawn('B', 7, Color.BLACK)
    gameField(2)(6) = new Pawn('C', 7, Color.BLACK)
    gameField(3)(6) = new Pawn('D', 7, Color.BLACK)
    gameField(4)(6) = new Pawn('E', 7, Color.BLACK)
    gameField(5)(6) = new Pawn('F', 7, Color.BLACK)
    gameField(6)(6) = new Pawn('G', 7, Color.BLACK)
    gameField(7)(6) = new Pawn('H', 7, Color.BLACK)
  }

  def moveValid(): Boolean = true

  def moveTo(posXNow : Char, posYNow : Int, posXNext : Char, posYNext: Int): Boolean = {
    if (moveValid()) {
      var piece  = gameField(posXNow)(posYNow)
      piece.posX = posXNext
      piece.posY = posYNext
      true
    } else false
  }

  override def toString: String = {
    var build = new StringBuilder()
    for (y <- Range(7, -1, -1)){

      for (x <- 0 to 7){
        if (gameField(x)(y) != null){
          build.append(gameField(x)(y).toString).append("\t")
        } else build.append("_\t")
      }
      build.append("\n")
    }

    build.toString()
  }
}
