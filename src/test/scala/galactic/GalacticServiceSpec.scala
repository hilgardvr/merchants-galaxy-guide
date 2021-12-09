package galactic

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GalacticServiceSpec extends AnyFlatSpec with Matchers {
  "GalacticService" should "map galactic units to roman" in {
    GalacticService.setGalacticToRoman("glob", "I")
    GalacticService.getGalacticToRoman("glob") shouldBe Option("I")
    GalacticService.setGalacticToRoman("pleb", "C")
    GalacticService.getGalacticToRoman("pleb") shouldBe Option("C")
  }
  "GalacticService" should "not return invalid roman numerals" in {
    GalacticService.setGalacticToRoman("soop", "T")
    GalacticService.getGalacticToRoman("soop") shouldBe None
  }
  "GalacticService" should "not return unset galactic units" in {
    GalacticService.getGalacticToRoman("xyz") shouldBe None
  }
}
