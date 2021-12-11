package commodity

import scala.collection.mutable

object CommodityService {
  private val commodityPrice = mutable.Map[String, Double]()

  def setCommodityPrice(commodity: String, price: Double): Unit = {
    commodityPrice(commodity) = price
  }

  def getCommodityPrice(commodity: String): Option[Double] = {
    commodityPrice.get(commodity)
  }

}
