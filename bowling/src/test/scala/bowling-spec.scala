import bowling._
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class BowlingSpec extends FunSuite with BeforeAndAfter {

    var game : Game = _
    before {
        game = new Game("John")
    }

    test("A frame score will be the sum of the tries"){

        game.ball(3)
        game.ball(3)
        assert(game.frameScore == 6)
    }
    test("A frame score will be zero if a strike happens"){
        game.ball(10)
        assert(game.frameScore==0)

    }
    test("A frame score will be zero if a spare happens"){
        game.ball(1)
        game.ball(9)
        assert(game.frameScore==0)
    }
    test("A strike should be 'X'"){
        game.ball(10)
        assert(game.toString == "X")
    }
    test("A spare should be the try plus / "){
        game.ball(1)
        game.ball(9)
        assert(game.toString == "1/")
    }
    test("A simple play should display both tries together"){
        assert(game.ball(3).ball(4).toString == "34")
    }
    test("misses should be displayed with - "){
        assert(game.ball(0).ball(4).toString == "-4")
        assert(game.ball(3).ball(0).toString == "-4|3-")
    }
    test("If a try hasnt happened yet, display nothing"){
        assert(game.toString == "")
        assert(game.ball(0).toString == "-")
    }
    test("A set of frames should be separated by '|'"){
        game.ball(2)
        game.ball(3)
        game.ball(4)
        game.ball(6)
        game.ball(10)
        assert(game.toString == "23|4/|X")
    }
}
