package galactic

import scala.collection.mutable
import roman.RomanNumeralService

object GalacticService {
  private val galacticToRoman = mutable.Map[String, String]()

  def setGalacticToRoman(galacticUnit: String, roman: String): Unit = {
    RomanNumeralService.get(roman) match {
      case Some(_) => galacticToRoman(galacticUnit) = roman
      case _ =>
    }
  }

  def getGalacticToRoman(galacticUnit: String): Option[String] = {
    galacticToRoman.get(galacticUnit)
  }

  def arabicFromGalactic(galacticTokens: List[String]): Option[Int] = {
    val romanTokens = galacticTokens.map(token => getGalacticToRoman(token))
    if (romanTokens.forall(_.isDefined)) {
      RomanNumeralService.romanToArabic(romanTokens.flatten)
    } else {
      None
    }
  }

}
