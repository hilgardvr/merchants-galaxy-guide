package parser

object Line extends Enumeration {
  type Line = Value
  val TOKEN_VALUE, COMMODITY_VALUE, TOKEN_REQUEST, COMMODITY_REQUEST, UNKNOWN = Value
}
