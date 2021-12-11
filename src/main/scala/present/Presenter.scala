package present

object Presenter {

  private val UNKNOWN_REQUEST = "I have no idea what you are talking about"

  def present(lst: List[Option[String]]): Unit = {
    println(lst.flatten.mkString("\n"))
  }

  def presentCommodityRequest(galacticAmounts: List[String], commodity: String, price: Double): Option[String] = {
    Option(s"${galacticAmounts.mkString(" ")} $commodity is ${price.round} Credits")
  }

  def presentCommodityValue(commodity: String, price: Double): Option[String] = {
    None
  }

  def presentTokenRequest(galacticAmounts: List[String], arabic: Int): Option[String] = {
    Option(s"${galacticAmounts.mkString(" ")} is $arabic")
  }

  def unknownRequest(): Option[String] = {
    Option(UNKNOWN_REQUEST)
  }

}
