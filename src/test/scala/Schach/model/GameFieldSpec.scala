package Schach.model

import java.awt.Color

import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameFieldSpec extends AnyWordSpec with Matchers {

  "Figure should from " should {
    val builder = new ChessGameFieldBuilder
    builder.makeGameField()
    var instance = builder.getGameField

    val figure = instance.getFigure(1, 1)
    "should be" in {
      figure.get  mustBe a [Pawn]
    }

    "move to" in {
      builder.makeGameField()
      instance = builder.getGameField
      instance = instance.moveTo(1, 1, 2, 3)
      instance.getFigure(2,3).get mustBe a [Pawn]
    }


    "moving test" in {
      builder.makeGameField()
      instance = builder.getGameField

      instance.wayToIsFreeDiagonal(2,0,6,4) should be(false)
      instance.wayToIsFreeDiagonal(3,2,0,5) should be(true)

      instance = instance.moveTo(3,1,3,2)

      instance = instance.moveTo(2,0,6,4)

      instance = instance.moveTo(6,4,7,3)

      instance = instance.moveTo(7,3,6,2)

      instance = instance.moveTo(6,2,4,4)

      instance = instance.moveTo(3,2, 0,3)

      instance.wayToIsFreeStraight(3,0,3,4) should be(true)
      instance = instance.moveTo(3,0,3,1)

      instance = instance.moveTo(3,1,4,2)

      instance.moveTo(4,0,0,4)

      instance.wayToIsFreeStraight(0,4,3,4) should be (true)

    }
    "do some more move cases" in {
      builder.makeGameField()
      instance = builder.getGameField

      instance = instance.moveTo(0, 1, 0, 3)
      instance = instance.moveTo(0, 0, 0, 2)
      instance.getFigure(0,2).get shouldBe a[Rook]

      instance = instance.moveTo(1, 0, 2, 2)
      instance.getFigure(2, 2).get shouldBe a[Knight]
      instance = instance.moveTo(1, 5, 2, 5)
      instance.getFigure(2, 5) should be(None)

    }
    "cases for black figures trying to move" in {
      builder.makeGameField()
      instance = builder.getGameField

      instance.wayToIsFreeStraight(1, 6, 1, 4) should be(true)

      instance = instance.moveTo(1, 6, 1, 4 )
      instance.wayToIsFreeDiagonal(2, 7, 0, 5) should be(true)

      instance = instance.moveTo(7, 7, 7, 5)
      instance.wayToIsFreeStraight(7, 5, 4, 5) should be(true)
    }
    "check if moving to a specific cell is allowed" in {
      builder.makeGameField()
      instance = builder.getGameField

      instance.moveToFieldAllowed(1, 0, Color.WHITE) should be(false)
      instance.moveToFieldAllowed(0, 2, Color.WHITE) should be(true)
    }

    "have a nice String representation" in {
      instance.toString shouldBe a[String]
    }
    "check if check" in {
      val f = King(3,0, Color.WHITE)
      instance.isCheck(f) should be(false)
    }
  }
}