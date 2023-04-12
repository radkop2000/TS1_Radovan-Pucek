package shop;

import java.util.ArrayList;


/**
 * Order is created, when an user purchases the content of the shopping cart.
 * 
 */
public class Order {

    private ArrayList<Item> items;
    String customerName;
    String customerAddress;
    int state;

    public Order(ShoppingCart cart, String customerName, String customerAddress) {
        this(cart, customerName, customerAddress, 0);
    }

    public Order(ShoppingCart cart, String customerName, String customerAddress, int state) {
        if (cart == null | customerName == null | customerAddress == null) {
            throw new IllegalArgumentException();
        }

        items = cart.getCartItems();
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.state = state;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> goods) {
        this.items = goods;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
