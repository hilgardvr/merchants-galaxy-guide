package line

import commodity.CommodityService
import galactic.GalacticService

class CommodityRequest(tokens: List[String]) extends Line {
  override def handle(): Option[String] = {
    val commodity = tokens(tokens.length - 2)
    val commodityPrice = CommodityService.getCommodityPrice(commodity)
    val galacticAmounts = tokens.slice(4, tokens.length - 2)
    GalacticService.arabicFromGalactic(galacticAmounts) match {
      case Some(arabic) if commodityPrice.isDefined => {
        val price = arabic * commodityPrice.get
        Option(s"${galacticAmounts.mkString(" ")} $commodity is ${price.round} Credits")
      }
      case _ => Option(unknownRequest())
    }
  }
}

object CommodityRequest {
  private val CREDITS_REQUEST = "how many Credits is"

  def isA(tokens: List[String]): Boolean = {
    tokens.length > 5 && tokens.last == "?" && tokens.slice(0, 4).mkString(" ") == CREDITS_REQUEST
  }
}
