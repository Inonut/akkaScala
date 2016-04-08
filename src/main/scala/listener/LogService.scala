package listener

/**
  * Created by Dragos on 08.04.2016.
  */
class LogService extends Service{
  override val customRecive: Receive = {
    case x => println(x)
  }
}
