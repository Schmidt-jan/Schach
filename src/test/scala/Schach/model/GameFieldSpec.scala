package Schach.model

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
      gameField.moveValid(1, 1, 2, 3) should be(true)
      gameField = gameField.moveTo(1, 1, 2, 3)
      gameField.getFigure(2,3).get mustBe a [Pawn]
    }


    "moving test" in {
      gameField = new GameField()
      gameField.wayToIsFreeDiagonal(2,0,6,4) should be(false)

      gameField = gameField.moveTo(3,1,3,2)
      gameField.moveValid(2,0,6,4) should be (true)
      gameField = gameField.moveTo(2,0,6,4)
      gameField.moveValid(6,4,7,3) should be (true)
      gameField = gameField.moveTo(6,4,7,3)
      gameField.moveValid(7,3,6,2) should be (true)
      gameField = gameField.moveTo(7,3,6,2)
      gameField.moveValid(6,2,4,4) should be (true)
      gameField = gameField.moveTo(6,2,4,4)
      gameField.moveValid(4,4,0,4) should be (false)

      gameField.moveValid(3,0,3,4) should be(false)
      gameField = gameField.moveTo(3,2, 0,3)

      gameField.wayToIsFreeStraight(3,0,3,4) should be(true)
      gameField.moveValid(3,0,3,1) should be(true)
      gameField = gameField.moveTo(3,0,3,1)
      gameField.moveValid(3,1,4,2) should be(true)
      gameField = gameField.moveTo(3,1,4,2)

      gameField.moveValid(4,0,0,4) should be(true)
      gameField.moveTo(4,0,0,4)

      gameField.wayToIsFreeStraight(0,4,3,4) should be (true)
      gameField = gameField.moveTo(0,4,3,4)


    }





    "have a nice String representation" in {
      gameField.toString shouldBe a[String]
    }
  }
}