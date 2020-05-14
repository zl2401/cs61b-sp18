import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {

    @Test
    public void testIsSameNumber() {
        Flik f = new Flik();
        assertTrue(f.isSameNumber(2, 2));
        assertFalse(f.isSameNumber(2, -2));
        assertFalse(f.isSameNumber(2, 0));
        assertFalse(f.isSameNumber(2, 3));
        assertTrue(f.isSameNumber(128, 128));
    }
}
