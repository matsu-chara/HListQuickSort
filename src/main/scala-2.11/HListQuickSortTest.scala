import HList._
import Nat.Nat._

import scala.reflect.runtime.{universe => ru}
import ru._

object HListQuickSortTest {
  def getType[A : TypeTag](obj:A) : Type = typeOf[A]

  def main(args: Array[String]): Unit = {
    val x = _1
    println("**Natural Number**")
    println(x)
    println(getType(x))
    println()

    val t = _1 :: _0 :: _3 :: _2 :: HNil
    println("**HList**")
    println(t)
    println(getType(t))
    println()

    val lteqs = LTEqs[_1 :: _0 :: _3 :: _2 :: HNil, _2, _1 :: _0 :: _2 :: HNil]
    println("**LessThanEquals**")
    println(lteqs)
    println(getType(lteqs))
    println()

    val sorted = HListQuickSort[_1 :: _0 :: _3 :: _2 :: HNil]
    println("**Sorted**")
    println(sorted)
    println(getType(sorted))
    println()
  }
}
