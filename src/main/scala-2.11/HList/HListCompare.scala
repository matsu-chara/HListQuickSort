package HList

import Nat.LT.<
import Nat.LTEq.<=
import Nat._

/**
 * HとAを受け取る。 フィルターされたA以下の型はOutに入る
 * @tparam H フィルターの対象となるHList
 * @tparam A フィルター基準値
 */
trait LTEqs[H <: HList, A <: Nat, Out <: HList]

object LTEqs {

  // if true then: LtEqs(HNil, A) should_be HNil
  implicit def hNilLTEqs[A <: Nat] = new LTEqs[HNil, A, HNil] {}

  // Aは基準値。 H, Tは対象HListのhead, tail
  // headがAより小さかったら結果にheadを加える。
  // if (LtEqsLower(T, A) is defined_and_result_is lts) and (H <= A) then:
  //   LtEqsLower(H :: T, A) should_be H :: lts.Out
  implicit def hlistLTEqsLower[A <: Nat, H <: Nat, T <: HList, LtsOut <: HList](implicit lts: LTEqs[T, A, LtsOut], l: H <= A) =
    new LTEqs[H :: T, A, H :: LtsOut] {}

  // Aは基準値。 H, Tは対象HListのhead, tail
  // headが基準値より大きかったらheadを結果に加えない(無視する)。
  // if (LtEqsGreater(T, A) is defined_and_result_is lts) and (A < H) then:
  //   LtEqsLower(H :: T) should_be lts.Out
  implicit def hlistLTEqsGreater[A <: Nat, H <: Nat, T <: HList, LtsOut <: HList](implicit lts: LTEqs[T, A, LtsOut], l: A < H) =
    new LTEqs[H :: T, A, LtsOut] {}

  def apply[H <: HList, A <: Nat, LtsOut <: HList](implicit lts: LTEqs[H, A, LtsOut]) = lts
}

/**
 * HとAを受け取る。 フィルターされたAより大きい型はOutに入る
 * @tparam H フィルターの対象となるHList
 * @tparam A フィルター基準値
 */
trait GTs[H <: HList, A <: Nat, Out <: HList]

object GTs {
  implicit def hnilGTEqs[A <: Nat] = new GTs[HNil, A, HNil] {}

  implicit def hlistGTEqsLower[A <: Nat, H <: Nat, T <: HList, GtsOut <: HList](implicit gts: GTs[T, A, GtsOut], l: A < H) =
    new GTs[H :: T, A, H :: GtsOut] {}

  implicit def hlistGTEqsGreater[A <: Nat, H <: Nat, T <: HList, GtsOut <: HList](implicit gts: GTs[T, A, GtsOut], l: H <= A) =
    new GTs[H :: T, A, GtsOut] {}

  def apply[H <: HList, A <: Nat, GtsOut <: HList](implicit gts: GTs[H, A, GtsOut]) = gts
}
