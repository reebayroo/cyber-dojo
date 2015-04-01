package bowling

object FrameStatus extends Enumeration {
   val  Normal, Strike, Spare = Value
}
object Game {
    def apply(players:String*):Game=new Game(
        players.zipWithIndex.map( (t) => new Player(t._1, t._2+1)).toList)


}

class Game(val players:List[Player]){

    var currentPlayer = players(0)
    var queue = players.toList
    var play = 0
    private def switchPlayer(){
        queue = queue.tail ::: queue.head :: Nil
        currentPlayer = queue.head
        play = 0
    }
    def newTry(pins:Int)={
        play += 1

        if (play == 2) switchPlayer
        if (pins == 10) switchPlayer


    }
    def score():Int = {
        0
    }
}
case class Player(val name:String, val index:Int)
