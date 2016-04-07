package listener

import akka.actor.Actor
import domain.BlockUIModel
import util.JavaFXExecutionContext

import scala.concurrent.ExecutionContext.Implicits.global
import scalafx.application.Platform

/**
  * Created by Dragos on 07.04.2016.
  */
class BlockUIService extends Actor{

  def receive = {
    case Show(model: BlockUIModel) => model.nrOfBlockUI.value = model.nrOfBlockUI.value + 1
    case Close(model: BlockUIModel) => model.nrOfBlockUI.value = model.nrOfBlockUI.value - 1
  }
  
}

case class Close(model: BlockUIModel)
case class Show(model: BlockUIModel)


