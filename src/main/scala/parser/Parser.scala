package parser

import commodity.CommodityService
import galactic.GalacticService
import parser.Line._
import roman.RomanNumeralService

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Parser {

  private val TOKENS_REQUEST = "how much is"
  private val CREDITS_REQUEST = "how many Credits is"
  private val UNKNOWN_REQUEST = "I have no idea what you are talking about"

  private def arabicFromGalactic(galacticTokens: List[String]): Int = {
    RomanNumeralService.romanToArabic(
      galacticTokens.flatMap(token => GalacticService.getGalacticToRoman(token))
    )
  }

  private def isTokenValue(tokens: List[String]): Boolean = {
    tokens.length == 3 && tokens(1) == "is" && RomanNumeralService.get(tokens(2)).isDefined
  }

  private def isCommodityValue(tokens: List[String]): Boolean = {
    tokens.length > 4 && tokens.last == "Credits" && tokens(tokens.length - 3) == "is"
  }

  private def isTokenRequest(tokens: List[String]): Boolean = {
    tokens.length > 4 && tokens.last == "?" && tokens.slice(0, 3).mkString(" ") == TOKENS_REQUEST
  }

  private def isCreditRequest(tokens: List[String]): Boolean = {
    tokens.length > 5 && tokens.last == "?" && tokens.slice(0, 4).mkString(" ") == CREDITS_REQUEST
  }

  private def getLine(tokens: List[String]): Line = {
    if (isTokenValue(tokens)) TOKEN_VALUE
    else if (isCommodityValue(tokens)) COMMODITY_VALUE
    else if (isTokenRequest(tokens)) TOKEN_REQUEST
    else if (isCreditRequest(tokens)) COMMODITY_REQUEST
    else UNKNOWN
  }

  private def handleTokenValue(tokens: List[String]): Option[String] = {
    GalacticService.setGalacticToRoman(tokens.head, tokens.last)
    None
  }

  private def handleCommodityValue(tokens: List[String]): Option[String] = {
    val commodity = tokens(tokens.length - 4)
    val arabic = arabicFromGalactic(tokens.slice(0, tokens.length - 4))
    val credits = tokens(tokens.length - 2).toDouble
    val price = credits / arabic
    CommodityService.setCommodityPrice(commodity, price)
    None
  }

  private def handleTokenRequest(tokens: List[String]): Option[String] = {
    val galaticAmounts = tokens.slice(3, tokens.length - 1)
    val arabic = arabicFromGalactic(galaticAmounts)
    Option(s"${galaticAmounts.mkString(" ")} is $arabic")
  }

  private def handleCommodityRequest(tokens: List[String]): Option[String] = {
    val commodity = tokens(tokens.length - 2)
    val commodityPrice = CommodityService.getCommodityPrice(commodity)
    val galacticAmounts = tokens.slice(4, tokens.length - 2)
    val arabic = arabicFromGalactic(galacticAmounts)
    val price = arabic * commodityPrice
    Option(s"${galacticAmounts.mkString(" ")} $commodity is ${price.round} Credits")
  }

  private def handleUnkownRequest(): Option[String] = {
    Option(UNKNOWN_REQUEST)
  }

  private def lineParser(line: String): Option[String] = {
    val tokens = line.split(" ").toList
    getLine(tokens) match {
      case TOKEN_VALUE => handleTokenValue(tokens)
      case COMMODITY_VALUE => handleCommodityValue(tokens)
      case TOKEN_REQUEST => handleTokenRequest(tokens)
      case COMMODITY_REQUEST => handleCommodityRequest(tokens)
      case _ => handleUnkownRequest()
    }
  }

  def parse(source: Source): String = {
    val str = ListBuffer[Option[String]]()
    for (line <- source.getLines()) {
      str += lineParser(line)
    }
    val res = str.flatten.mkString("\n")
    println(res)
    res
  }

}
