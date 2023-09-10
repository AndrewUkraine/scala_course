package net.study.functionaly.lesson10_OOP_classes.hometask.oop.validator

import net.study.functional.lesson10_OOP_classes.hometask.oop.errors.{Error, ValidationError}
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.SignUpRequest
import net.study.functional.lesson10_OOP_classes.hometask.oop.validator.ValidatorUtil.{validateLogin, validateMsisdn, validateName, validateSurName}

trait SignUpRequestValidator extends RequestValidator[SignUpRequest]  {
  override def validate(request: SignUpRequest): Either[Error, SignUpRequest] = {

    val nameValidation: Either[ValidationError, Unit] = validateName(request.name)
    val surnameValidation: Either[ValidationError, Unit] = validateSurName(request.surname)
    val loginValidation: Either[ValidationError, Unit] =  validateLogin(request.login)
    val msisdnValidation: Either[ValidationError, Unit] = validateMsisdn(request.msisdn)

    val a: List[Either[ValidationError, Unit]] =  List[Either[ValidationError, Unit]](nameValidation, surnameValidation, loginValidation, msisdnValidation)

    val bre: List[ValidationError] =  a.flatMap(_.swap.toOption)

    bre match {
      case Nil => Right(request)
      case head :: tail => {
        val mergedError = tail.foldLeft(head)(_ + _)
        Left(mergedError)
      }
    }
  }
}
