package line

import scala.collection.mutable.ListBuffer
import scala.io.Source

object LineFactory {
  private def lineFactory(line: String): Line = {
    val tokens = line.split(" ").toList
    if (TokenValue.isA(tokens)) new TokenValue(tokens)
    else if (TokenRequest.isA(tokens)) new TokenRequest(tokens)
    else if (CommodityValue.isA(tokens)) new CommodityValue(tokens)
    else if (CommodityRequest.isA(tokens)) new CommodityRequest(tokens)
    else new UnknownRequest(tokens)
  }

  def build(source: Source): List[Line] = {
    val buf = ListBuffer[Line]()
    for (line <- source.getLines()) {
      buf += lineFactory(line)
    }
    buf.toList
  }

}
