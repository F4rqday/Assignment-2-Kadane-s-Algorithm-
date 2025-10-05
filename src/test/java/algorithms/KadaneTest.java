package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KadaneTest {

    @Test
    public void emptyArray() {
        Kadane.Result r = Kadane.maxSubarray(new int[]{});
        assertEquals(0, r.maxSum);
        assertEquals(-1, r.start);
        assertEquals(-1, r.end);
    }

    @Test
    public void singleElement() {
        Kadane.Result r = Kadane.maxSubarray(new int[]{7});
        assertEquals(7, r.maxSum);
        assertEquals(0, r.start);
        assertEquals(0, r.end);
    }

    @Test
    public void allNegative() {
        Kadane.Result r = Kadane.maxSubarray(new int[]{-5, -2, -11, -3});
        assertEquals(-2, r.maxSum);
        assertEquals(1, r.start);
        assertEquals(1, r.end);
    }

    @Test
    public void mixedNumbers() {
        Kadane.Result r = Kadane.maxSubarray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        assertEquals(6, r.maxSum);
        assertEquals(3, r.start);
        assertEquals(6, r.end);
    }
}
