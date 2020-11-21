package Schach.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PiecesSpec extends AnyWordSpec with Matchers {

  "A Piece" when {
    "should be a chess figure" should {
      val piece = Pieces(0)
      "first piece" in {
        piece.getPiece() should be("pawn")
      }
      val piece1 = Pieces(1)
      "second piece" in {
        piece1.getPiece() should be("rook")
      }
      val piece2 = Pieces(2)
      "third piece" in {
        piece2.getPiece() should be("knight")
      }
      val piece3 = Pieces(3)
      "fourth piece" in {
        piece3.getPiece() should be("bishop")
      }
      val piece4 = Pieces(4)
      "fifth piece" in {
        piece4.getPiece() should be("queen")
      }
      val piece5 = Pieces(5)
      "sixth piece" in {
        piece5.getPiece() should be("king")
      }
    }
  }
}