package Schach.model

import java.awt.Color

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class RulesSpec extends AnyWordSpec with Matchers {

  "Rules" should {
    val gameField = new GameField()
    val rule = Rules(gameField)

    "confirm if a move is according to the rules" in {
      rule.moveValid(0,0,0,2) should be(false)
      rule.moveValid(1,0,0,2) should be(true)
      rule.moveValid(2,0,1,2) should be(false)
      rule.moveValid(3,0,3,1) should be(false)
      rule.moveValid(4,0,5,0) should be(false)
      rule.moveValid(4,0,9,0) should be(false)
      rule.moveValid(0,3,0,4) should be(false)
    }

    "make sure Pawn is moving as intended" in {
      val p = Pawn(0,1, Color.WHITE)
      p.hasBeenMoved should be(false)
      val p2 = Pawn(0,6, Color.BLACK)
      val p3 = Pawn(0,1, Color.WHITE, Some(true))
      rule.validPawn(p, 0, 0) should be(false)
      rule.validPawn(p2, 0, 7) should be(false)


      rule.validPawn(p, 0, 2) should be(true)
      rule.validPawn(p3, 0, 3) should be(false)
      rule.validPawn(p3, 0, 2) should be(true)
    }
  }

}
