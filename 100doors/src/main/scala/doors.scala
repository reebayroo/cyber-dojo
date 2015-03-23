package doors

class Door(var closed:Boolean){
    def toogle =  
        this.closed = !this.closed
}

object DoorContainer {

    def create(total:Int):List[Door]=
            (1 to total).map( _ => new Door(true)).toList

    def iterateTillOneIsLeft(doors:List[Door], step:Int):List[Door]={
        if (step > doors.length){
            return doors
        }
        for ( (door, index) <- doors.zipWithIndex if ( index + 1 ) % step == 0) {
            door.toogle
        }
        return iterateTillOneIsLeft(doors, step+1)
    }

}

