package net.study.functionaly.lesson10_OOP_classes.hometask.oop.mappers

import net.study.functional.lesson10_OOP_classes.hometask.oop.dto.SignUpDto
import net.study.functional.lesson10_OOP_classes.hometask.oop.errors
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.SignUpRequest

trait SignUpRequestMapper extends Mappers {

  override def map(request: SignUpRequest)(implicit defaultMapper: SignUpRequest => SignUpDto): Either[errors.Error, SignUpDto] = {
    val dto: SignUpDto = defaultMapper(request)
    Right(dto)
  }

 // override def hash(in: String): String = super.hash(in)
}
