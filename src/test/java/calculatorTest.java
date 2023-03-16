import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class calculatorTest {

    @Test
    void add() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void subtract() {
        assertEquals(5, calculator.subtract(10, 5));
    }

    @Test
    void multiply() {
        assertEquals(10, calculator.multiply(2, 5));
    }

    @Test
    void divide() {
        assertEquals(5, calculator.divide(10, 2));
    }

    @Test
    void divideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }
}