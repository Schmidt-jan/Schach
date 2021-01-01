package Schach.controller.controllerComponent.controllerBaseImpl

import Schach.util.Command

class MoveCommand(xNow: Int, yNow: Int, xNext: Int, yNext: Int, controller: Controller) extends Command {
  var memento = new GameFieldMemento(controller.gameField.getFigures, controller.gameField.getPlayer)

  override def doStep(): Unit = {
    memento = new GameFieldMemento(controller.gameField.getFigures, controller.gameField.getPlayer)
    controller.gameField = controller.gameField.moveTo(xNow, yNow, xNext, yNext)
  }


  //TODO add support for reset unchecking
  override def undoStep(): Unit = {
    controller.gameField.clear()
    controller.gameField.addFigures(memento.getFigures)
    controller.gameField.setPlayer(memento.getPlayer)
  }


  override def redoStep(): Unit = {
    controller.gameField = controller.gameField.moveTo(xNow, yNow, xNext, yNext)
    controller.changePlayer()
  }

  /*
  override def doStep(): Unit = controller.gameField = controller.gameField.moveTo(xNow, yNow, xNext, yNext)
  override def undoStep(): Unit = controller.gameField = controller.gameField.moveTo(xNext, yNext, xNow, yNow)
  override def redoStep(): Unit = controller.gameField = controller.gameField.moveTo(xNow, yNow, xNext, yNext)
   */
}
