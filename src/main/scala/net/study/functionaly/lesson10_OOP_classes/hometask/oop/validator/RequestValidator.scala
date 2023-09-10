package net.study.functionaly.lesson10_OOP_classes.hometask.oop.validator

import net.study.functional.lesson10_OOP_classes.hometask.oop.dto.SignUpDto
import net.study.functional.lesson10_OOP_classes.hometask.oop.errors.Error
import net.study.functional.lesson10_OOP_classes.hometask.oop.mappers.Mappers
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.SignUpRequest


// here you can implement sub-traits for validation purpose


trait RequestValidator[R]  {

  def validate(request: R): Either[Error, R]

}

object RequestValidator {

   val Name    = "name"
   val Surname = "surname"
   val Login   = "login"
   val pass    = "pass"
   val Msisdn  = "msisdn"

}









