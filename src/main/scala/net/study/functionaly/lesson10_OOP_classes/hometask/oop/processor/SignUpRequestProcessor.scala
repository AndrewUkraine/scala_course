package net.study.functionaly.lesson10_OOP_classes.hometask.oop.processor

import net.study.functional.lesson10_OOP_classes.hometask.oop.dto.SignUpDto
import net.study.functional.lesson10_OOP_classes.hometask.oop.errors
import net.study.functional.lesson10_OOP_classes.hometask.oop.response.SignUpResponse

trait SignUpRequestProcessor extends Processor[SignUpDto, SignUpResponse] {
  override def process(in: SignUpDto): Either[errors.Error, SignUpResponse] = {
    try {
      val response = SignUpResponse(OK, in)
      Right(response)
    } catch {
      case e: Exception =>
        Left(errors.InnerError)
    }
  }
}
