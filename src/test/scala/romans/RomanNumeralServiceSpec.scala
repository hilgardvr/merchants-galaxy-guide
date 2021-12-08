package romans

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import roman.RomanNumeralService

class RomanNumeralServiceSpec extends AnyFlatSpec with Matchers {
  "Roman numeral map" should "return correct decimal values" in {
    RomanNumeralService.RomanNumerals.get("I") shouldEqual Option(1)
    RomanNumeralService.RomanNumerals.get("V") shouldEqual Option(5)
    RomanNumeralService.RomanNumerals.get("X") shouldEqual Option(10)
    RomanNumeralService.RomanNumerals.get("L") shouldEqual Option(50)
    RomanNumeralService.RomanNumerals.get("C") shouldEqual Option(100)
    RomanNumeralService.RomanNumerals.get("D") shouldEqual Option(500)
    RomanNumeralService.RomanNumerals.get("M") shouldEqual Option(1000)
  }
}