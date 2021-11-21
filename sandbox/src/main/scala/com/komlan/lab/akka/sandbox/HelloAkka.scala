package com.komlan.lab.akka
package sandbox

import akka.actor.{Actor, ActorSystem, Props}


object HelloAkka {
    // Define actor messages
    final case class WhoToGreet(who: String)

    // define greeter actor
    class Greeter extends Actor {
        def receive : Receive = {
            case WhoToGreet(who) =>
                println(s"Hello, $who")
        }
    }


    def run(): Unit = {
        // Create the hello-actor system
        val system = ActorSystem("hello-actor")

        // Instanciate the hello actor
        val greeter = system.actorOf(Props[Greeter](), "greeter")
        
        // send WhoToGreet message
        greeter ! WhoToGreet("Akka")
    }
}
