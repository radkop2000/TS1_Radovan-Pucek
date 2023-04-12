package shop;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.Before;
import org.junit.Test;

import archive.PurchasesArchive;
import storage.NoItemInStorage;

public class EShopControllerTest {

    private Item testItem1;
    private Item testItem2;
    private Item testItem3;

    @Before
    public void setUp() {
        testItem1 = new StandardItem(1, "Test Item 1", 100, "Test Category", 5);
        testItem2 = new StandardItem(2, "Test Item 2", 200, "Test Category", 10);
        testItem3 = new StandardItem(3, "Test Item 3", 300, "Test Category", 15);

        EShopController.startEShop();
    }

    @Test
    public void testAddItemToCart() {
        ShoppingCart cart = EShopController.newCart();
        cart.addItem(testItem1);
        cart.addItem(testItem2);
        assertEquals(2, cart.getItemsCount());
    }

    @Test
    public void testRemoveItemFromCart() {
        ShoppingCart cart = EShopController.newCart();
        cart.addItem(testItem1);
        cart.addItem(testItem2);
        cart.removeItem(testItem1.getID());
        assertEquals(1, cart.getItemsCount());
    }

    @Test
    public void testPurchaseShoppingCartThrowsNoItemInStorage() {
        EShopController.getStorage().insertItems(testItem1,1);
        EShopController.getStorage().insertItems(testItem2,1);
        ShoppingCart cart = EShopController.newCart();
        cart.addItem(testItem1);
        cart.addItem(testItem2);
        cart.addItem(testItem3);
        assertThrows(NoItemInStorage.class, () -> EShopController.purchaseShoppingCart(cart, "John Doe", "123 Main St."));
    }


    @Test
    public void testPurchaseArchiving() throws NoItemInStorage {
        ShoppingCart cart = EShopController.newCart();
        EShopController.getStorage().insertItems(testItem1,1);
        EShopController.getStorage().insertItems(testItem2,5);
        cart.addItem(testItem1);
        cart.addItem(testItem2);
        cart.addItem(testItem2);
        cart.addItem(testItem2);
        EShopController.purchaseShoppingCart(cart, "John Doe", "123 Main St.");

        PurchasesArchive archive = EShopController.getArchive();
        assertEquals(3, archive.getHowManyTimesHasBeenItemSold(testItem2));
        assertEquals(0, archive.getHowManyTimesHasBeenItemSold(testItem3));
    }

}