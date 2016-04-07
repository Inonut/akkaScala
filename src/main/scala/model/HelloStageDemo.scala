package model

/**
  * Created by Dragos on 05.04.2016.
  */
import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinPool
import akka.util.Timeout

import scala.concurrent.duration._
import scalafx.application.JFXApp
import scalafx.beans.property.StringProperty
import scalafx.scene.Scene
import scalafx.scene.control.TextField
import scalafx.scene.paint.Color

object HelloStageDemo extends JFXApp {

  val textStr = new StringProperty()

  implicit val timeout = Timeout(5 seconds);

  val system = ActorSystem("HelloSystem")
  val helloActor = system.actorOf(Props[Service].withRouter(RoundRobinPool(4)), name = "helloactor")

  helloActor ! LogMessages(textStr)

  stage = new JFXApp.PrimaryStage {
    title.value = "Hello Stage"
    width = 600
    height = 450
    scene = new Scene {
      fill = Color.LightGreen
      content = new TextField{
        text.bind(textStr)
      }
    }
  }
}