package net.study.functionaly.lesson10_OOP_classes.hometask.oop

import net.study.functional.lesson10_OOP_classes.hometask.oop.dto.SignUpDto
import net.study.functional.lesson10_OOP_classes.hometask.oop.handler.RequestHandler
import net.study.functional.lesson10_OOP_classes.hometask.oop.mappers.{Mapper, Mappers, SignUpRequestMapper}
import net.study.functional.lesson10_OOP_classes.hometask.oop.processor.SignUpRequestProcessor
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.SignUpRequest
import net.study.functional.lesson10_OOP_classes.hometask.oop.response.SignUpResponse
import net.study.functional.lesson10_OOP_classes.hometask.oop.validator.{RequestValidator, SignUpRequestValidator}

trait SignUpRequestHandler extends RequestHandler[SignUpRequest, SignUpDto, SignUpResponse]
  with SignUpRequestValidator with SignUpRequestMapper with SignUpRequestProcessor