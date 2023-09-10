package net.study.functionaly.lesson10_OOP_classes.hometask.oop.validator

import net.study.functional.lesson10_OOP_classes.hometask.oop.errors.{EmptyStringError, Error, LackMappedParamError, MapperError, ValidationError}
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.SignUpRequest
import net.study.functional.lesson10_OOP_classes.hometask.oop.services.LoginService

import scala.util.matching.Regex

// Here you can declare all supplementary method for validation with style below(or use your own signature and return type
// if you want)
object ValidatorUtil {

  /*trait ValidatorUtil extends RequestValidator[SignUpRequest]{
    override def validate(request: SignUpRequest): Either[Error, SignUpRequest] = ???
  }*/

  val regexpsDigit = "([0-9]+)".r
  val alphabetical: Regex = "([A-Za-z]+)".r
  val whatToMatch = "12"
  val stringable = "abcZ"
  val nonEmptyStringPattern = "\\w+".r



  def validation(paramName: String, maybeStringEmpty: Option[String]): Either[ValidationError, Unit] = {
    maybeStringEmpty match {
      case Some(value) if nonEmptyStringPattern.findFirstIn(value).isDefined => Right(())
      case _ => Left(ValidationError(Map(paramName -> EmptyStringError)))
    }
  }

  def validateStringEmptyParam(paramName: String, maybeStringEmpty: Option[String]): Either[ValidationError, Unit] = {
    maybeStringEmpty match {
      case Some(value) if value.nonEmpty && value != null => Right(())
      case _ => Left(ValidationError(Map(paramName -> EmptyStringError)))
    }
  }

  def validateDigit(paramName: String, maybeStringEmpty: Option[String]): Either[ValidationError, Unit] = {
    maybeStringEmpty match {
      case Some(value) if regexpsDigit.findFirstIn(value).isDefined => Right(())
      case _ => Left(ValidationError(Map(paramName -> EmptyStringError)))
    }
  }

  val loginService = new LoginService()

  def checkUniqueness(login: String): Boolean =  loginService.checkUniqueness(login)

  def validateLatin(login: Option[String]): Either[ValidationError, Unit] = {
    login match {
      case Some(value) if value.matches(alphabetical.regex) => Right(())
      case Some(_) => Left(ValidationError(Map(RequestValidator.Login -> LackMappedParamError)))
    }
  }

  def validateLogin(login: Option[String]): Either[ValidationError, Unit] = {
    for {
      _ <- validateStringEmptyParam(RequestValidator.Login, login)
      //_ <- validateMsisdnDig(login)
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

  /*def validateUnique(value: Option[String]): Either[ValidationError, Unit] = {
    value match {
      case Some(value) if value
      case Some(_) => Left(ValidationError(Map(RequestValidator.Msisdn -> MapperError)))
    }
  }*/

  def validateMsisdn(msisdn: Option[String]): Either[ValidationError, Unit] = {
    for {
      _ <- validateStringEmptyParam(RequestValidator.Msisdn, msisdn)
     // _ <- validateMsisdnDig(msisdn)
    } yield ()
  }


}




