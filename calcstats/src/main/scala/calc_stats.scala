package calc_stats

object CalcStats{

    def evaluate(seq:List[Int]):StatResults = {

        if (seq.isEmpty){
            return StatResults(0, 0, 0, 0)
        }
        else {
            doEval(seq.tail, StatResults(seq.head))
        }
    }

    def doEval(seq:List[Int], currentStat:StatResults):StatResults={
        if (seq.isEmpty){
            currentStat
        }
        else {
            doEval(seq.tail, currentStat.recalc(seq.head))
        }
    }

}

object StatResults {

    def apply(value:Int):StatResults={
        new StatResults(value, value, 1, value, value)
    }
    def apply(min:Int,  max:Int,  length:Int, average:Float):StatResults={
        new StatResults(min, max, length, average, 0 )
    }

}
class StatResults(val min:Int, val max:Int, val length:Int, val average:Float, val total:Int){

    def recalc(newValue:Int):StatResults={
        val new_min = Math.min(newValue, min)
        val new_max = Math.max(newValue, max)
        val new_length = length + 1
        val new_total = total + newValue
        val new_average = ( new_total.toFloat ) / new_length.toFloat
        return new StatResults(new_min, new_max, new_length, new_average, new_total)
    }

    override def equals(other: Any) = other match {
        case that: StatResults => this.min == that.min && this.max == that.max && this.length == that.length && this.average == that.average
        case _ => false 
    }
    override def toString():String = 
        s"\n min $min max $max length $length average $average"
}
