package model

/**
  * Created by Dragos on 05.04.2016.
  */
sealed trait Message
case object OK extends Message
case class LogMessages(message: Any) extends Message
case class PMessages(message: () => Unit) extends Message