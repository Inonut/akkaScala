package model

import akka.actor.Actor
import com.sun.javafx.application.PlatformImpl

/**
  * Created by Dragos on 05.04.2016.
  */
class UpdateViewService extends Actor{


  override def receive: Receive = {
    case PMessages(body) => PlatformImpl.runAndWait(new Runnable {
      override def run(): Unit = body()
    })
  }
}
