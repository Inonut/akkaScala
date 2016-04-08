package view

import domain.BlockUIModel

import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.ProgressIndicator
import scalafx.scene.layout.VBox
import scalafx.stage.{Modality, Stage, StageStyle}

/**
  * Created by Dragos on 08.04.2016.
  */
class BlockUIView {

  val model = new BlockUIModel()

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
}
