package roman

object RomanNumeralService {
  private val RomanNumerals = Map(
    "I" -> 1,
    "V" -> 5,
    "X" -> 10,
    //"L" -> 250,
    "L" -> 50,
    "C" -> 100,
    "D" -> 500,
    "M" -> 1000
  )

  private val maxRepeater = Map(
    "I" -> 3,
    "X" -> 3,
    "C" -> 3,
    "M" -> 3,
    "D" -> 1,
    "L" -> 1,
    "V" -> 1
  )

  def get(roman: String): Option[Int] = {
    RomanNumerals.get(roman)
  }

  private def checkValidRoman(roman: List[String]): Unit = {
    for (i <- roman.indices) {
      val max = maxRepeater.getOrElse(roman(i), throw new Exception(s"Invalid roman ${roman(i)}"))
      if (i < roman.length - max) {
        val subAmounts = roman.slice(i, i + max + 1)
        if (subAmounts.forall(_ == subAmounts.head)) throw new Exception(s"${roman(i)} can only be repeated $max times")
      }
    }
  }

  def romanToArabic(roman: List[String]): Int = {
    checkValidRoman(roman)
    var sub = 0
    var total = 0
    for (x <- roman.indices) {
      val amount = RomanNumerals.getOrElse(roman(x), throw new Exception(s"Invalid Roman at ${roman(x)}"))
      val isLast = x == roman.length - 1
      val next =
        if (!isLast) RomanNumerals.getOrElse(roman(x + 1), throw new Exception(s"Invalid Roman at ${roman(x+1)}"))
        else 0
      if (next > amount) {
        sub = amount
      } else {
        total = total + amount - sub
        sub = 0
      }
    }
    total
  }
}
