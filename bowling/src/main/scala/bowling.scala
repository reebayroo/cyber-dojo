package bowling

object Bowling { 
    def score(rolls : Int*):Int = {
        val frames = turnToFrames(rolls.toList,1)
        sum(frames)
    }
    def turnToFrames(rolls: List[Int], rollNum:Int):List[Frame] = rolls match {
        case 10::tail if (rollNum > 10) => BonusStrike() :: turnToFrames(tail, rollNum + 1)
        case 10::tail => Strike() :: turnToFrames(tail, rollNum + 1)
        case x::y::tail if (x + y == 10) => Spare(x, y) :: turnToFrames(tail, rollNum + 1)
        case x::y::tail => Normal(x, y) :: turnToFrames(tail, rollNum + 1)
        case Nil => Nil
    }
    def sum(frames:List[Frame]):Int = 
        frames match {
            case BonusStrike(_, _)::tail=> sum(tail) 
            case Spare(x, y)::f::tail => x + y + f.x + sum(f::tail)
            case Strike(_, _)::f::g::tail=> 10 + f.score + g.score + sum(f::g::tail) 
            case Strike(_, _)::f::tail=> 10 + f.score + sum(f::tail) 
            case f::tail => f.score + sum(tail)
            case Nil => 0
        } 
}
trait Frame {
    val score:Int
    val x:Int
    val y:Int
}

case class Normal(override val x:Int ,override val y:Int) extends Frame {
    override val score = x + y
}
case class Spare(override val x:Int ,override val y:Int) extends Frame {
    override val score = x + y
}

case class Strike(override val x:Int=10, override val y:Int=0) extends Frame{
    override val score = 10
}
case class BonusStrike(override val x:Int=10, override val y:Int=0) extends Frame {
    override val score = 10
}
