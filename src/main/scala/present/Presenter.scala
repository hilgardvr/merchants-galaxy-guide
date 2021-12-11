package present

object Presenter {
  def present(lst: List[Option[String]]): Unit = {
    println(lst.flatten.mkString("\n"))
  }
}
