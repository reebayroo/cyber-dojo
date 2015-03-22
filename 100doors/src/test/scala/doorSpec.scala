import org.scalatest.FunSuite
import door._
class DoorSpec  extends FunSuite {

    test("Given 100 closed doors, loop and toggle them all, till one is left") {
        var doors=DoorContainer.create(100);
        var actualDoors = DoorContainer.iterateTillOneIsLeft(doors, 1)
        assert(closedDoors(actualDoors)== 90)
    
    }
    test("If I give you n-1, it should toggle only one"){
        var doors=DoorContainer.create(3)
        var actual = DoorContainer.iterateTillOneIsLeft(doors, 3)
        assert(actual(2).closed == false)
        assert(actual(0).closed == true)
        assert(actual(1).closed == true)
    }
    test("Given 5 should return expected result"){
        var doors=DoorContainer.create(5)
        var actual = DoorContainer.iterateTillOneIsLeft(doors, 1)
        assert(actual(0).closed == false)
        assert(actual(1).closed == true)
        assert(actual(2).closed == true)
        assert(actual(3).closed == false)
        assert(actual(4).closed == true)
    }
    def closedDoors(doors:List[Door]):Int={
        return doors.count(( x:Door ) => x.closed)
    }
}
