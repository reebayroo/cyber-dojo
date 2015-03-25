import calc_stats._
import org.scalatest.FunSuite


class CalcStatsSpec extends FunSuite {
    test("Given an empty list, stats should return zeros on everything"){
        val actual= CalcStats.evaluate(List[Int]())
        assert(actual == StatResults(0, 0, 0, 0))
    }

    test("Given a list with one item, stats should return that value for everything"){
        val actual = CalcStats.evaluate(List(17))
        assert(actual == StatResults(17, 17, 1, 17))

    }

    test("Given a list with two items, do some calculation"){
        val actual = CalcStats.evaluate(List(10, 20))
        assert(actual == StatResults(10, 20, 2, 15))

    }
    
    test("Given the original problem 6, 9, 15, -2, 93, 11]"){
        assert(CalcStats.evaluate(List(6, 9, 15, -2, 93, 11))==StatResults(-2, 93, 6, 22.0f))
    }
}
