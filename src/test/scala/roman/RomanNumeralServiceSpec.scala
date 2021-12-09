package roman

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

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
      RomanNumeralService.romanToArabic(List("V","I")) shouldEqual 6
      RomanNumeralService.romanToArabic(List("I","V")) shouldEqual 4
    RomanNumeralService.romanToArabic(List("X")) shouldEqual 10
    RomanNumeralService.romanToArabic(List("C","D")) shouldEqual 400
    RomanNumeralService.romanToArabic(List("C","D","X")) shouldEqual 410
    RomanNumeralService.romanToArabic(List("D","M","C","D","X","V","I","V","I","I")) shouldEqual 921
    intercept[Exception] {
      RomanNumeralService.romanToArabic(List("I", "I", "I", "I"))
    }
    intercept[Exception] {
      RomanNumeralService.romanToArabic(List("D", "D"))
    }
    }
}