package Schach.controller

import Schach.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec



class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when  {
    "observed by an Observer" should {
      val controller = new Controller()
      val vec = Vector(0, 1, 0, 2)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      controller.add(observer)
      "notify its observer after init" in {
        controller.createGameField()
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
        controller.subscribers should not contain observer
      }
    }
    "used as an Originator" should {
      val controller = new Controller()
      val vec = Vector(0, 1, 0, 2)

      "handle undo/redo correctly" in {
        controller.createGameField()
        controller.movePiece(vec)
        val tmp = controller.gameFieldToString

        controller.undo()
        controller.gameFieldToString should not be tmp

        controller.redo()
        controller.gameFieldToString should be(tmp)
      }

      "save and load a state" in {
        controller.createGameField()
        val old = controller.gameFieldToString
        controller.save()

        controller.movePiece(vec)
        controller.gameFieldToString should not be old

        controller.restore()
        controller.gameFieldToString should be(old)
      }
      "inform if there is a current save state" in {
        controller.save()
        controller.caretaker.called should be(true)
      }

    }
  }

}
