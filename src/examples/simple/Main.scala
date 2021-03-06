package simple

import windlass.processors._
import windlass.http.{ResponseProcessor, Response}
import windlass.WebApp._
import scala.util.Try

object Main extends App {
  new windlass.WebApp(
    port = Try(sys.env("PORT").toInt).getOrElse(9000),
    beforeAll = SimpleRouter,
    afterAll = Seq(XPoweredBy("Windlass"), UpperCaseOutput)
  ).start
}

/**
 * Example of a simple ResponseProcessor.
 * Turns all response to upper case.
 */
object UpperCaseOutput extends ResponseProcessor {
  def apply(resp: Response) = resp.body(resp.body.toUpperCase)
}