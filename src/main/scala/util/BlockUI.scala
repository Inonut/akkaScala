package util

import akka.actor.Props
import akka.routing.RoundRobinPool
import akka.util.Timeout
import domain.{ApplicationModel, BlockUIModel}
import listener.{BlockUIService, Close, Show}

import scala.concurrent.duration._
import scalafx.application.Platform
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.ProgressIndicator
import scalafx.scene.layout.VBox
import scalafx.stage.{Modality, Stage, StageStyle}

/**
  * Created by Dragos on 07.04.2016.
  */
object BlockUI {

  private implicit val timeout = Timeout(5 seconds)

  private val model = new BlockUIModel()
  private val blockUIService = ApplicationModel.context.actorOf(Props[BlockUIService].withRouter(RoundRobinPool(BusinessConstant.defalutPool)).withDispatcher("javafx-dispatcher"), name = "blockUI")


  private val stage = new Stage {
    resizable = false
    initStyle(StageStyle.UNDECORATED)
    initModality(Modality.APPLICATION_MODAL)

    scene = new Scene(100,100) {
      root = new VBox() {
        spacing = 5
        alignment = Pos.Center
        children = List(
          new ProgressIndicator() {
            progress => model.progressProp
          }
        )
      }
    }
  }

  model.nrOfBlockUI.onChange((_, oldValue: Number, newValue: Number)  => {
    print(Platform.isFxApplicationThread)
    Platform.runLater(new Runnable {
      override def run(): Unit = {
        newValue.intValue() match {
          case 0 => stage.close()
          case _ => stage.show()
        }
      }
    })

  })


  def show(): Unit ={
    blockUIService ! Show(model)
  }

  def close(): Unit ={
    blockUIService ! Close(model)
  }

}
