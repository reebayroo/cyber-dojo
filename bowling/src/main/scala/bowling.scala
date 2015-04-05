package bowling
class Game(val player:String){
    var frames = Frame():: Nil
    def ball(pins:Int):Game ={
        if (frames.head.shallSwitch)
            frames = Frame() :: frames
        frames.head.ball(pins)
        this
    }
    def score():Int = {
        val inverted = frames.reverse
        println("score on: " +  inverted.mkString("|"))
        var list = convertToPlays(frames.reverse)
  /*      
        instead of 
        var list = List[Int]::Nil
        frames.foreach( f => list = if (f.strike) 10 :: list
                                     else f.first :: f.second :: list )

    */
        println("scores on: " + list.mkString(", "))
        sumPlays(list)
        // doSum(inverted.head, inverted.tail)
    }
    def sumPlays(plays : List[Int]):Int = plays match {
        case Nil => 0
        case 10::x::y::tail => 10 + x + y + sumPlays(x::y::tail)
        case x::y::z::tail => if (x + y == 10) x + y + z + sumPlays(z::tail) else x + y + sumPlays(z::tail)
        case x::tail => x + sumPlays(tail)
    }
    def convertToPlays(theFrames:List[Frame]):List[Int]= theFrames match {
            case Nil => Nil
            case head::tail => if (head.strike) 10 :: convertToPlays(tail)
                               else head.first :: head.second :: convertToPlays(tail)
    }

    def doSum(head:Frame, tail:List[Frame]):Int=
        if (tail.isEmpty)
            head.score
        else if (head.spare)
            head.score + tail.head.first + doSum(tail.head, tail.tail)
        else if (head.strike && tail.head.strike )
            head.score + tail.head.score + doSum(tail.head, tail.tail)
        else if (head.strike)
            head.score + tail.head.first + tail.head.second +  doSum(tail.head, tail.tail)
        else 
            head.score + doSum(tail.head, tail.tail)

    def frameScore():Int = {
        val score = frames.head.score
        if (score==10) 0 else score
    }
    override def toString():String = {
        frames.reverse.mkString("|")
    }

}
case class Frame(var first:Int=0, var second:Int=0, var play:Int=0){
    def score():Int = first + second
    def ball(pins:Int) {
        play match {
            case 0 => first = pins
            case 1 => second = pins
        }
        play += 1
    }

    def shallSwitch():Boolean=
        (play == 2) || (first == 10)

    def strike():Boolean = (first == 10)
    def spare():Boolean = (score == 10) && !strike
    override def toString():String={
        def missed(ball:Int):String = if (ball == 0) "-" else ball.toString
        val missedFirst = missed(first)
        val missedSecond = missed(second)
        if (play==0) ""
        else if (play == 1 && first != 10) missedFirst
        else if (strike) "X"
        else if (spare && first > 0) s"$missedFirst/"
        else if (spare) "-/"
        else s"$missedFirst$missedSecond"
    }
}
