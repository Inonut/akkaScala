package util


import akka.dispatch.{DispatcherPrerequisites, ExecutorServiceFactory, ExecutorServiceConfigurator}
import com.typesafe.config.Config
import java.util.concurrent.{ExecutorService, AbstractExecutorService, ThreadFactory, TimeUnit}
import java.util.Collections
import javax.swing.SwingUtilities
import javafx.application.Platform


abstract class GUIExecutorService extends AbstractExecutorService {
  def execute(command: Runnable): Unit

  def shutdown(): Unit = ()

  def shutdownNow() = Collections.emptyList[Runnable]

  def isShutdown = false

  def isTerminated = false

  def awaitTermination(l: Long, timeUnit: TimeUnit) = true
}

object JavaFXExecutorService extends GUIExecutorService {
  override def execute(command: Runnable) = Platform.runLater(command)
}

class JavaFXEventThreadExecutorServiceConfigurator(config: Config, prerequisites: DispatcherPrerequisites) extends ExecutorServiceConfigurator(config, prerequisites) {
  private val f = new ExecutorServiceFactory {
    def createExecutorService: ExecutorService = JavaFXExecutorService
  }

  def createExecutorServiceFactory(id: String, threadFactory: ThreadFactory): ExecutorServiceFactory = f
}
