package com.komlan.lab.akka.sandbox

import akka.actor.{ActorSystem, Props}
import akka.routing.FromConfig
import com.komlan.lab.akka.sandbox.Worker.Work

object RandomRouter {

  def run(): Unit = {

    val system = ActorSystem("Random-Router")

    val routerPool = system.actorOf(FromConfig.props(Props[Worker]), "random-router-pool")

    routerPool ! Work()
    routerPool ! Work()
    routerPool ! Work()
    routerPool ! Work()

    Thread.sleep(100)

    system.terminate()
  }

}
