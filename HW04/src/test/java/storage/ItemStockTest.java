package storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shop.Item;
import shop.StandardItem;

import static org.junit.jupiter.api.Assertions.*;

public class ItemStockTest {

    Item item;

    @BeforeEach
    public void setUp() {
        item =  new StandardItem(1, "Dummy Item", 10.0f, "Dummy Category", 10);
    }

    @Test
    public void testItemStockConstructorWithZeroCount() {
        ItemStock stock = new ItemStock(item, 0);
        assertEquals(0, stock.getCount());
    }

    @Test
    public void testItemStockConstructorWithPositiveCount() {
        ItemStock stock = new ItemStock(item, 10);
        assertEquals(10, stock.getCount());
    }

    @Test
    public void testItemStockConstructorWithNegativeCount() {
        assertThrows(IllegalArgumentException.class, () -> new ItemStock(item, -5));
    }

    @Test
    public void testItemStockConstructorWithNullItem() {
        assertThrows(NullPointerException.class, () -> new ItemStock(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    public void testIncreaseItemCount(int increaseAmount) {
        ItemStock stock = new ItemStock(item);
        stock.IncreaseItemCount(increaseAmount);
        assertEquals(increaseAmount, stock.getCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    public void testDecreaseItemCount(int decreaseAmount) {
        ItemStock stock = new ItemStock(item);
        stock.IncreaseItemCount(100);
        stock.decreaseItemCount(decreaseAmount);
        assertEquals(100 - decreaseAmount, stock.getCount());
    }

    @Test
    public void testDecreaseItemCountToNegativeValue() {
        ItemStock stock = new ItemStock(item);
        assertThrows(IllegalArgumentException.class, () -> stock.decreaseItemCount(1));
    }

    @Test
    public void testGetCount() {
        ItemStock stock = new ItemStock(item);
        assertEquals(0, stock.getCount());
        stock.IncreaseItemCount(10);
        assertEquals(10, stock.getCount());
    }

    @Test
    public void testGetItem() {
        ItemStock stock = new ItemStock(item);
        assertEquals(item, stock.getItem());
    }
}