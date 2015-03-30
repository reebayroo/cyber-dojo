package bowling

object Game {
    def apply(players:Int):Game=new Game(players)
}

class Game(val players:Int){

    var frames:List[Frame] = Nil
    var currentPlayer = 0

    def newTurn():Frame = {
        val newFrame = new Frame(currentPlayer)
        frames =  newFrame :: frames
        setNextPlayer();
        return newFrame
    }
    private def setNextPlayer(){
        currentPlayer = if (currentPlayer + 1 ==  players) 0 else currentPlayer + 1
    }
}
class Frame(val player:Int){
    var score = 0
    var over = false
    def newTry(pins:Int)={
        if (over){
            throw new IllegalStateException("The frame is over")
        }
        score += pins
        over = score == 10
    }
}
