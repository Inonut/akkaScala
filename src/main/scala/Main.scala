import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinPool
import com.typesafe.config.ConfigFactory
import listener._
import util.BusinessConstant

import scalafx.application.JFXApp

/**
  * Created by Dragos on 07.04.2016.
  */
object Main extends JFXApp{

  val system = ActorSystem("mySystem", ConfigFactory.load)
  val appService = system.actorOf(Props[GeneralService].withRouter(RoundRobinPool(BusinessConstant.defalutPool)))


  appService ! OpenBlockUI

  appService ! CloseBlockUI



/*
  val two: PartialFunction[Int, String] = { case 2 => "two" }

  val three: PartialFunction[Int, String] = { case 3 => "three" }

  val wildcard: PartialFunction[Int, String] = { case _ => "something else" }

  val fct: PartialFunction[String, String] = { case _ => "then" }

  def partial = two orElse three orElse wildcard andThen fct

  println(partial(3))


  def f(x: Int) = ""

  def g(x: String) = "g(" + x + ")"

  val fAndThenG = f _ andThen g _

  println(fAndThenG(5))*/

  //BlockUI.close()

}
