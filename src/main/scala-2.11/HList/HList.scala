package HList

sealed trait HList

final case class ::[+H, +T <: HList](h: H, t: T) extends HList {
  def ::[A](x: A): A :: H :: T = new ::(x, this)

  override def toString = s"$h :: ${t.toString}"

  def head = HListAccessor.head(this)

  def tail = HListAccessor.tail(this)
}

sealed trait HNil extends HList {
  def ::[A](x: A): A :: HNil = new ::(x, this)

  override def toString = "HNil"

  def head = this
}

case object HNil extends HNil
