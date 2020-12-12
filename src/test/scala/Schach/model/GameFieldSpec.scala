package Schach.model

import java.awt.Color

import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameFieldSpec extends AnyWordSpec with Matchers {

  "Figure should from " should {
    val builder = new ChessGameFieldBuilder
    var gameField = builder.getNewGameField

    
    val figure = gameField.getFigure(1, 1)
    "should be" in {
      figure.get  mustBe a [Pawn]
    }

    "move to" in {

      gameField = gameField.moveTo(1, 1, 2, 3)
      gameField.getFigure(2,3).get mustBe a [Pawn]
    }


    "moving test" in {
      gameField = builder.getNewGameField
      gameField.wayToIsFreeDiagonal(2,0,6,4) should be(false)
      gameField.wayToIsFreeDiagonal(3,2,0,5) should be(true)

      gameField = gameField.moveTo(3,1,3,2)

      gameField = gameField.moveTo(2,0,6,4)

      gameField = gameField.moveTo(6,4,7,3)

      gameField = gameField.moveTo(7,3,6,2)

      gameField = gameField.moveTo(6,2,4,4)

      gameField = gameField.moveTo(3,2, 0,3)

      gameField.wayToIsFreeStraight(3,0,3,4) should be(true)
      gameField = gameField.moveTo(3,0,3,1)

      gameField = gameField.moveTo(3,1,4,2)

      gameField.moveTo(4,0,0,4)

      gameField.wayToIsFreeStraight(0,4,3,4) should be (true)

    }
    "do some more move cases" in {
      gameField = builder.getNewGameField
      gameField = gameField.moveTo(0, 1, 0, 3)
      gameField = gameField.moveTo(0, 0, 0, 2)
      gameField.getFigure(0,2).get shouldBe a[Rook]

      gameField = gameField.moveTo(1, 0, 2, 2)
      gameField.getFigure(2, 2).get shouldBe a[Knight]
      gameField = gameField.moveTo(1, 5, 2, 5)
      gameField.getFigure(2, 5) should be(None)

    }
    "cases for black figures trying to move" in {
      gameField = builder.getNewGameField
      gameField.wayToIsFreeStraight(1, 6, 1, 4) should be(true)

      gameField = gameField.moveTo(1, 6, 1, 4 )
      gameField.wayToIsFreeDiagonal(2, 7, 0, 5) should be(true)

      gameField = gameField.moveTo(7, 7, 7, 5)
      gameField.wayToIsFreeStraight(7, 5, 4, 5) should be(true)
    }

    "check if moving to a specific cell is allowed" in {
      gameField = builder.getNewGameField
      gameField.moveToFieldAllowed(1, 0, Color.WHITE) should be(false)
      gameField.moveToFieldAllowed(0, 2, Color.WHITE) should be(true)
      gameField.moveToFieldAllowed(2, 1, Color.WHITE) should be (false)
    }

    "have a nice String representation" in {
      gameField.toString shouldBe a[String]
    }

    "check if check" in {
      val f = King(3,0, Color.WHITE)
      val thrown = the [UnsupportedOperationException] thrownBy gameField.isCheck(f)
      thrown.getMessage should equal("isCheck() is still not supported")
    }
    "add Figures correctly" in {
      gameField = builder.getNewGameField
      val old = gameField.toString
      
      val vec = Vector(Rook(0, 0, Color.WHITE), Knight(1, 0, Color.WHITE), Bishop(2, 0, Color.WHITE), King(3, 0, Color.WHITE),
        Queen(4, 0, Color.WHITE), Bishop(5, 0, Color.WHITE), Knight(6, 0, Color.WHITE), Rook(7, 0, Color.WHITE),
        Pawn(0, 1, Color.WHITE), Pawn(1, 1, Color.WHITE), Pawn(2, 1, Color.WHITE), Pawn(3, 1, Color.WHITE),
        Pawn(4, 1, Color.WHITE), Pawn(5, 1, Color.WHITE), Pawn(6, 1, Color.WHITE), Pawn(7, 1, Color.WHITE))
      
      gameField.addFigures(vec)
      gameField.toString should be(old)
      
    }
  }
}
