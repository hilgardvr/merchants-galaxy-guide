package parser

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.io.Source

class ParserSpec extends AnyFlatSpec with Matchers {
  private val source = Source.fromString(
    """glob is I
      |prok is V
      |pish is X
      |tegj is L
      |glob glob Silver is 34 Credits
      |glob prok Gold is 57800 Credits
      |pish pish Iron is 3910 Credits
      |how much is pish tegj glob glob ?
      |how many Credits is glob prok Silver ?
      |how many Credits is glob prok Gold ?
      |how many Credits is glob prok Iron ?
      |how much wood could a woodchuck chuck if a woodchuck could chuck wood?""".stripMargin)

  private val result =
    """pish tegj glob glob is 42
      |glob prok Silver is 68 Credits
      |glob prok Gold is 57800 Credits
      |glob prok Iron is 782 Credits
      |I have no idea what you are talking about""".stripMargin

  "Parser" should "return correct result" in {
    Parser.parse(source) shouldEqual result
  }
}
