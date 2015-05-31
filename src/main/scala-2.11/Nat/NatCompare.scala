package Nat

import Nat._

// expression of A < B
trait LT[A <: Nat, B <: Nat]

object LT {
  // こうすると A < B という型表記が可能になる。
  type <[A <: Nat, B <: Nat] = LT[A, B]

  // if true then: 0 < n
  implicit def lt1[B <: Nat] = new <[_0, Succ[B]] {}

  // if A < B then: Succ[A] < (Succ[B]
  implicit def lt2[A <: Nat, B <: Nat](implicit lt: A < B) = new <[Succ[A], Succ[B]] {}

  def apply[A <: Nat, B <: Nat](implicit lt: A < B): LT[A, B] = lt
}

// expression of A <= B
trait LTEq[A <: Nat, B <: Nat]

object LTEq {
  type <=[A <: Nat, B <: Nat] = LTEq[A, B]

  // if true then: 0 <= 0
  implicit def ltEq1 = new <=[_0, _0] {}

  // if true then: 0 <= B
  implicit def ltEq2[B <: Nat] = new <=[_0, Succ[B]] {}

  // if (A <= B) then: (Succ[A] <= Succ[B])
  implicit def ltEq3[A <: Nat, B <: Nat](implicit lteq: A <= B) = new <=[Succ[A], Succ[B]] {}

  def apply[A <: Nat, B <: Nat](implicit lteq: A <= B): LTEq[A, B] = lteq
}
