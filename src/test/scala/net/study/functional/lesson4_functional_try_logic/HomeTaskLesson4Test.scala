package net.study.functional.lesson4_functional_try_logic

import net.study.functionaly.lesson3_case_classes_and_monades.HomeTask.filterObject
import net.study.functionaly.lesson4_functional_try_logic.Lesson4.NetworkException
import net.study.functionaly.lesson6_test_learning.MethodsForTesting.devide
import net.study.functionaly.{usingSource, usingSourceWithFinally}
import org.junit.runner.RunWith
import org.scalatest.{Matchers, OptionValues, WordSpec}
import org.scalatest.junit.JUnitRunner

import scala.io.BufferedSource
import scala.io.Source._
import scala.language.postfixOps
import scala.util.Try

@RunWith(classOf[JUnitRunner])
class HomeTaskLesson4Test extends WordSpec

  with OptionValues
  with Matchers {

  import net.study.functionaly.lesson4_functional_try_logic.HomeTask._



  "MethodsForTesting" when {

    "filterObject" should {

      "value is less then max value" in {
        val catchResult:   NetworkException = intercept[NetworkException]  {
          tryGetSubscribers(isRisky = true, "src/main/resources/lesson4/externalSourceFile.txt") shouldEqual 0
        }
      }
    }
  }

}



