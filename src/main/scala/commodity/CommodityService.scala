package commodity

import scala.collection.mutable

object CommodityService {
  private val commodityPrice = mutable.Map[String, Double]()

  def setCommodityPrice(commodity: String, price: Double): Unit = {
    commodityPrice(commodity) = price
  }

  def getCommodityPrice(commodity: String): Double = {
    commodityPrice.get(commodity) match {
      case Some(price) => price
      case _ => throw new Exception(s"No commodity listed as $commodity")
    }
  }

}
