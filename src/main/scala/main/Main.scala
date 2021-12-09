package main

import parser.Parser
import scala.io.Source

object Main extends App {
  private val fileName = if (args.length > 0) args(0) else "testfile"
  try {
    val source = Source.fromFile(fileName)
    Parser.parse(source)
    source.close()
  } catch {
    case e: Exception => println(e)
  }
}
