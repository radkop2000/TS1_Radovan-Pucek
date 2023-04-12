package storage;

import shop.*;


/**
 * Auxiliary class for item storage
 */
public class ItemStock {
    private Item refItem;
    private int count;
    
    ItemStock(Item refItem) {
        if (refItem == null) {
            throw new NullPointerException();
        }
        this.refItem = refItem;
        count = 0;
    }

    ItemStock(Item refItem, int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }
        this.refItem = refItem;
        this.count = count;
    }

    @Override
    public String toString() {
        return "STOCK OF ITEM:  "+refItem.toString()+"    PIECES IN STORAGE: "+count;
    }
    
    void IncreaseItemCount(int x) {
        count += x;
    }
    
    void decreaseItemCount(int x) {
        if (count - x < 0) {
            throw new IllegalArgumentException();
        }
        count -= x;
    }
    
    int getCount() {
        return count;
    }
    
    Item getItem() {
        return refItem;
    }
}