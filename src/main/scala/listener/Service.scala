package listener

import java.util.{Set, TreeSet}

import akka.actor.{Actor, ActorRef}
import akka.actor.Actor.Receive
import akka.routing.{Deafen, Listen, Listeners, WithListeners}

/**
  * Created by Dragos on 08.04.2016.
  */
trait Service extends Actor with Listeners{

  val customRecive: Receive

  listeners add sender

  override def receive: Receive = this.listenerManagement orElse this.customRecive
}

sealed trait ServiceMessage
case object OpenBlockUI extends ServiceMessage
case object CloseBlockUI extends ServiceMessage
