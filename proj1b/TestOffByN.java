import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    @Test
    public void testEqualChars() {
        OffByN ob5 = new OffByN(5);
        assertTrue(ob5.equalChars('a', 'f'));
        assertTrue(ob5.equalChars('f', 'a'));
        assertFalse(ob5.equalChars('f', 'h'));
        assertFalse(ob5.equalChars('a', 'a'));

        OffByN ob1 = new OffByN(1);
        assertTrue(ob1.equalChars('a', 'b'));

        OffByN ob0 = new OffByN(0);
        assertTrue(ob0.equalChars('a', 'a'));
    }
}
