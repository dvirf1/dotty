import scala.quoted._

object Macros {

  inline def test(): String = ${ testImpl }

  private def testImpl(using qctx: QuoteContext) : Expr[String] = {
    import qctx.tasty._
    val classSym = Type.of[Function1].classSymbol.get
    classSym.classMethod("apply")
    classSym.classMethods
    classSym.method("apply")
    Expr(classSym.methods.map(_.name).sorted.mkString("\n"))
  }

}
