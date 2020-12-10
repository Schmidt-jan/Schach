package Schach.controller

import Schach.util.Command

class MoveCommand(xNow: Int, yNow: Int, xNext: Int, yNext: Int, controller: Controller) extends Command {
  var memento = new GameFieldMemento(controller.gameField.getFigures)

  override def doStep(): Unit = {
    memento = new GameFieldMemento(controller.gameField.getFigures)
    controller.gameField = controller.gameField.moveTo(xNow, yNow, xNext, yNext)
  }

  override def undoStep(): Unit = {
    controller.gameField.clear()
    controller.gameField.addFigures(memento.getFigures)
  }

  override def redoStep(): Unit = controller.gameField = controller.gameField.moveTo(xNow, yNow, xNext, yNext)

  /*
  override def doStep(): Unit = controller.gameField = controller.gameField.moveTo(xNow, yNow, xNext, yNext)

  override def undoStep(): Unit = controller.gameField = controller.gameField.moveTo(xNext, yNext, xNow, yNow)

  override def redoStep(): Unit = controller.gameField = controller.gameField.moveTo(xNow, yNow, xNext, yNext)

   */
}
