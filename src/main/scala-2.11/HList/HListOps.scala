package HList

import Nat._

object HListAccessor {

  def head[A, B <: HList](l: A :: B): A = l match {
    case h :: _ => h
  }

  def tail[A, B <: HList](l: A :: B): B = l match {
    case _ :: t => t
  }
}

object HListAppend {

  trait HAppend[A <: HList, B <: HList, Out <: HList]

  object HAppend {
    implicit def appendHNil[A <: HList] = new HAppend[HNil, A, A] {}

    implicit def appendHList[A <: HList, B <: HList, C <: HList, X]
    (implicit append: HAppend[A, B, C]) = new HAppend[X :: A, B, X :: C] {}

    def apply[A <: HList, B <: HList, C <: HList]
    (implicit append: HAppend[A, B, C]) = append
  }
}

object HListNth {

  trait HNth[A <: HList, B <: Nat, Out]

  implicit def nthZero[A, B <: HList] = new HNth[A :: B, Zero, A] {}

  implicit def nthN[A <: HList, B <: Nat, C, D](implicit i: HNth[A, B, C]) =
    new HNth[D :: A, Succ[B], C] {}

  def apply[A <: HList, B <: Nat, C](implicit nth: HNth[A, B, C]) = nth
}