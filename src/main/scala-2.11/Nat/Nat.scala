package Nat

sealed trait Nat

sealed trait Zero extends Nat {
  override def toString = "0"
}

case class Succ[A <: Nat](n: A) extends Nat {
  override def toString = s"${Integer.parseInt(n.toString) + 1}"
}

object Nat {
  // types
  type _0 = Zero
  type _1 = Succ[_0]
  type _2 = Succ[_1]
  type _3 = Succ[_2]
  type _4 = Succ[_3]
  type _5 = Succ[_4]

  // values
  val _0 = new Zero {}
  val _1 = Succ(_0)
  val _2 = Succ(_1)
  val _3 = Succ(_2)
  val _4 = Succ(_3)
  val _5 = Succ(_4)

  def succ[A <: Nat](n: A): Succ[A] = Succ(n)

  def pred[A <: Nat](n: Succ[A]): A = n match {
    case Succ(m) => m
  }
}
