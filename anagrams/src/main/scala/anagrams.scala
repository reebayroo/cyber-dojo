package anagrams
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set

object Anagrams {

    def create(word:String):List[String]={
        val check = Set("")
        val result = new ListBuffer[String]()
        addParts(word.head.toString, word.tail, result, check)
        addParts(word.reverse.head.toString, word.reverse.tail, result, check)
        return result.toList.sorted

    }
    private def addParts(head:String, tail:String, result:ListBuffer[String], check:Set[String]):Unit={
        println (("head", head))
        println (("tail", tail))
        if (tail.isEmpty){
            addIf(head, result, check)
            return
        }

        val newWord = head + tail
        if (addIf(newWord, result, check)){
            addParts(head, tail.reverse, result, check)
            addParts(tail.head.toString,  tail.tail + head, result,check)
        }
    }

    private def addIf(w:String, result:ListBuffer[String], check:Set[String] ):Boolean={
        if (!check.contains(w)){
            println(( "adding ", w))
            result += w
            check += w
            return true
        }
        else {
            println("Not adding " + w)
            return false
        }
    }
}
