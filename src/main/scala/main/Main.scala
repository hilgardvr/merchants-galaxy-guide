package main

import line.LineFactory
import present.Presenter

import scala.io.Source

object Main extends App {
  private val fileName = if (args.length > 0) args(0) else "testfile"
  try {
    val source = Source.fromFile(fileName)
    val lines = LineFactory.build(source)
    val processed = lines.map(line => line.handle())
    Presenter.present(processed)
    source.close()
  } catch {
    case e: Exception => println(e)
  }
}
