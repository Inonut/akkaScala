package model

import akka.actor.Actor

/**
  * Created by Dragos on 05.04.2016.
  */
class Log extends Actor{

  override def receive: Receive = {
    case _ => println("ceva")
  }
}
