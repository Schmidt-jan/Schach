package Schach.model

import java.awt.Color

import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameFieldSpec extends AnyWordSpec with Matchers {

  "Figure should from " should {
    var gameField = new GameField()

    val figure = gameField.getFigure(1, 1)
    "should be" in {
      figure.get  mustBe a [Pawn]
    }

    "move to" in {

      gameField = gameField.moveTo(1, 1, 2, 3)
      gameField.getFigure(2,3).get mustBe a [Pawn]
    }


    "moving test" in {
      gameField = new GameField()
      gameField.wayToIsFreeDiagonal(2,0,6,4) should be(false)

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
    "check if moving to a specific cell is allowed" in {
      gameField = new GameField()
      gameField.moveToFieldAllowed(1, 0, Color.WHITE) should be(false)
      gameField.moveToFieldAllowed(0, 2, Color.WHITE) should be(true)
    }

    "have a nice String representation" in {
      gameField.toString shouldBe a[String]
    }
  }
}