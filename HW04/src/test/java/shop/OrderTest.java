package shop;

import org.junit.jupiter.api.*;
import shop.ShoppingCart;

import java.util.ArrayList;

public class OrderTest {

    ShoppingCart cart;
    ArrayList<Item> items;
    Order order;

    @BeforeEach
    public void setUp() {
        items = new ArrayList<>();
        items.add(new StandardItem(1, "item1", 100, "category1", 10));
        items.add(new StandardItem(2, "item2", 200, "category2", 20));
        cart = new ShoppingCart(items);
        order = new Order(cart, "John Doe", "123 Main St", 0);
    }

    @Nested
    @DisplayName("When a new Order is created")
    class OrderCreation {

        @Test
        @DisplayName("The order contains the items from the shopping cart")
        public void testOrderContainsItems() {
            Assertions.assertEquals(items, order.getItems());
        }

        @Test
        @DisplayName("The order has the correct customer name")
        public void testOrderHasCorrectCustomerName() {
            Assertions.assertEquals("John Doe", order.getCustomerName());
        }

        @Test
        @DisplayName("The order has the correct customer address")
        public void testOrderHasCorrectCustomerAddress() {
            Assertions.assertEquals("123 Main St", order.getCustomerAddress());
        }

        @Test
        @DisplayName("The order has the correct state")
        public void testOrderHasCorrectState() {
            Assertions.assertEquals(0, order.getState());
        }
    }

    @Nested
    @DisplayName("When an Order is updated")
    class OrderUpdate {

        @BeforeEach
        public void updateOrder() {
            order.setCustomerName("Jane Doe");
            order.setCustomerAddress("456 Elm St");
            order.setState(1);
        }

        @Test
        @DisplayName("The order has the updated customer name")
        public void testOrderHasUpdatedCustomerName() {
            Assertions.assertEquals("Jane Doe", order.getCustomerName());
        }

        @Test
        @DisplayName("The order has the updated customer address")
        public void testOrderHasUpdatedCustomerAddress() {
            Assertions.assertEquals("456 Elm St", order.getCustomerAddress());
        }

        @Test
        @DisplayName("The order has the updated state")
        public void testOrderHasUpdatedState() {
            Assertions.assertEquals(1, order.getState());
        }
    }

    @Nested
    @DisplayName("When an Order is created with invalid parameters")
    class OrderCreationWithInvalidParameters {
        ArrayList<Item> cartItems;
        ShoppingCart cart;
        String customerName;
        String customerAddress;
        int state;

        @BeforeEach
        void setUp() {
            cartItems = new ArrayList<>();
            cartItems.add(new StandardItem(1, "item1", 10.0f, "category1", 5));
            cart = new ShoppingCart(cartItems);
            customerName = "John Smith";
            customerAddress = "123 Main St.";
            state = 0;
        }

        @Test
        void testConstructorWithNullCart() {
            ShoppingCart cart = null;
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Order(cart, customerName, customerAddress, state));
        }

        @Test
        void testConstructorWithNullName() {
            customerName = null;
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Order(cart, customerName, customerAddress, state));
        }

        @Test
        void testConstructorWithNullAddress() {
            String customerAddress = null;
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Order(cart, customerName, customerAddress, state));
        }
    }
}