package Schach.model.fileIOComponent.fileIOJSONImpl

import Schach.controller.controllerComponent.controllerBaseImpl.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec



class FileIOSpecJSON extends AnyWordSpec with Matchers {

  "The JSON FileIO" when {
    "making use of the FileIO Implementation" should {
      val fileIo = new FileIO

      val controller = new Controller
      var gameField = controller.gameField

      "save and load a savefile correctly" in {
        gameField = gameField.moveTo(0, 1, 0, 2)
        val old = gameField
        fileIo.saveGame(controller.gameField)

        gameField = gameField.moveTo(1, 6, 1, 4)
        gameField.toString should not be old

        val (v, _) = fileIo.loadGame
        gameField = gameField.addFigures(v)
        gameField should be (old)
      }
    }
  }

}
