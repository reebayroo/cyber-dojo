import bowling.Bowling._
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.scalatest.ShouldMatchers

class BowlingSpec extends FunSuite with BeforeAndAfter with ShouldMatchers{

    test("The Score of two simple rolls will be their sum"){
        score(2, 3) should be (5)
        score(3, 4, 5, 1) should be ( 7 + 6 )
    }
    test("Spares should add the next roll"){
       score(4, 6, 6, 1) should be ( (4+ 6 + 6) + 6 + 1) 
    }
    test("Strikes should add following rolls "){
        score(10, 5, 2) should be ( 10 + 7 + 7)
    }
    test("Game X | 5 / = (10 + 5 + 5) + 5 + 5)"){
        score(10, 5, 5) should be (10 + 10 + 10)
    }
    test("A Spare in the end gives you a bonus balls"){
        score( 0, 0,    0, 0,   0, 0,   0, 0,   0, 0,
               0, 0,    0, 0,   0, 0,   0, 0,   7,3, 10 ) should be ( (10 + 10) )
    }

    test("A Strike in the end gives you two bonus balls"){
        score( 0, 0,    0, 0,   0, 0,   0, 0,   0, 0,
               0, 0,    0, 0,   0, 0,   0, 0,   10, 10, 10) should be ( 10 + 10 + 10 )
    }
    test("Game X | X | X "){
        score (10, 10, 10) should be (30 + 20 + 10)
    }
    test("A perfect game "){
        score( 10, 10, 10, 10, 10, 
               10, 10, 10, 10, 10, 10, 10) should be (300)

    }

}
