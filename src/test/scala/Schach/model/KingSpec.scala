package Schach.model

import org.scalatest.DoNotDiscover
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

@DoNotDiscover
class KingSpec extends AnyWordSpec with Matchers {

  "Figure King" should {
    val king = King
    "have a very nice String representation" in {
      king.toString should be ("♔")
    }
  }

}
