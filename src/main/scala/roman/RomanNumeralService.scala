package roman

object RomanNumeralService {
  private val romanNumerals = Map(
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

  private val subtractable = Map(
    "I" -> List("V", "X"),
    "X" -> List("L", "C"),
    "C" -> List("D", "M")
  )

  def get(roman: String): Option[Int] = {
    romanNumerals.get(roman)
  }

  private def validateRoman(roman: List[String]): Unit = {
    for (i <- roman.indices) {
      val max = maxRepeater.getOrElse(roman(i), throw new Exception(s"Invalid roman ${roman(i)}"))
      //check for max repeated values
      if (i < roman.length - max) {
        val subAmounts = roman.slice(i, i + max + 1)
        if (subAmounts.forall(_ == subAmounts.head)) throw new Exception(s"${roman(i)} can only be repeated $max times")
      }
      //check for subtract values
      if (i < roman.length - 1) {
        val current = get(roman(i)).getOrElse(throw new Exception(s"No roman ${roman(i)}"))
        val next = get(roman(i + 1)).getOrElse(throw new Exception(s"No roman ${roman(i)}"))
        if (next > current) {
          subtractable.get(roman(i)) match {
            case Some(lst) => if (!lst.contains(roman(i + 1))) throw new Exception(s"Cannot substract ${roman(i)} from ${roman(i + 1)}")
            case _ => throw new Exception(s"Cannot subtract ${roman(i)} from anything")
          }
        }
      }
    }
  }

  def romanToArabic(roman: List[String]): Int = {
    validateRoman(roman)
    var sub = 0
    var total = 0
    for (x <- roman.indices) {
      val amount = romanNumerals.getOrElse(roman(x), throw new Exception(s"Invalid Roman at ${roman(x)}"))
      val isLast = x == roman.length - 1
      val next =
        if (!isLast) romanNumerals.getOrElse(roman(x + 1), throw new Exception(s"Invalid Roman at ${roman(x+1)}"))
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
