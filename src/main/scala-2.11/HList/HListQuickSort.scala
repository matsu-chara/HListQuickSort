package HList

import Nat.Nat
import HListAppend._

trait HListQuickSort[L <: HList] {
  type Out <: HList
}

object HListQuickSort {
  def apply[L <: HList](implicit sorted: HListQuickSort[L]): Aux[L, sorted.Out] = sorted

  type Aux[L <: HList, Out0 <: HList] = HListQuickSort[L] {type Out = Out0}

  implicit def hnilSorted: Aux[HNil, HNil] = new HListQuickSort[HNil] {
    type Out = HNil
  }

  implicit def hlistSorted[H <: Nat, T <: HList, lsOut <: HList,
  gsOut <: HList, smOut <: HList, slOut <: HList, prepOut <: HList]
  (implicit
   ls: LTEqs[T, H, lsOut],
   gs: GTs[T, H, gsOut],
   sortedSmaller: HListQuickSort.Aux[lsOut, smOut],
   sortedLarger: HListQuickSort.Aux[gsOut, slOut],
   preps: HAppend[smOut, H :: slOut, prepOut]) =
    new HListQuickSort[H :: T] {
      type Out = prepOut
    }
}
