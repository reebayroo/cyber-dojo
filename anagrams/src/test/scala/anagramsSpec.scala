import anagrams._
import org.scalatest.FunSuite

class AnagramsSpec  extends FunSuite {

    test("given a expect a list with 'a' ") {
        assert(Anagrams.create("a") == List("a"))
    }
    test("given ab expect ab,ba ") {
        assert(Anagrams.create("ab") == List("ab","ba"))
    }
    
    test("given abc expect abc, acb, bac, bca, cab, cba ") {
        assert(Anagrams.create("abc") == List("abc", "acb", "bca", "bac", "cab", "cba").sorted)
    }
    
}
