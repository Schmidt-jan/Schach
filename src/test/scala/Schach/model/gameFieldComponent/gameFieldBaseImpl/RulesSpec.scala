package Schach.model.gameFieldComponent.gameFieldBaseImpl

import java.awt.Color

import Schach.model.figureComponent._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class RulesSpec extends AnyWordSpec with Matchers {

  "Rules" should {
    val builder = new ChessGameFieldBuilder
    var gameField : GameField = builder.getNewGameField
    var rule = Rules(gameField)

    "confirm if a move is according to the rules" in {
      gameField = builder.getNewGameField
      rule = Rules(gameField)
      gameField.moveValid(0,0,0,2) should be(false)
      gameField.moveValid(1,0,0,2) should be(true)
      gameField.moveValid(2,0,1,2) should be(false)
      gameField.moveValid(3,0,3,1) should be(false)
      gameField.moveValid(4,0,5,0) should be(false)
      gameField.moveValid(4,0,9,0) should be(false)
      gameField.moveValid(0,3,0,4) should be(false)
    }

    "make sure Pawn is moving as intended" in {
      gameField = builder.getNewGameField
      rule = Rules(gameField)
      val p = Pawn(0,1, Color.WHITE)
      p.hasBeenMoved should be(false)
      val p2 = Pawn(0,6, Color.BLACK)
      val p3 = Pawn(7,1, Color.WHITE, Some(true))
      rule.validPawn(p, 0, 0) should be(false)
      rule.validPawn(p2, 0, 7) should be(false)
      rule.validPawn(p3, 0, 3) should be(false)

      rule.validPawn(p3, 7, 2) should be(true)
      rule.validPawn(p, 0, 3) should be(true)
      rule.validPawn(p, 0, 4) should be(false)
    }

    "make the Knight jump horizontally" in {
      val k = Knight(1, 0, Color.WHITE)
      rule.validKnight(k, 2, 2) should be(true)
      rule.validKnight(k, 3, 1) should be(false)
      rule.validKnight(k, 0, 4) should be(false)
    }

    "have the Queen move diagonally" in {
      val q = Queen(3, 2, Color.WHITE)
      rule.validQueen(q, 6, 5) should be(true)
    }

    "make the King not be able to move more than 1 cells" in {
      val king = King(4, 2, Color.WHITE)
      rule.validKing(king, 4, 4) should be(false)
    }

    "check if the Rook is moving as intended" in {
      val r = Rook(0,3, Color.WHITE)
      rule.validRook(r, 7,3) should be(true)
      rule.validRook(r, 2,5) should be(false)
    }

    "make sure the about to be moved figure is actually existing" in {
      rule.moveValidFigure(0, 3, 0, 4) should be(false)
      rule.moveValidWithoutKingCheck(7, 3, 7, 4) should be(false)
    }

    "make sure the bishop is behaving appropriately" in {
      gameField.moveTo(4, 0, 0, 4)
      gameField.moveTo(2, 0, 1, 3)

      rule.moveValidFigure(1, 3, 0, 4) should be (false)
    }

    "control pawn moving diagonally if there is an enemy figure" in {
      gameField = builder.getNewGameField
      gameField.moveTo(0, 1, 0, 3)
      gameField.moveTo(1, 6, 1, 4)
      rule.moveValidWithoutKingCheck(0, 3, 1, 4)
    }
  }

}
