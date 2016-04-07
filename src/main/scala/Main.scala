import akka.actor.{ActorSystem, Props}
import akka.routing.{Listen, RoundRobinPool}
import akka.util.Timeout
import listener.BlockUIService
import model.Service
import util.BlockUI

import scala.concurrent.duration._
import scalafx.application.{JFXApp, Platform}
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene

/**
  * Created by Dragos on 07.04.2016.
  */
object Main extends JFXApp{

  new Thread(new Runnable {
    override def run(): Unit = BlockUI.show()
  }).start()



  //BlockUI.close()

}
