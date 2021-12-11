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
      RomanNumeralService.romanToArabic(List("I","I")) shouldEqual Some(2)
      RomanNumeralService.romanToArabic(List("V","I")) shouldEqual Some(6)
      RomanNumeralService.romanToArabic(List("I","V")) shouldEqual Some(4)
      RomanNumeralService.romanToArabic(List("X")) shouldEqual Some(10)
      RomanNumeralService.romanToArabic(List("C","D")) shouldEqual Some(400)
      RomanNumeralService.romanToArabic(List("C","D","X")) shouldEqual Some(410)
      RomanNumeralService.romanToArabic(List("M","C","D","X","V","I","V","I","I")) shouldEqual Some(1421)
      RomanNumeralService.romanToArabic(List("M","M","M","D","C","C","X","X","I","V")) shouldEqual Some(3724)
      RomanNumeralService.romanToArabic(List("I", "X")) shouldEqual Some(9)
      RomanNumeralService.romanToArabic(List("C", "D")) shouldEqual Some(400)
      RomanNumeralService.romanToArabic(List("I", "I", "I", "I")) shouldEqual None
      RomanNumeralService.romanToArabic(List("D", "D")) shouldEqual None
      RomanNumeralService.romanToArabic(List("I", "M")) shouldEqual None
      RomanNumeralService.romanToArabic(List("D", "M")) shouldEqual None
    }
}