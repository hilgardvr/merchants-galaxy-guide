package main

import parser.Parser
import scala.io.Source

object Main extends App {
  private val fileName = if (args.length > 0) args(0) else "testfile"
  try {
    val bufferedSource = Source.fromFile(fileName)
    Parser.parse(bufferedSource)
    bufferedSource.close()
  } catch {
    case e: Exception => println(e)
  }
}
