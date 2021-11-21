package com.komlan.lab.akka.sandbox

import akka.actor.Actor

class Counter extends Actor {
  var count: Int = 0
  def receive: Receive = {
    case _ =>
      count += 1
      println(s"Current count is $count")
  }
}
