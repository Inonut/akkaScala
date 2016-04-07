package model

import akka.actor.{ActorSystem, Props}
import akka.routing.{Listen, RoundRobinPool}
import akka.util.Timeout

import scala.concurrent.duration._
import scalafx.beans.property.StringProperty


/**
  * Created by Dragos on 05.04.2016.
  */
object Main {

  def main(args: Array[String]) {

    implicit val timeout = Timeout(5 seconds);

    val system = ActorSystem("HelloSystem")
    val helloActor = system.actorOf(Props[Service].withRouter(RoundRobinPool(4)), name = "helloactor")

    helloActor ! Listen(system.actorOf(Props[Log]))

    helloActor ! LogMessages("1")
    helloActor ! LogMessages("2")
    helloActor ! LogMessages("3")
    helloActor ! LogMessages("4")

    val x = new StringProperty()

  }
}
