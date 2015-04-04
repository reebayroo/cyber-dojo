package bowling
class Game(val player:String){
    var frames = Frame():: Nil
    def ball(pins:Int):Game ={
        if (frames.head.shallSwitch)
            frames = Frame() :: frames
        frames.head.ball(pins)
        this
    }
    def score():Int = 0
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
    override def toString():String={
        def missed(ball:Int):String = if (ball == 0) "-" else ball.toString
        val missedFirst = missed(first)
        val missedSecond = missed(second)
        if (play==0) ""
        else if (play == 1 && first != 10) missedFirst
        else if (first==10) "X"
        else if (score == 10 && first > 0) s"$missedFirst/"
        else if (score == 10) "-/"
        else s"$missedFirst$missedSecond"
    }
}
