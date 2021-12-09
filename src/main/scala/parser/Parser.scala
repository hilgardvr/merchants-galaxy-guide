package parser

import commodity.CommodityService
import galactic.GalacticService
import roman.RomanNumeralService

import scala.io.{BufferedSource, Source}

object Parser {

  private val TOKENS_REQUEST = "how much is"
  private val CREDITS_REQUEST = "how many Credits is"
  private val UNKNOWN_REQUEST = "I have not idea what you are talking about"

  private def arabicFromGalactic(galacticTokens: List[String]): Int = {
    RomanNumeralService.romanToArabic(
      galacticTokens.flatMap(token => GalacticService.getGalacticToRoman(token))
    )
  }

  private def toInt(str: String): Option[Int] = {
    try {
      Option(str.toInt)
    } catch {
      case e: NumberFormatException => None
    }
  }

  private def isGalacticTokenValue(tokens: List[String]): Boolean = {
    tokens.length == 3 && tokens(1) == "is" && RomanNumeralService.get(tokens(2)).isDefined
  }

  private def isCommodityValue(tokens: List[String]): Boolean = {
    tokens.length > 4 && tokens.last == "Credits" && tokens(tokens.length - 3) == "is"
  }

  def lineParser(line: String): Unit = {
    val tokens = line.split(" ").toList
    if (isGalacticTokenValue(tokens)) {
      GalacticService.setGalacticToRoman(tokens.head, tokens.last)
      println(s"set galactic ${tokens.head} to ${tokens.last}")
    } else if (tokens.length > 4 && tokens.last == "Credits" && tokens(tokens.length - 3) == "is") {
      val commodity = tokens(tokens.length - 4)
      val galacticAmounts = tokens.slice(0, tokens.length - 4)
      val roman = galacticAmounts.flatMap(gal => GalacticService.getGalacticToRoman(gal))
      val arabic = RomanNumeralService.romanToArabic(roman)
      val credits = tokens(tokens.length - 2).toInt
      val price = credits / arabic
      CommodityService.setCommodityPrice(commodity, price)
      println(s"galactic: $galacticAmounts, roman: $roman, arabic: $arabic, commodity: $commodity, credits: $credits, price: $price")
    } else if (tokens.length > 4 && tokens.last == "?" && tokens(0) == "how" && tokens(1) =="much" && tokens(2) == "is") {
      val galaticAmounts = tokens.slice(3, tokens.length - 1)
      val roman = galaticAmounts.flatMap(gal => GalacticService.getGalacticToRoman(gal))
      val arabic = RomanNumeralService.romanToArabic(roman)
      println(s"${galaticAmounts.mkString(" ")} is $arabic")
    } else if (tokens.length > 5 && tokens.last == "?" && tokens(0) == "how" && tokens(1) =="many" && tokens(2) == "Credits" && tokens(3) == "is") {
      val commodity = tokens(tokens.length - 2)
      val commodityPrice = CommodityService.getCommodityPrice(commodity)
      val galacticAmounts = tokens.slice(4, tokens.length - 2)
      val roman = galacticAmounts.flatMap(gal => GalacticService.getGalacticToRoman(gal))
      val arabic = RomanNumeralService.romanToArabic(roman)
      val price = arabic * commodityPrice
      println(s"${galacticAmounts.mkString(" ")} $commodity is ${price.round} Credits")
    } else {
      println(UNKNOWN_REQUEST)
    }
  }

  def parse(source: BufferedSource): Unit = {
    for (line <- source.getLines()) {
      lineParser(line)
    }
  }

}
