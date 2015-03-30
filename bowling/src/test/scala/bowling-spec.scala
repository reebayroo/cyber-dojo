import bowling._
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class BowlingSpec extends FunSuite with BeforeAndAfter {

    var game: Game  = _
    before {
         game = Game(2)
    }
    test("A bowling game will have a predefinide number of players"){
        assert(game.players == 2)
    }
    
    test("a game will have frames"){
        var frame = game.newTurn()
        assert(frame.player == 0)
    }

    test("Every frame will have the appropriate player"){
        var frame = game.newTurn()
        assert(frame.player == 0)
        var frame2 = game.newTurn()
        assert(frame2.player == 1)
        var frame3 = game.newTurn()
        assert(frame3.player == 0)
    }
    test("Every frame will have two tries, with the score being the sum"){
        var frame = game.newTurn()
        frame.newTry(3)
        frame.newTry(2)
        assert(frame.score == 5)
    }
    test("A frame will have one try if the first is a strike"){
        var frame = game.newTurn()
        frame.newTry(10)
        intercept[IllegalStateException]{
            frame.newTry(1)
        }
    }
    test("When a strike happens the frame is over"){
        var frame = game.newTurn()
        frame.newTry(10)
        assert(frame.over == true)
    }

}
