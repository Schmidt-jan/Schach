package Schach


import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SchachSpec extends AnyWordSpec with Matchers {

  "Schach" should {


    "Create a Chess Board" in {

      val field = Schach.createGameField()

      field should startWith("    a   b   c   d   e   f   g   h\n  ┌───┬───┬───┬───┬───┬───┬───┬───┐")
      field should include("8 │   │   │   │   │   │   │   │   │\n  ├───┼───┼───┼───┼───┼───┼───┼───┤")
      field should include("7 │   │   │   │   │   │   │   │   │\n  ├───┼───┼───┼───┼───┼───┼───┼───┤")
      field should include("6 │   │   │   │   │   │   │   │   │\n  ├───┼───┼───┼───┼───┼───┼───┼───┤")
      field should include("5 │   │   │   │   │   │   │   │   │\n  ├───┼───┼───┼───┼───┼───┼───┼───┤")
      field should include("4 │   │   │   │   │   │   │   │   │\n  ├───┼───┼───┼───┼───┼───┼───┼───┤")
      field should include("3 │   │   │   │   │   │   │   │   │\n  ├───┼───┼───┼───┼───┼───┼───┼───┤")
      field should include("2 │   │   │   │   │   │   │   │   │\n  ├───┼───┼───┼───┼───┼───┼───┼───┤")
      field should endWith("1 │   │   │   │   │   │   │   │   │\n  └───┴───┴───┴───┴───┴───┴───┴───┘")
    }
    "Have a specific size" in {
      val field = Schach.createGameField()
      field should have length 645
    }
  }
}