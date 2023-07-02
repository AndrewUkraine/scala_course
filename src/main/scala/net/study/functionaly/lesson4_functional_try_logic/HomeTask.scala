package net.study.functional.lesson4_functional_try_logic

import net.study.functionaly.lesson4_functional_try_logic.Lesson4.{NetworkException, result}

import scala.collection.mutable.ListBuffer
import scala.io.Source._
import scala.language.postfixOps
import scala.util.Try

object HomeTask extends App {

  // 1) try to read file from external system over network (use method getFile with two columns: 1) msisdn, subscriber type)
  // and don't forget to close resource after usage!!!!!!

  // 2) try to enrich get Data using main source getDataFromMainSource

  // 3) if fail to execute step 2) go to alternative source and try once more ( use getDataFromAlternativeSource)

  // 4) if success to do so, try to send to 3-d party system all list

  // 5) Implement enrichAndSend method with proper Left(Error) type or Rigiht[Int] Quantity of msisdns send to our third party system

  // Conditions:
  // use only Try Monad to resolve all problems with exception handling
  // You can use any additional custom functions / methods
  // Don't use method Try monad methods as get, getOrElse, isSuccess, isFailure !!!!!

  /// ===============help code ======================

  trait Error

  case object NetworkError extends Error // if sftp server not available

  case object SourceTemporaryUnavailableError extends Error // if main source main source unavailable

  case object AllSourceTemporaryUnavailableError extends Error //if all source were unavailable

  case object ThirdPartySystemError extends Error //if 3-d party system error

  case class TemporaryUnavailableException(string: String) extends Exception

  case class ThirdPartySystemException(string: String) extends Exception

  case class SubscriberInfo(msisdn: String, subscriberType: Int, isActive: Boolean)

  val fileSource = "src/main/resources/lesson4/externalSourceFile.txt"

  // do not change this methods !!!!
  @throws[NetworkException]
  def getFile(isRisky: Boolean, source: String) = if (isRisky) throw NetworkException("SFTP server network exception") else fromFile(source)

  @throws[TemporaryUnavailableException]
  def getActiveData(isRisky: Boolean, msisdns: Seq[String]) = if (isRisky) throw TemporaryUnavailableException("Temporary Unavailable Exception") else {
    msisdns.map(m => (m, if (m.toInt % 2 == 0) 1 else 0))
  }

  @throws[TemporaryUnavailableException]
  def getDataFromMainSource(isRisky: Boolean, msisdns: Seq[String]) = getActiveData(isRisky, msisdns)

  @throws[TemporaryUnavailableException]
  def getDataFromAlternativeSource(isRisky: Boolean, msisdns: Seq[String]) = getActiveData(isRisky, msisdns)

  def sendToProvider(isRisky: Boolean, msisdns: Seq[SubscriberInfo]): Unit =
    if (isRisky) throw ThirdPartySystemException("third party system exception") else msisdns.foreach(m => s"Sent $m")


  val convertToSubscriber = (line: String) => Option(line.split(";")).filter(_.length == 2) map {
    case Array(key, value) => SubscriberInfo(key.trim, value.trim.toInt, isActive = false)
  } orElse Some(throw new Exception())


  def getListSubscribers(isRisky: Boolean, fileSource: String): Try[List[SubscriberInfo]] = {
    Try(getFile(isRisky, fileSource).getLines().flatMap(a => convertToSubscriber(a)).toList)
  }

  def tryToEnrichData(isRisky: Boolean, isRiskyAlternative: Boolean, msisdns: Seq[String]): Try[Map[String, Int]] = {
    (Try(getDataFromMainSource(isRisky, msisdns)) orElse Try(getDataFromAlternativeSource(isRiskyAlternative, msisdns))) map (_.toMap)
  }

  def convertToSubscriberInfo(enrichData: Map[String, Int]): Try[Seq[SubscriberInfo]] = Try {

    val subscriberInfos = ListBuffer.empty[SubscriberInfo]

    for ((msisdn, subscriberType) <- enrichData) {
      val isActive = if (subscriberType == 0) true else false
      val subscriberInfo: SubscriberInfo = SubscriberInfo(msisdn, subscriberType, isActive)
      subscriberInfos += subscriberInfo
    }
    subscriberInfos
  }


  // implement this one
  def enrichAndSend(getFileIsRisky: Boolean,
                    getDataFromMainSourceIsRisky: Boolean,
                    getDataFromAlternativeSourceIsRisky: Boolean,
                    sendToProviderIsRisky: Boolean,
                    fileSource: String): Either[Error, Int] = {

    val result = for {

      listSubscribers: Seq[SubscriberInfo] <- getListSubscribers(getFileIsRisky, fileSource)
      enrichData: Map[String, Int] <- tryToEnrichData(getDataFromMainSourceIsRisky, getDataFromAlternativeSourceIsRisky, listSubscribers.map(_.msisdn))
      listSubscriberInfo: Seq[SubscriberInfo] <- convertToSubscriberInfo(enrichData)
      send <- sendToProvider(sendToProviderIsRisky, listSubscriberInfo)

    } yield

    result match {
      case Left(error) => Left(error)
      case Right(sendResult) => Right(sendResult)
    }
  }
}


