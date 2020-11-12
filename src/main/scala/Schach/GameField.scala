package Schach

import java.awt.Color


case class GameField() {
  val gameField: Array[Array[Figure]] = Array.ofDim[Figure](8, 8)

  def init() {
    initBlack()
    initWhite()

  }

  def initWhite(): Unit = {
    gameField(0)(0) = new Rook(0, 0, Color.WHITE)
    gameField(1)(0) = new Knight(1, 0, Color.WHITE)
    gameField(2)(0) = new Bishop(2, 0, Color.WHITE)
    gameField(3)(0) = new King(3, 0, Color.WHITE)
    gameField(4)(0) = new Queen(4, 0, Color.WHITE)
    gameField(5)(0) = new Bishop(5, 0, Color.WHITE)
    gameField(6)(0) = new Knight(6, 0, Color.WHITE)
    gameField(7)(0) = new Rook(7, 0, Color.WHITE)

    gameField(0)(1) = new Pawn(0, 1, Color.WHITE)
    gameField(1)(1) = new Pawn(1, 1, Color.WHITE)
    gameField(2)(1) = new Pawn(2, 1, Color.WHITE)
    gameField(3)(1) = new Pawn(3, 1, Color.WHITE)
    gameField(4)(1) = new Pawn(4, 1, Color.WHITE)
    gameField(5)(1) = new Pawn(5, 1, Color.WHITE)
    gameField(6)(1) = new Pawn(6, 1, Color.WHITE)
    gameField(7)(1) = new Pawn(7, 1, Color.WHITE)

  }

  def initBlack(): Unit = {
    gameField(0)(7) = new Rook(0, 7, Color.BLACK)
    gameField(1)(7) = new Knight(1, 7, Color.BLACK)
    gameField(2)(7) = new Bishop(2, 7, Color.BLACK)
    gameField(3)(7) = new King(3, 7, Color.BLACK)
    gameField(4)(7) = new Queen(4, 7, Color.BLACK)
    gameField(5)(7) = new Bishop(5, 7, Color.BLACK)
    gameField(6)(7) = new Knight(6, 7, Color.BLACK)
    gameField(7)(7) = new Rook(7, 7, Color.BLACK)

    gameField(0)(6) = new Pawn(0, 6, Color.BLACK)
    gameField(1)(6) = new Pawn(1, 6, Color.BLACK)
    gameField(2)(6) = new Pawn(2, 6, Color.BLACK)
    gameField(3)(6) = new Pawn(3, 6, Color.BLACK)
    gameField(4)(6) = new Pawn(4, 6, Color.BLACK)
    gameField(5)(6) = new Pawn(5, 6, Color.BLACK)
    gameField(6)(6) = new Pawn(6, 6, Color.BLACK)
    gameField(7)(6) = new Pawn(7, 6, Color.BLACK)
  }

  def moveValid(): Boolean = true

  def moveTo(posXNow : Int, posYNow : Int, posXNext : Int, posYNext: Int): Boolean = {
    if (moveValid()) {

      if (gameField(posXNow)(posYNow) != null) {
        val figure = gameField(posXNow)(posYNow)
        figure.posX = posXNext
        figure.posY = posYNext
        gameField(posXNext)(posYNext) = figure
        gameField(posXNow)(posYNow) =  null
        true
      } else false
    }
    false
  }

  def getFigure(xPos : Int, yPos : Int): Figure = {
    gameField(xPos)(yPos)
  }

  override def toString: String = {
    val build = new StringBuilder
    build.append("\tA\tB\tC\tD\tE\tF\tG\tH\n")
    build.append("\t──────────────────────────────\n")

    for (y <- Range(7, -1, -1)){
      build.append(y + 1).append(" │\t")

      for (x <- 0 to 7){
        if (gameField(x)(y) != null){
          build.append(gameField(x)(y).toString).append("\t")
        } else build.append("_\t")
      }
      build.append("\n")
    }
    build.toString
  }
}
