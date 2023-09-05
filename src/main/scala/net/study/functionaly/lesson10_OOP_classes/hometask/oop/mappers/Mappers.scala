package net.study.functionaly.lesson10_OOP_classes.hometask.oop.mappers

import net.study.functional.lesson10_OOP_classes.hometask.oop.dto.SignUpDto
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.SignUpRequest
import net.study.functional.lesson10_OOP_classes.hometask.oop.services.HashService

// here you can assign your implicit mapper function  implement this trait with your logic
trait Mappers extends  HashService {

  // implement this
  implicit val signUpRequestMapper: SignUpRequest => SignUpDto = { signUpRequest =>
    val name = signUpRequest.name.getOrElse("")
    val surname = signUpRequest.surname.getOrElse("")
    val login = signUpRequest.login.getOrElse("")
    val pass = signUpRequest.pass.getOrElse("")
    val msisdn = signUpRequest.msisdn.getOrElse("")

    val hashedPassword = hash(pass)

    SignUpDto(name, surname, login, hashedPassword, msisdn)
  }

  override def hash(in: String): String = super.hash(in)
}