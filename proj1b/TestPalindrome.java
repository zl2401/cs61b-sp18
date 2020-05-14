import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String testWord1 = "mam";
        boolean res1 = palindrome.isPalindrome(testWord1);
        assertTrue(res1);

        String testWord2 = "";
        boolean res2 = palindrome.isPalindrome(testWord2);
        assertTrue(res2);

        String testWord3 = "Aa";
        boolean res3 = palindrome.isPalindrome(testWord3);
        assertFalse(res3);
    }

    @Test
    public void testIsPalindromeCc() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertFalse(palindrome.isPalindrome("aa", obo));
        assertTrue(palindrome.isPalindrome("e", obo));
        assertTrue(palindrome.isPalindrome("", obo));
    }
}
