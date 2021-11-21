package com.komlan.lab.akka
package sandbox

import akka.actor.Actor

class Worker extends Actor {
    import Worker._
    
    def receive: Receive = {
        case Work() =>
            println("I'm now doing work")
    }
}

object Worker {
    case class Work()

}
