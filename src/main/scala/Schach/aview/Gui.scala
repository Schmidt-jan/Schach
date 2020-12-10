package Schach.aview

import Schach.util.Observer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{BorderPane, HBox}
import scalafx.scene.paint.Color.{Black, Cyan, DodgerBlue, PaleGreen, SeaGreen}
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text



object Gui extends JFXApp with Observer {

  stage = new PrimaryStage {
    title = "Chess"
    width = 800
    height = 600

    width onChange show
    height onChange show
  }


  override def update: Unit = ???
}

object HelloSBT extends JFXApp {
  stage = new PrimaryStage {
    title = "Chess"
    scene = new Scene {
      fill = Black
      content = new HBox() {
        padding = Insets(200)

        children = Seq {
          new Text {
            text = "Welcome to chess!"
            style = "-fx-font-size: 48pt"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(PaleGreen, SeaGreen))
          }
          new Text {
            text = "Test :)"
            style = "-fx-font-size: 48 pt"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(Cyan, DodgerBlue)
            )
            effect = new DropShadow {
              color = DodgerBlue
              radius = 25
              spread = 0.25
            }
          }
        }
      }
    }
  }
}