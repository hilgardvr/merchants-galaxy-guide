package main

import galactic.GalacticService
import roman.RomanNumeralService

import scala.io.Source

object Main extends Greeting with App {
  private val fileName = if (args.length > 0) args(0) else "testfile"

  def lineParser(line: String): Unit = {
    val tokens = line.split(" ").toList
    if (tokens.length == 3 && tokens(1) == "is") {
      GalacticService.setGalacticToRoman(tokens.head, tokens.last)
    } else if (tokens.length > 4 && tokens.last == "Credits" && tokens(tokens.length - 3) == "is") {
      val commodity = tokens(tokens.length - 4)
      val galacticAmounts = tokens.slice(0, tokens.length - 4)
      val roman = galacticAmounts.flatMap(gal => GalacticService.getGalacticToRoman(gal))
      val arabic = RomanNumeralService.romanToArabic(roman)
      println(s"galactic: $galacticAmounts, roman: $roman, arabic: $arabic, commodity: $commodity")
    }
  }

  val bufferedSource = Source.fromFile(fileName)
  for (line <- bufferedSource.getLines()) {
    lineParser(line)
  }
  bufferedSource.close()
}

trait Greeting {
  lazy val greeting: String = "hello"
}
