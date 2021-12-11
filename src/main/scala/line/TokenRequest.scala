package line

import galactic.GalacticService.arabicFromGalactic

class TokenRequest(tokens: List[String]) extends Line {
  override def handle(): Option[String] = {
    val galacticAmounts = tokens.slice(3, tokens.length - 1)
    arabicFromGalactic(galacticAmounts) match {
      case Some(arabic) => Option(s"${galacticAmounts.mkString(" ")} is $arabic")
      case None => Option(unknownRequest())
    }
  }
}

object TokenRequest {
  private val TOKENS_REQUEST = "how much is"

  def isA(tokens: List[String]): Boolean = {
    tokens.length > 4 && tokens.last == "?" && tokens.slice(0, 3).mkString(" ") == TOKENS_REQUEST
  }
}
