package line

trait Line {
  def unknownRequest(): String = "I have no idea what you are talking about"
  def handle(): Option[String]
}
