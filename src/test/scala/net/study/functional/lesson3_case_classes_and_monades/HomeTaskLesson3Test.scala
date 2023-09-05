package net.study.functional.lesson3_case_classes_and_monades

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, OptionValues, WordSpec}

import scala.language.postfixOps

@RunWith(classOf[JUnitRunner])
class HomeTaskLesson3Test extends WordSpec

  with OptionValues
  with Matchers {

  import net.study.functionaly.lesson3_case_classes_and_monades.HomeTask._


  "MethodsForTesting" when {

    "filterObject" should {

      "value is less then max value" in {
        filterObject(99) shouldEqual false
      }

      "value is more then max value" in {
        filterObject(101) shouldEqual true
      }

      "value is equals to max value" in {
        filterObject(100) shouldEqual false
      }
    }


    "computeTaxSum" should {

      "value is less then max value" in {
        computeTaxSum(10) shouldEqual Some(0)
      }

      "value is dsfsd then max value" in {
        computeTaxSum(1000) shouldEqual Some(200)
      }
    }


    "correctPayment" must {

      "value is efe then max value" in {
        correctPayment(1, Some(300)) shouldEqual Some(300)
      }
      "value is wrwew wewe ww value" in {
        correctPayment(1, None) shouldEqual None
      }
      "value is wrwew asdqeq ww value" in {
        correctPayment(5, Option.empty) shouldEqual Some(500)
      }
    }

    "correctTax" must {

      "value tax is 20" in {
        correctTax(Some(20), 1000) shouldEqual Some(20)
      }

      "value tax is empty" in {
        correctTax(Option.empty, 1000) shouldEqual Some(200)
      }
    }


    "correctDesc" must {

      "present payment type" in {
        correctDesc(Some("payment for Iphone 15")) shouldEqual Some("payment for Iphone 15")
      }

      "absent payment type. Should be default payment type" in {
        correctDesc(Option.empty) shouldEqual Some("technical")
      }
    }

    "correctPaymentInfo" must {

      "payment correct" in {
        correctPaymentInfo(PaymentInfoDto(1, Some("customerA"), Some(1500), None, Some("payment for Iphone 15"))) shouldEqual Some(PaymentInfo(1, 1500, 300, "payment for Iphone 15"))
      }
    }
  }

}



