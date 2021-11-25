package com.komlan.lab.akka
package sandbox

import akka.actor.Actor

class Worker extends Actor {
    import Worker._
    
    def receive: Receive = {
        case Work() =>
            println(s"I received Work message. My actorRef: ${self}")
    }
}

object Worker {
    case class Work()

}
