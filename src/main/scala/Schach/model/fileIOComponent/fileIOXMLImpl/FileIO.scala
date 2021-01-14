package Schach.model.fileIOComponent.fileIOXMLImpl

import Schach.model.figureComponent._
import Schach.model.fileIOComponent.FileIOInterface
import Schach.model.gameFieldComponent.GameFieldInterface

import java.awt.Color
import java.io._
import scala.xml.{Elem, PrettyPrinter}

class FileIO extends FileIOInterface{

  override def loadGame: (Vector[Figure], Color) = {

    val file = scala.xml.XML.loadFile("save.xml")
    val player = (file \\ "@player").text
    val figureNodes = (file \\ "figure")
    var figureVec: Vector[Figure] = Vector.empty[Figure]
    for (figure <- figureNodes) {
      val x = (figure \ "@xPos").text.toInt
      val y = (figure \ "@yPos").text.toInt
      val moved = (figure \ "@moved").text
      val fig = (figure \ "@value").text

      if (!fig.equals("")) {
        val piece = getPiece(fig, x, y, moved)
        figureVec = figureVec :+ piece
      }
    }
    (figureVec, getColor(player))
  }

  override def saveGame(gameField: GameFieldInterface): Unit = {
    val printWriter = new PrintWriter(new File("save.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gameFieldToXML(gameField))
    printWriter.write(xml)
    printWriter.close()
  }

  def gameFieldToXML(gameField: GameFieldInterface): Elem = {
    <gameField player={gameField.getPlayer.toString}>
      {
        for {
          xPos <- 0 until 8
          yPos <- 0 until 8
        } yield figureToXML(gameField, xPos, yPos)
      }
    </gameField>
  }

  def figureToXML(gameField: GameFieldInterface, xPos: Int, yPos: Int): Elem = {

    val figure = gameField.getFigure(xPos, yPos)
    val xmlFig = {
      <figure xPos={xPos.toString} yPos={yPos.toString} moved={getCorrectString(figure)}
      value={
      figure match {
        case Some(value) => value.toString
        case None => ""
      }}>
      </figure>
    }
    xmlFig
  }

}
