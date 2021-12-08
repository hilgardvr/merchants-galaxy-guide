package main

import scala.io.Source

object Main extends Greeting with App {
  val fileName = if (args.length > 0) args(0) else "testfile"
  for (line <- Source.fromFile(fileName).getLines()) {
    println(line)
  }
}

trait Greeting {
  lazy val greeting: String = "hello"
}
