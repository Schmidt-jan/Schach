package Schach.controller.controllerComponent.controllerBaseImpl

import Schach.model.figureComponent.Figure
import Schach.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import java.awt.Color

import Schach.GameFieldModule
import Schach.controller.controllerComponent.ControllerInterface
import com.google.inject.Guice



class ControllerSpec extends AnyWordSpec with Matchers {

  val injector = Guice.createInjector(new GameFieldModule)

  "A Controller" when  {
    "observed by an Observer" should {
      val controller = injector.getInstance(classOf[ControllerInterface])
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
        controller.changePlayer()
      }
      "check if a move is valid" in {
        controller.createGameField()
        controller.movePiece(vec)
        val v = Vector(1, 6, 1, 4)
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
      val controller = injector.getInstance(classOf[ControllerInterface])
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
      "return a gameField via getGameField" in {
        controller.getGameField shouldBe a [Vector[Figure]]
      }
      "give the first turn to white and the second to black" in {
        controller.createGameField()
        controller.getPlayer() should be (Color.WHITE)

        controller.movePiece(vec)
        controller.getPlayer() should be (Color.BLACK)
      }
      "set a player correctly" in {
        controller.createGameField()
        controller.setPlayer(Color.BLACK)
        controller.getPlayer() should not be (Color.WHITE)
      }

    }
  }

}
