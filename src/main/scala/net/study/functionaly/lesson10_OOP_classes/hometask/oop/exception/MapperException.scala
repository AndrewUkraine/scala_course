package net.study.functionaly.lesson10_OOP_classes.hometask.oop.exception

// Here you can declare your exceptions
case class MapperException(errMsg: String) extends RuntimeException(errMsg)
