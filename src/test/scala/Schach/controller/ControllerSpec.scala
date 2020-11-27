package Schach.controller

import Schach.model.GameField
import Schach.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec



class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when  {
    "observed by an Observer" should {
      val field = new GameField()
      val controller = new Controller(field)
      val vec = Vector(0, 1, 0,2)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      controller.add(observer)
      "notify its observer after init" in {
        controller.createGameField
        observer.updated should be(true)
      }
      "notify its observer after moving a piece" in {
        controller.movePiece(vec)
        observer.updated should be(true)
      }
      "check if a move is valid" in {
        val v = Vector(1, 1, 1, 3)
        controller.moveIsValid(v) should be(true)
      }
      "return a string representation of the GameField" in {
        controller.gameFieldToString shouldBe a[String]
      }
      "remove an observer" in {
        controller.remove(observer)
        controller.subscribers should not contain (observer)
      }
    }
  }

}
