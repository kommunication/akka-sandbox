package com.komlan.lab.akka
package sandbox

import akka.actor.{Actor, ActorIdentity, ActorRef, ActorSystem, Identify, Props}

class Watcher extends Actor {
    var counterRef : ActorRef = _

    val selection = context.actorSelection("/user/counter")

    selection ! Identify(None)

    def receive: Receive = {
        case ActorIdentity(_, Some(ref)) =>
            println(s"Actor reference for counter is $ref")
            counterRef = ref
        case ActorIdentity(_, None) =>
            println("Actor selection for conter is not live")
    }
}

object Watcher {
    def run(): Unit = {
        val system = ActorSystem("watch-actor-selection")

        val counter = system.actorOf(Props[Counter], "counter")

        val watcher = system.actorOf(Props[Watcher], "watcher")


        system.terminate()


    }
}
