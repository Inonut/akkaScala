package util

/**
  * Created by Dragos on 07.04.2016.
  */
import javafx.application.Platform
import java.util.concurrent.Executor

import scala.concurrent.ExecutionContext

//
object JavaFXExecutionContext {
  implicit val javaFxExecutionContext: ExecutionContext = ExecutionContext.fromExecutor(new Executor {
    def execute(command: Runnable): Unit = Platform.runLater(command)
  })
}
