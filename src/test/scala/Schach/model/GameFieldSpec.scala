package Schach.model

import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.awt.Color

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
      gameField.wayToIsFreeDiagonal(0, 3, 1, 2) should be(true)
      gameField.wayToIsFreeDiagonal(0, 5, 3, 2) should be(true)

      gameField = gameField.moveTo(3,1,3,3)

      gameField = gameField.moveTo(7,6,7,5)

      gameField = gameField.moveTo(4,0,0,4)

      gameField.moveValid(2, 6, 2, 5) should be (false)

      gameField.wayToIsFreeStraight(0,4,2,4) should be(true)
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

      gameField = builder.getNewGameField
      gameField.moveTo(2, 0, 2, 1)
      gameField.getFigure(2, 1) shouldBe a[Some[Bishop]]

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
      gameField.moveToFieldAllowed(1, 0, gameField.getFigure(1,1).get) should be(false)
      gameField.moveToFieldAllowed(0, 2, gameField.getFigure(1,1).get) should be(true)
      gameField.moveToFieldAllowed(2, 1, gameField.getFigure(1,1).get) should be (false)
    }

    "have a nice String representation" in {
      gameField.toString shouldBe a[String]
    }

    "make use of Figure's equals method" in {
      val f3 = gameField.getFigure(0,3)
      val f = new Figure {
        override val x: Int = 1
        override val y: Int = 2
        override val color: Color = color
      }
      f.equals(f3) should be (false)
      gameField.getFigure(2,7) shouldBe a[Some[Bishop]]
    }

    "check for checkmate" in {
      gameField = builder.getNewGameField
      gameField.isCheckmate should be (false)
      val p = gameField.getFigure(0, 1).get
      val p2 = Knight(1, 7, Color.BLACK)
      val k = King(7, 7, Color.WHITE)
      val k2 = King(1, 4, Color.WHITE)
      gameField.setSelfIntoCheck(p, 0, 2, k) should be (false)
      gameField.setSelfIntoCheck(p2,0, 2, k2) should be (true)

    }
    /*
    "set a GameField" in {
      gameField = builder.getNewGameField
      gameField2 = gameField2.moveTo(1, 0, 2, 2)
      val old = gameField2.toString
      gameField = builder.setGameField(gameField2)
      gameField.toString should be (old)
    }
     */
  }
}