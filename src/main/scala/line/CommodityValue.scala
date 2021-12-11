package line

import commodity.CommodityService
import galactic.GalacticService.arabicFromGalactic

class CommodityValue(tokens: List[String]) extends Line {
  override def handle(): Option[String] = {
    val commodity = tokens(tokens.length - 4)
    arabicFromGalactic(tokens.slice(0, tokens.length - 4)) match {
      case Some(arabic) => {
        val credits = tokens(tokens.length - 2).toDouble
        val price = credits / arabic
        CommodityService.setCommodityPrice(commodity, price)
        None
      }
      case None => Option(unknownRequest())
    }
  }
}

object CommodityValue {
  def isA(tokens: List[String]): Boolean = {
    tokens.length > 4 && tokens.last == "Credits" && tokens(tokens.length - 3) == "is"
  }
}
