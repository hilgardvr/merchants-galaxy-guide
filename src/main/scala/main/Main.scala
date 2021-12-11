package main

import parser.Parser
import present.Presenter

import scala.io.Source

object Main extends App {
  private val fileName = if (args.length > 0) args(0) else "testfile"
  try {
    val source = Source.fromFile(fileName)
    val parsed = Parser.parse(source)
    Presenter.present(parsed)
    source.close()
  } catch {
    case e: Exception => println(e)
  }
}
