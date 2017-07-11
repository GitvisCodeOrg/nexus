package nexus

import nexus.typelevel._
import shapeless._

import scala.language.higherKinds

/**
 * @author Tongfei Chen
 */
trait TensorOpsMixin {

  implicit class TensorOps[T[D, A <: HList], D, A <: HList](val a: T[D, A])(implicit env: Env[T, D]) {

    import env._

    def +(b: T[D, A]): T[D, A] = add(a, b)
    def -(b: T[D, A]): T[D, A] = sub(a, b)
    def |*|(b: T[D, A]): T[D, A] = mul(a, b)
    def |/|(b: T[D, A]): T[D, A] = div(a, b)
    def :*(u: D): T[D, A] = scale(a, u)
    def :*(u: Float): T[D, A] = scale(a, fromFloat(u))
    def :*(u: Double): T[D, A] = scale(a, fromDouble(u))

    def ⋈[B <: HList, C <: HList](b: T[D, B])(implicit env: Env[T, D], sd: SymDiff.Aux[A, B, C]): T[D, C] = tMul(a, b)

    def unary_- : T[D, A] = neg(a)


  }

}
