package listener

import akka.util.Timeout
import domain.BlockUIModel

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
class BlockUIService extends Service{

  private val model = new BlockUIModel()

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
    newValue.intValue() match {
      case 0 => stage.close()
      case _ => stage.show()
    }
  })

  override val customRecive: Receive = {
    case OpenBlockUI => model.nrOfBlockUI.value = model.nrOfBlockUI.value + 1
    case CloseBlockUI => model.nrOfBlockUI.value = model.nrOfBlockUI.value - 1
  }
}


