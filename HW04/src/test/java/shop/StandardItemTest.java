package shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import shop.Item;
import shop.StandardItem;

class StandardItemTest {


    StandardItem item;

    @BeforeEach
    void setup() {
        item = new StandardItem(5, "name", 10, "category", 20);
    }

    @Test
    void constructor() {
        Assertions.assertEquals(item.getID(), 5);
        Assertions.assertEquals(item.getName(), "name");
        Assertions.assertEquals(item.getPrice(), 10);
        Assertions.assertEquals(item.getCategory(), "category");
        Assertions.assertEquals(item.getLoyaltyPoints(), 20);
    }

    @Test
    void setLoyaltyPoints() {
        Assertions.assertEquals(item.getLoyaltyPoints(), 20);
        item.setLoyaltyPoints(40);
        Assertions.assertEquals(item.getLoyaltyPoints(), 40);
    }

    @ParameterizedTest(name = "Item {0}: {1} | {2} | {3} | LP={4}, Item {5}: {6} | {7} | {8} | LP={9} => Expected: {10}")
    @CsvSource({
            "1, Apple, 1.99, Fruit, 10, 1, Apple, 1.99, Fruit, 10, true",
            "2, Orange, 2.99, Fruit, 20, 2, Orange, 2.99, Fruit, 30, false",
            "3, Mango, 3.99, Fruit, 30, 3, Mango, 3.99, Fruit, 40, false",
            "4, Tomato, 0.99, Vegetable, 5, 4, Tomato, 0.99, Vegetable, 5, true",
            "5, Broccoli, 1.99, Vegetable, 10, 6, Carrot, 1.99, Vegetable, 10, false"
    })
    void testEquals(int id1, String name1, float price1, String category1, int loyaltyPoints1,
                    int id2, String name2, float price2, String category2, int loyaltyPoints2,
                    boolean expected) {
        StandardItem item1 = new StandardItem(id1, name1, price1, category1, loyaltyPoints1);
        StandardItem item2 = new StandardItem(id2, name2, price2, category2, loyaltyPoints2);
        boolean actual = item1.equals(item2);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void copy() {
        Item item2 = item.copy();
        Assertions.assertEquals(item, item2);
    }
}