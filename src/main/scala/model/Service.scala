package model



import akka.actor.{Actor, Props}

import scalafx.beans.property.StringProperty

/**
  * Created by Dragos on 05.04.2016.
  */
class Service extends Actor {

  val fx = context.actorOf(Props[UpdateViewService])

  override def receive: Receive = {
    case LogMessages(message: StringProperty) =>

      println(Thread.currentThread())
      for(i <- 1 to 100000000){
        Thread.sleep(10)
        fx ! PMessages (() => {message.value = i.toString})
        println(message.value)
      }

  }

}
