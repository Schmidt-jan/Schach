package Schach

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    "initialized with any value" should {
      val player = Player("TestName")
      "have value name" in {
        player.name should be("TestName")
      }
      "have a nice String representation" in {
        player.toString should be("TestName")
      }
      "have value isActive" in {
        player.isActive should be(false)
      }

      "have value isCheck" in {
        player.isCheck should be(false)
      }

      "have value isCheckmate" in {
        player.isCheckmate should be(false)
      }
    }
  }
}