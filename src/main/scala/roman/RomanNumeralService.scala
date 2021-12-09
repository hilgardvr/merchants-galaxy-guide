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

  def get(roman: String): Option[Int] = {
    RomanNumerals.get(roman)
  }

  def romanToArabic(roman: List[String]): Int = {
    var sub = 0
    var total = 0
    for (x <- roman.indices) {
      val amount = RomanNumerals.getOrElse(roman(x), throw new Exception(s"Invalid Roman at ${roman(x)}"))
      val isLast = x == roman.length - 1
      val next =
        if (!isLast) RomanNumerals.getOrElse(roman(x + 1), throw new Exception(s"Invalid Roman at ${roman(x+1)}"))
        else 0
      if (next > amount) sub = amount
      else total = total + amount - sub
    }
    total
  }
}
