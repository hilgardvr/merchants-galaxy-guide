package line

import galactic.GalacticService
import roman.RomanNumeralService

class TokenValue(tokens: List[String]) extends Line {
  override def handle(): Option[String] = {
    GalacticService.setGalacticToRoman(tokens.head, tokens.last)
    None
  }
}

object TokenValue {
  def isA(tokens: List[String]): Boolean = {
    tokens.length == 3 && tokens(1) == "is" && RomanNumeralService.get(tokens(2)).isDefined
  }
}
