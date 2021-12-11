package line

class UnknownRequest(tokens: List[String]) extends Line {
  override def handle(): Option[String] = Option(unknownRequest())
}
