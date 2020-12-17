package Schach.aview

import java.awt.Color

import Schach.controller.Controller
import Schach.util.Observer

import scala.swing._
import scala.swing.event.ButtonClicked


class Gui(controller: Controller) extends Frame with Observer {

  controller.add(this)

  var fromSet = false
  var from = (-1, -1)
  var to = (-1, -1)

  listenTo(this)


  title = "Chess"
  minimumSize = new Dimension(500, 500)

  var buttons = Array.ofDim[Button](8,8)

  contents = new GridPanel(8, 8) {
    for (i <- Range(0, 64)) {
      val cell = i % 8
      val row = Integer2int(7 - i / 8)

      val button: Button = new Button {

        //set background color
        if (cell % 2 == 1)
          if (row % 2 == 1) background = Color.WHITE
          else background = Color.GRAY
        else if (row % 2 == 0) background = Color.WHITE
        else background = Color.GRAY

        reactions += {
          case e: ButtonClicked => if (!fromSet) {
            fromSet = true
            from = (cell, row)
            println("from : " + from)
          } else {
            fromSet = false
            to = (cell, row)
            println("move : " + from + " " + to)
          }
        }
      }
      contents += button
      listenTo(button)
      buttons(cell)(row) = button
    }

  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("New")(controller.createGameField()))
      contents += new MenuItem(Action("Close")(System.exit(0)))
    }
    contents += new Menu("Edit") {
      contents += new MenuItem(Action("Undo")(controller.undo()))
      contents += new MenuItem(Action("Redo")(controller.redo()))
      contents += new MenuItem(Action("Save")(controller.save()))
      contents += new MenuItem(Action("Load") {
        if (controller.caretaker.called) {
          controller.restore()
        } else {
          println("No Save created yet")
        }
      })
    }
  }


  resizable = false
  visible = true

  override def update: Unit = {
    println("Frame update called")

    buttons.foreach(_.foreach(_.text = ""))
    val figures = controller.getGameField.filter(!_.checked)
    for (figure <- figures) {
      buttons(figure.x)(figure.y).text = figure.toString
    }
  }
}