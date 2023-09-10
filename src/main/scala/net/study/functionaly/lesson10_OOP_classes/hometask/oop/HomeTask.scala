package net.study.functionaly.lesson10_OOP_classes.hometask.oop

import net.study.functional.lesson10_OOP_classes.{Child, ComputerScience, Programmer}
import net.study.functional.lesson10_OOP_classes.hometask.oop.dto.SignUpDto
import net.study.functional.lesson10_OOP_classes.hometask.oop.handler.RequestHandler
import net.study.functional.lesson10_OOP_classes.hometask.oop.mappers.{Mapper, Mappers}
import net.study.functional.lesson10_OOP_classes.hometask.oop.processor.{OK, Processor, Status}
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.SignUpRequest
import net.study.functional.lesson10_OOP_classes.hometask.oop.response.SignUpResponse
import net.study.functional.lesson10_OOP_classes.hometask.oop.validator.{RequestValidator, ValidatorUtil}

import scala.language.postfixOps

object HomeTask extends App with SignUpRequestHandler{

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
    msisdn = Some("380976786534")
  )

  val res = handle(request)

  res match {
    case Left(error) =>
      println(s"Request handling failed: $error")
    case Right(response) =>
      println(s"Request handled successfully: $response")
  }

}








