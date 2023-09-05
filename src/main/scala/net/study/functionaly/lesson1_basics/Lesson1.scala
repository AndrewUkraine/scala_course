package net.study.functionaly.lesson1_basics

object Lesson1 {

  def main(args: Array[String]) = {

    //What is dif between Function and Method ? Method is Object and create each time when call! Function only once.

    //Expression don't return, Statement - return values

    val myStringFinal = "Hi" + 1 ; //in Java it is FINAL. We can't change.
    var myString = "Hi" + 2 ; //in Java it is just variable. We can change.

    val x = 1   //immutable
    var y = 0   //mutable

    def sum(int: Int,  int2: Int) = int + int2

    val sumFunc: (Int, Int) => Int = (int: Int,  int2: Int) => int + int2

    println(myString)
    println(sumFunc)

    val range = 1 to 10

    //val x = if (a < b) a else b

    val i = 65;

    val result = i match {
      case 1 => "one"
      case 2 => "two"
      case _ => "not 1 or 2"
    }

    println(result)

    println(getClassAsString(6556));

    for (arg <- args) println(arg)

    /*try {
      writeToFile(text)
    } catch {
      case fnfe: FileNotFoundException => println(fnfe)
      case ioe: IOException => println(ioe)
    }*/

    val qw = for (i <- 1 to 5) yield i * 2
   println(qw)  //Vector(2, 4, 6, 8, 10) ?


    //https://docs.scala-lang.org/overviews/scala-book/prelude-taste-of-scala.html#the-scala-repl
  }

  def getClassAsString(x: Any): String = x match {
    case s: String => s + " is a String"
    case i: Int => "Int"
    case f: Float => "Float"
    case l: List[_] => "List"
    //case p: Person => "Person"
    case _ => "Unknown"
  }

  val res: Int = sum(4,5)(10);
  println(res);
  def sum(a: Int, b: Int)(c:Int): Int = a + b + c

  val nums = (1 to 10).toList

 //nums.foreach(println)
  nums.filter(1 < _).foreach(println)


  nums.foldLeft(0)(_ + _)

}
