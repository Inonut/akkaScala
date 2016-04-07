package domain

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

/**
  * Created by Dragos on 07.04.2016.
  */
object ApplicationModel {

  val context = ActorSystem("mySystem", ConfigFactory.load)

}
