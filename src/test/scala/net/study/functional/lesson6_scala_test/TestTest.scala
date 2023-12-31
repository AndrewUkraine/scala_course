package net.study.functional.lesson6_scala_test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, OptionValues, WordSpec}

@RunWith(classOf[JUnitRunner])
class TestTest extends WordSpec
  // with MockFactory
  with OptionValues
  with Matchers {

  import net.study.functionaly.lesson6_test_learning.MethodsForTesting._

  // Unit of testing
  "MethodsForTesting" when {
    // method of testing
    "sum" should {
      // behavior assertion
      "calculate right positive result" in {
        sum(1, 2) shouldEqual 3
      }

      "calculate right negative result" in {
        sum(-1, -2) shouldEqual -3
      }
    }

    "devide" should {
      "calculate right division result" in {
        devide(9, 3) shouldEqual 3
      }

      "fail with ArithmeticException with interception" in {

        val catchResult: ArithmeticException = intercept[ArithmeticException] {
          devide(3, 0) shouldEqual 0
        }
        catchResult.getMessage shouldEqual "/ by zero"
      }

      "fail with ArithmeticException with assertion" in {
        assertThrows[ArithmeticException] {
          devide(3, 0) shouldEqual 0
        }
      }
    }
  }
}