package listener

import akka.actor.Props
import akka.routing.RoundRobinPool
import util.BusinessConstant

/**
  * Created by Dragos on 08.04.2016.
  */
class GeneralService extends Service{

  listeners add context.actorOf(Props[LogService].withRouter(RoundRobinPool(BusinessConstant.defalutPool)))
  listeners add context.actorOf(Props[BlockUIService].withRouter(RoundRobinPool(BusinessConstant.defalutPool)).withDispatcher("javafx-dispatcher"))

  override val customRecive: Receive = {
    case OpenBlockUI =>
      gossip(OpenBlockUI)
    case CloseBlockUI =>
      gossip(CloseBlockUI)
  }
}
