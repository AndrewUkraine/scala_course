package net.study.functionaly.lesson10_OOP_classes.hometask.oop

import net.study.functional.lesson10_OOP_classes.hometask.oop.errors.ValidationError
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.SignUpRequest
import net.study.functional.lesson10_OOP_classes.hometask.oop.validator.{RequestValidator, ValidatorUtil}

import java.util.Date
import scala.language.postfixOps

object HomeTask extends App {


  /*
    Using all this infrustructure and fraims implement handler for SignUp operation
    1) validation, using rules declare in scala doc above SignUpRequest. All validation errors must be gathered together
    with help of ValidationError
    2) implement mapper for converting it to common SignUpDto and hash password
    3) implement processor(only simple stub which immediately returns OK answer)
    Write test for validator and mapper components
   */


  val request = SignUpRequest(
    name = Some("Ivan"),
    surname = Some("Ivanov"),
    login = Some("qwerty"),
    pass = Some("password"),
    msisdn = Some("0976786534")
  )


  val validationResult: Either[ValidationError, SignUpRequest] = ValidatorUtil.validate(request);




}




