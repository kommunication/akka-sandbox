package com.komlan.lab.akka
package sandbox

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}


object ActorPath {


    def run(): Unit = {

        val system = ActorSystem("Actor-Path")

        val counter1 = system.actorOf(Props[Counter], "counter")

        println(s"Actor reference fr counter1: ${counter1}")

        val counterSelection1 = system.actorSelection("counter")
        println(s"Actor selection for counter1: ${counterSelection1}")

        counter1 ! PoisonPill

        Thread.sleep(100)

        val counter2 = system.actorOf(Props[Counter], "counter")

        println(s"Actor reference for counter2: ${counter2}")

        val counterSelection2 = system.actorSelection("counter")

        println(s"Actor selection for counter 2: ${counterSelection2}")

        system.terminate()


        println("Running ActorPath")
    }
}
