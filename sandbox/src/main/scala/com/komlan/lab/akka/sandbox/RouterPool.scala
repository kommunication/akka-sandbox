package com.komlan.lab.akka
package sandbox

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.komlan.lab.akka.sandbox.Worker.Work

class RouterPool extends Actor {

  var routees: List[ActorRef] = _

  override def preStart(): Unit = {
    routees = List.fill(5)(
      context.actorOf(Props[Worker])
    )
  }
  def receive: Receive = {
    case msg: Work =>
      println("I'm the router and I receive a Work message")
      routees(util.Random.nextInt(routees.size)) forward msg
  }

}

object RouterPool {

  def run: Unit = {
    val system = ActorSystem("router")
    val router = system.actorOf(Props[RouterPool])

    router ! Work()
    router ! Work()
    router ! Work()

    Thread.sleep(100)

    system.terminate()
  }

}

class RouterGroup(routees: List[String]) extends Actor {
  override def receive: Receive = {
    case msg: Work =>
      println(s"I'm Router Group and I received Work message")
      context.actorSelection(routees(util.Random.nextInt(routees.size))) forward msg
  }
}

object RouterGroup {

  def run():Unit = {
    val system = ActorSystem("router")

    system.actorOf(Props[Worker], "w1")
    system.actorOf(Props[Worker], "w2")
    system.actorOf(Props[Worker], "w3")
    system.actorOf(Props[Worker], "w4")
    system.actorOf(Props[Worker], "w5")

    val workers: List[String] = List(
      "/user/w1",
      "/user/w2",
      "/user/w3",
      "/user/w4",
      "/user/w5",
    )

    val routerGroup = system.actorOf(Props(classOf[RouterGroup], workers))

    routerGroup ! Work()
    routerGroup ! Work()
    routerGroup ! Work()

    Thread.sleep(100)

    system.terminate()
  }
}
