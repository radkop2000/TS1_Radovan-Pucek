package archive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import shop.Item;
import shop.Order;
import shop.ShoppingCart;
import shop.StandardItem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchasesArchiveTest {

    Item item1;
    Item item2;

    @BeforeEach
    public void setUp() {
        item1 = new StandardItem(1, "Test Item 1", 10, "Test Caterogory 1", 20);
        item2 = new StandardItem(2, "Test Item 2", 20, "Test Category 2", 30);

    }

    @Test
    public void testPrintItemPurchaseStatistics() {
        HashMap<Integer, ItemPurchaseArchiveEntry> itemArchive = new HashMap<>();
        itemArchive.put(1, new ItemPurchaseArchiveEntry(item1));
        itemArchive.put(2, new ItemPurchaseArchiveEntry(item2));
        PurchasesArchive purchasesArchive = new PurchasesArchive(itemArchive, new ArrayList<>());


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        java.io.PrintStream out = System.out;
        System.setOut(printStream);

        purchasesArchive.printItemPurchaseStatistics();

        String expectedOutput = "ITEM PURCHASE STATISTICS:\n"
                + "ITEM  Item   ID 1   NAME Test Item 1   CATEGORY Test Caterogory 1   PRICE 10.0   LOYALTY POINTS 20   HAS BEEN SOLD 1 TIMES\n"
                + "ITEM  Item   ID 2   NAME Test Item 2   CATEGORY Test Category 2   PRICE 20.0   LOYALTY POINTS 30   HAS BEEN SOLD 1 TIMES\n";
        String actualOutput = outputStream.toString();
        assertEquals(expectedOutput, actualOutput);

        System.setOut(out);
    }


    @Test
    public void testGetHowManyTimesHasBeenItemSold() {

        ArrayList<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        ShoppingCart cart = new ShoppingCart(items);
        Order order = new Order(cart, "John Doe", "123 Main St", 0);


        HashMap<Integer, ItemPurchaseArchiveEntry> itemArchive = new HashMap<>();

        PurchasesArchive purchasesArchive = new PurchasesArchive(itemArchive, new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            purchasesArchive.putOrderToPurchasesArchive(order);
            int result = purchasesArchive.getHowManyTimesHasBeenItemSold(item1);
            assertEquals(i+1, result);
        }

    }

    @Test
    public void testItemPurchaseArchiveEntry() {
        Item item = Mockito.mock(Item.class);
        ItemPurchaseArchiveEntry itemPurchaseArchiveEntry = new ItemPurchaseArchiveEntry(item);
        Mockito.when(item.toString()).thenReturn("Test Item");

        String expected = "ITEM  Test Item   HAS BEEN SOLD 1 TIMES";
        assertEquals(expected, itemPurchaseArchiveEntry.toString());
    }
}