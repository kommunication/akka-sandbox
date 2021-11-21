package com.komlan.lab.akka
package sandbox

object Main extends App {
  println("─" * 50)

  // // 1- Hello World
  // HelloAkka.run()

  // 2- Actor path, actor ref, actor selection
  // ActorPath.run()
  Watcher.run()
  
  println("─" * 50)
}
