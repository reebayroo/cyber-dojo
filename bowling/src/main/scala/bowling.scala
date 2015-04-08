package bowling
class Game(val player:String){
    var frames = new Frame():: Nil
        
    def ball(pins:Int):Game ={
        def createFrame():Frame=
            if (frames.size < 10) new Frame()
            else frames.head.plays match {
                case Strike()::Nil => new BonusStrike()
                case _::Spare(x)::Nil => new BonusSpare()
                case _ => new GameOver()
            }

        if (frames.head.shallSwitch) 
            frames = createFrame :: frames

        frames.head.ball(pins)
        this
    }
    def score():Int = {
        val inverted = frames.reverse
        println("score on: " +  inverted.mkString("|"))
        val plays = frames.foldLeft[List[Ball]](Nil){
            (result:List[Ball], frame:Frame) => frame.plays.reverse ::: result
        }
        println(plays.mkString(","))
        return doSum(plays)
    }
    def doSum(plays:List[Ball]):Int = plays match {
        case Miss()::tail => doSum(tail)
        case BonusStrikeBall()::tail => 0 + doSum(tail)
        case Spare(x)::some::tail => 10 + some.pins + doSum(some::tail) 
        case NormalBall(_)::Spare(x)::tail =>  doSum(Spare(x)::tail)
        case NormalBall(x)::tail => x + doSum(tail) 
        case Strike()::some::Nil => 10 + some.pins + doSum(some::Nil)
        case Strike()::some::other::tail => 10 + some.pins + other.pins + doSum(some::other::tail)
        case Nil => 0
    }
    def gameIsOver():Boolean = (
        ( frames.size > 10 && !frames.tail.head.spare) 
    )

    def sumPlays(plays : List[Int]):Int = plays match {
        case Nil => 0
        case 10::x::y::Nil => 10 + sumPlays(x::y::Nil)
        case 10::x::y::tail => 10 + x + y + sumPlays(x::y::tail)
        case  x::y::z::tail => if (x + y == 10) x + y + z + sumPlays(z::tail) else x + y + sumPlays(z::tail)
        case  x::tail => x + sumPlays(tail)
    }

    def frameScore():Int = {

        val score = frames.head.score
        if (score==10) 0 else score
    }
    override def toString():String = {
        frames.reverse.mkString("|")
    }

}
sealed abstract class Ball(val pins:Int, strRep:String)
{
    override def toString():String = strRep
}
case class Strike() extends Ball(10, "X")
case class Spare (override val pins:Int) extends Ball(pins, "/")
case class Miss() extends Ball(0,"-")
case class NormalBall (override val pins:Int) extends Ball(pins, pins.toString)
case class BonusSpareBall (override val pins:Int) extends Ball(pins, pins.toString)
case class BonusStrikeBall () extends Ball(10, "X")

class GameOver extends Frame {
    override def shallSwitch():Boolean=false 
    override def ball(pins:Int) = 
        throw new IllegalStateException("Game is over")
}
class BonusSpare extends Frame {
    override def shallSwitch():Boolean=false
    override def ball(pins:Int) = {
        if (plays.size > 1) 
            throw new IllegalStateException("Game is over")
        plays = BonusSpareBall(pins)::plays
    }
}

class BonusStrike extends Frame {
    override def shallSwitch():Boolean=false
    override def ball(pins:Int) = {
        if (plays.size > 1) 
            throw new IllegalStateException("Game is over")
        plays = BonusStrikeBall()::plays
    }
}

class Frame(){
    var plays: List[Ball]=Nil

    def score():Int = plays.foldLeft(0){
        _ + _.pins
    }
    def ball(pins:Int) {
        if (pins == 10 && plays.isEmpty)
            plays = Strike()::Nil
        else if (pins ==0)
            plays = Miss()::plays
        else if (plays.nonEmpty && plays.head.pins + pins == 10)
            plays = Spare(pins)::plays
        else 
            plays = NormalBall(pins)::plays
    }

    def shallSwitch():Boolean=
        (plays.size == 2) || (score == 10)


    def strike():Boolean = false
    def spare():Boolean = false
    override def toString():String={
        plays.reverse.mkString
    }
}
