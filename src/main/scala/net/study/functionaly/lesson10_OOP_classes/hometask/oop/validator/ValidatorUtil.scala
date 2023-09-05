package net.study.functionaly.lesson10_OOP_classes.hometask.oop.validator

import net.study.functional.lesson10_OOP_classes.hometask.oop.errors.{EmptyStringError, LackMappedParamError, MapperError, ValidationError}
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.SignUpRequest
import net.study.functional.lesson10_OOP_classes.hometask.oop.services.LoginService

// Here you can declare all supplementary method for validation with style below(or use your own signature and return type
// if you want)
object ValidatorUtil extends RequestValidator[SignUpRequest] with LoginService{

  def validateStringEmptyParam(paramName: String, maybeStringEmpty: Option[String]): Either[ValidationError, Unit] = {
    maybeStringEmpty match {
      case Some(value) if value.nonEmpty && value != null => Right(())
      case _ => Left(ValidationError(Map(paramName -> EmptyStringError)))
    }
  }


  override def checkUniqueness(login: String): Boolean = super.checkUniqueness(login)

  def validateLatin(login: Option[String]): Either[ValidationError, Unit] = {
    login match {
      case Some(value) if value.matches("[a-zA-Z]+") => Right(())
      case Some(_) => Left(ValidationError(Map(RequestValidator.Login -> LackMappedParamError)))
    }
  }

  def validateLogin(login: Option[String]): Either[ValidationError, Unit] = {
    for {
      _ <- validateStringEmptyParam(RequestValidator.Login, login)
      _ <- validateMsisdnDig(login)
    } yield ()
  }

  def loginVal(login: Option[String]): Either[ValidationError, Unit] = {
    login match {
      case Some(value) if checkUniqueness(value) => Right(())
      case Some(_) => Left(ValidationError(Map(RequestValidator.Login -> LackMappedParamError)))
    }
  }

  def validateName(name: Option[String]): Either[ValidationError, Unit] = {
    for {
      _ <- validateStringEmptyParam(RequestValidator.Name, name)
      _ <- validateLatin(name)
    } yield ()
  }

  def validateSurName(surname: Option[String]): Either[ValidationError, Unit] = {
    for {
      _ <- validateStringEmptyParam(RequestValidator.Surname, surname)
      _ <- validateLatin(surname)
    } yield ()
  }


  def validateMsisdnDig(msisdn: Option[String]): Either[ValidationError, Unit] = {
    msisdn match {
      case Some(msisdn) if msisdn.matches("\\d{9}|\\d{12}") => Right(())
      case Some(_) => Left(ValidationError(Map(RequestValidator.Msisdn -> MapperError)))
    }
  }

  def validateUnique(value: Option[String]): Either[ValidationError, Unit] = {
    value match {
      case Some(value) if value
      case Some(_) => Left(ValidationError(Map(RequestValidator.Msisdn -> MapperError)))
    }
  }

  def validateMsisdn(msisdn: Option[String]): Either[ValidationError, Unit] = {
    for {
      _ <- validateStringEmptyParam(RequestValidator.Msisdn, msisdn)
      _ <- validateMsisdnDig(msisdn)
    } yield ()
  }

  override def validate(request: SignUpRequest): Either[Error, SignUpRequest] = {

    val nameValidation = validateName(request.name)
    val surnameValidation = validateSurName(request.surname)
    val loginValidation =  validateLogin(request.login)
    val msisdnValidation = validateMsisdn(request.msisdn)

    val combinedValidation = nameValidation + surnameValidation + loginValidation + msisdnValidation

    combinedValidation match {
      case Left(errors) => Left(errors)
      case Right(_) => Right(request)
    }
  }




}




