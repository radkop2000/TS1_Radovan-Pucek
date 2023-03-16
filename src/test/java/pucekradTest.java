import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class pucekradTest {

    @Test
    void factorial() {
        assertEquals(120, pucekrad.factorial(5));
    }

    @Test
    void factorialI() {
        assertEquals(120, pucekrad.factorialI(5));
    }

    @Test
    void myFactorial() {
        assertEquals(6, pucekrad.myFactorial(3));
    }
}
