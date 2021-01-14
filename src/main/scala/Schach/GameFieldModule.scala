package Schach

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import model.gameFieldComponent.gameFieldBaseImpl._
import model.gameFieldComponent._
import controller.controllerComponent._

class GameFieldModule extends AbstractModule with ScalaModule {

  val builder = new ChessGameFieldBuilder
  val gameField = "Chess"

  override def configure() : Unit = {
    bindConstant().annotatedWith(Names.named("Chess")).to(gameField)
    bind[GameFieldInterface].to[GameField]
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    bind[GameFieldInterface].annotatedWithName("Chess").toInstance(new GameField(builder.getNewGameField.getFigures));
  }
}
