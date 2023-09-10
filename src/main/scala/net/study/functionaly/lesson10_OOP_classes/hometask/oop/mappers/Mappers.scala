package net.study.functionaly.lesson10_OOP_classes.hometask.oop.mappers

import net.study.functional.lesson10_OOP_classes.hometask.oop.dto.SignUpDto
import net.study.functional.lesson10_OOP_classes.hometask.oop.exception.MapperException
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.SignUpRequest
import net.study.functional.lesson10_OOP_classes.hometask.oop.services.HashService

// here you can assign your implicit mapper function  implement this trait with your logic
trait Mappers extends Mapper[SignUpRequest, SignUpDto] {

  // implement this
  implicit val signUpRequestMapper: SignUpRequest => SignUpDto = { signUpRequest =>
    val name = signUpRequest.name getOrElse MapperException("Name is Empty").errMsg
    val surname = signUpRequest.surname getOrElse MapperException("surname is Empty").errMsg
    val login = signUpRequest.login getOrElse MapperException("login is Empty").errMsg
    val pass = signUpRequest.pass getOrElse MapperException("pass is Empty").errMsg
    val msisdn = signUpRequest.msisdn getOrElse MapperException("msisdn is Empty").errMsg

    val hashedPassword = new HashService hash (pass)

    SignUpDto(name, surname, login, hashedPassword, msisdn)
  }
}