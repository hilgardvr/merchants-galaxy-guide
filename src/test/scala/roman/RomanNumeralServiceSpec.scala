package roman

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import roman.RomanNumeralService

class RomanNumeralServiceSpec extends AnyFlatSpec with Matchers {
  "Roman numeral map" should
    "return correct decimal values" in {
      RomanNumeralService.get("I") shouldEqual Option(1)
      RomanNumeralService.get("V") shouldEqual Option(5)
      RomanNumeralService.get("X") shouldEqual Option(10)
      RomanNumeralService.get("L") shouldEqual Option(50)
      RomanNumeralService.get("C") shouldEqual Option(100)
      RomanNumeralService.get("D") shouldEqual Option(500)
      RomanNumeralService.get("M") shouldEqual Option(1000)
    }

  "RomanNumeralService" should
    "convert roman numerals to arabic" in {
      RomanNumeralService.romanToArabic(List("I","I")) shouldEqual 2
    }
}