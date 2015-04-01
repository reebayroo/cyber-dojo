import bowling._
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class BowlingSpec extends FunSuite with BeforeAndAfter {

    var john = Player("john", 1)
    var smith = Player("smith", 2)
    var game: Game  = _
    before {
         game = Game("john", "smith")
    }
    test("A bowling game will have a predefinide number of players"){
        assert(game.players == List(john, smith))
    }
    
    test("Every game will have the appropriate player"){
        assert(game.currentPlayer == john)
    }

    test("Once a player plays twice, the game switches to the next player"){
        game.newTry(2)
        game.newTry(4)
        assert(game.currentPlayer == smith)
    }
    test("A strike will switch the player"){
        game.newTry(10)
        assert(game.currentPlayer == smith)
    }
    test("A spare will switch the player"){
        game.newTry(1)
        game.newTry(9)
        assert(game.currentPlayer == smith)
    }
}
