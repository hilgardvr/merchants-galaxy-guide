package commodity

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CommodityServiceSpec extends AnyFlatSpec with Matchers {
  "CommodityService" should "set and get commodity prices" in {
    CommodityService.setCommodityPrice("Gold", 10.2345)
    CommodityService.getCommodityPrice("Gold") shouldEqual 10.2345
    CommodityService.setCommodityPrice("Silver", 55)
    CommodityService.getCommodityPrice("Silver") shouldEqual 55
  }
}
