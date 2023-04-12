package archive;

import org.junit.jupiter.api.Test;
import shop.Item;
import shop.StandardItem;

import static org.junit.jupiter.api.Assertions.*;

class ItemPurchaseArchiveEntryTest {

    @Test
    public void testConstructor() {
        Item item = new StandardItem(1, "Test Item", 10, "Category", 10);
        ItemPurchaseArchiveEntry entry = new ItemPurchaseArchiveEntry(item);

        assertNotNull(entry);
        assertEquals(1, entry.getCountHowManyTimesHasBeenSold());
        assertEquals(item, entry.getRefItem());
        assertEquals("ITEM  " + item.toString() + "   HAS BEEN SOLD 1 TIMES", entry.toString());
    }
}