package domain

import scalafx.beans.property.{DoubleProperty, IntegerProperty}

/**
  * Created by Dragos on 07.04.2016.
  */
class BlockUIModel {

  val progressProp = new DoubleProperty()
  val nrOfBlockUI = new IntegerProperty()
}
