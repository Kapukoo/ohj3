package fi.tuni.prog3.junitorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JunitOrderTest {
    
    //--------- item -----------
    @Test
    public void testItem() {
        try {
            Order.Item item = new Order.Item("test", 32);
        } catch(IllegalArgumentException e){  
        }        
    }
    @Test
    public void testNullItem() {
        try {
            Order.Item item = new Order.Item(null, 32);
        } catch(IllegalArgumentException e){  
        }
    }
    @Test
    public void testNegItem() {
        try {
            Order.Item item = new Order.Item("test", -2.5);
        } catch(IllegalArgumentException e){  
        }
    }
    @Test
    public void testGetName() {
        Order.Item item = new Order.Item("test", 32);
        String e = "test";
        String a = item.getName();
        assertEquals(e, a);
    }
    @Test
    public void testGetPrice() {
        Order.Item item = new Order.Item("test", 32);
        double e = 32;
        double a = item.getPrice();
        assertEquals(e, a);
    }
    @Test
    public void testItemToString() {
        Order.Item item = new Order.Item("test", 32);
        String e = "Item(test, 32.00)";
        String a = item.toString();
        assertEquals(e, a);
    }
    @Test
    public void testEquals() {
        Order.Item item1 = new Order.Item("test", 32);
        Order.Item item2 = new Order.Item("test", 43);
        boolean e = true;
        boolean a = item1.equals(item2);
        assertEquals(e, a);
    }
    @Test
    public void testNotEquals() {
        Order.Item item1 = new Order.Item("tset", 32);
        Order.Item item2 = new Order.Item("test", 43);
        boolean e = false;
        boolean a = item1.equals(item2);
        assertEquals(e, a);
    }
    
    
    //-------------entry-------------
    
    @Test
    public void testEntry() {
        Order.Item item1 = new Order.Item("test", 32);
        try {
            Order.Entry entry = new Order.Entry(item1, 2);
        }catch(IllegalArgumentException e){
        }
    }
    @Test
    public void testNegEntry() {
        Order.Item item1 = new Order.Item("test", 32);
        try {
            Order.Entry entry = new Order.Entry(item1, -2);
        }catch(IllegalArgumentException e){
        }
    }
    @Test
    public void testGetItemName() {
        Order.Item item = new Order.Item("test", 17);
        Order.Entry entry = new Order.Entry(item, 10);
        String e = "test";
        String a = entry.getItemName();
        assertEquals(e, a);
    }
    @Test
    public void testGetUnitPrice() {
        Order.Item item = new Order.Item("test", 17);
        Order.Entry entry = new Order.Entry(item, 10);
        double e = 17;
        double a = entry.getUnitPrice();
        assertEquals(e, a);
    }
    @Test
    public void testGetItem() {
        Order.Item item = new Order.Item("test", 17);
        Order.Entry entry = new Order.Entry(item, 10);
        Order.Item e = item;
        Order.Item a = entry.getItem();
        assertEquals(e, a);
    }
    @Test
    public void testGetCount() {
        Order.Item item = new Order.Item("test", 17);
        Order.Entry entry = new Order.Entry(item, 10);
        int e = 10;
        int a = entry.getCount();
        assertEquals(e, a);
    }
    @Test
    public void testEntryToString() {
        Order.Item item = new Order.Item("test", 17);
        Order.Entry entry = new Order.Entry(item, 10);
        String e = "10 units of Item(test, 17.00)";
        String a = entry.toString();
        assertEquals(e, a);
    }
    
    
    //---------------order----------------

    
    @Test
    public void testAddItems() {
        Order.Item item = new Order.Item("test3", 8);
        Order order = new Order();
        try {
            order.addItems(item, 2);
        } catch(IllegalArgumentException | IllegalStateException e){
        }
    }
    @Test
    public void testAddItemsString() {
        Order.Item item = new Order.Item("test3", 8);
        Order order = new Order();
        order.addItems(item, 2);
        try {
            order.addItems("test3", 2);
        } catch(IllegalArgumentException | NoSuchElementException e){
        }
    }
    @Test
    public void testAddNegItems() {
        Order.Item item = new Order.Item("test3", 8);
        Order order = new Order();
        try {
            order.addItems(item, -2);
        } catch(IllegalArgumentException e){
        }
    }
    @Test
    public void testAddSameNameItems() {
        Order.Item item1 = new Order.Item("test3", 5);
        Order.Item item2 = new Order.Item("test3", 8);
        Order order = new Order();
        order.addItems(item1, 2);
        try {
            order.addItems(item2, 1);
        } catch(IllegalStateException e){
        }
    }
    @Test
    public void testAddItemsStringNoItem() {
        Order.Item item = new Order.Item("test3", 8);
        Order order = new Order();
        order.addItems(item, 2);
        try {
            order.addItems("test", 2);
        } catch(NoSuchElementException e){
        }
    }
    @Test
    public void testAddItemsStringNeg() {
        Order.Item item = new Order.Item("test3", 8);
        Order order = new Order();
        order.addItems(item, 2);
        try {
            order.addItems("test3", -5);
        } catch(IllegalArgumentException e){
        }
    }
    
    
    /*
    @Test
    public void testGetEntries() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("item1", 8);
        Order.Item item2 = new Order.Item("item2", 5);
        Order.Item item3 = new Order.Item("item3", 3);
        //Order.Entry entry1 = new Order.Entry(item1, 2);
        //Order.Entry entry2 = new Order.Entry(item2, 1);
        //Order.Entry entry3 = new Order.Entry(item3, 1);
        order.addItems(item1, 2);
        order.addItems(item2, 1);
        order.addItems(item3, 1);
        
        List<Order.Entry> e = Arrays.asList(
        new Order.Entry(item1, 2),
        new Order.Entry(item2, 1),
        new Order.Entry(item3, 1)
        );
        
        List<Order.Entry> a = order.getEntries();
        assertArrayEquals(e.toArray(), a.toArray());
    }
    */
    
    @Test
    public void testGetEntryCount() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("item1", 8);
        Order.Item item2 = new Order.Item("item2", 5);
        Order.Item item3 = new Order.Item("item3", 3);
        order.addItems(item1, 2);
        order.addItems(item2, 1);
        order.addItems(item3, 1);
        int e = 3;
        int a = order.getEntryCount();
        assertEquals(e,a);  
    }
    @Test
    public void testGetItemCount() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("item1", 8);
        Order.Item item2 = new Order.Item("item2", 5);
        Order.Item item3 = new Order.Item("item3", 3);
        order.addItems(item1, 3);
        order.addItems(item2, 2);
        order.addItems(item3, 1);
        int e = 6;
        int a = order.getItemCount();
        assertEquals(e,a);  
    }
    @Test
    public void testGetTotalPrice() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("item1", 8);
        Order.Item item2 = new Order.Item("item2", 5);
        Order.Item item3 = new Order.Item("item3", 3);
        order.addItems(item1, 3);
        order.addItems(item2, 2);
        order.addItems(item3, 1);
        double e = 3*8+2*5+1*3;
        double a = order.getTotalPrice();
        assertEquals(e,a);  
    }
    @Test
    public void testGetTotalPriceDouble() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("item1", 8.6);
        Order.Item item2 = new Order.Item("item2", 5.74);
        Order.Item item3 = new Order.Item("item3", 3.56);
        order.addItems(item1, 3);
        order.addItems(item2, 2);
        order.addItems(item3, 1);
        double e = 3*8.6+2*5.74+1*3.56;
        double a = order.getTotalPrice();
        String es = String.format("%.4f",e);
        String as = String.format("%.4f",a);
        assertEquals(es,as);  
    }
    @Test
    public void testGetTotalPriceRemove() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("item1", 8.62);
        Order.Item item2 = new Order.Item("item2", 5.74);
        Order.Item item3 = new Order.Item("item3", 3.56);
        order.addItems(item1, 3);
        order.addItems(item2, 2);
        order.addItems(item3, 1);
        order.removeItems("item1", 3);
        double e = 2*5.74 + 1*3.56;
        double a = order.getTotalPrice();
        String es = String.format("%.4f",e);
        String as = String.format("%.4f",a);
        assertEquals(es,as);
    }
    @Test
    public void testIsEmpty() {
        Order order = new Order();
        boolean e = true;
        boolean a = order.isEmpty();
        assertEquals(e,a);
    }
    @Test
    public void testIsEmptyRemove() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("item1", 8);
        order.addItems(item1, 1);
        order.removeItems("item1", 1);
        boolean e = true;
        boolean a = order.isEmpty();
        assertEquals(e,a);
    }
    @Test
    public void testIsNotEmptyRemove() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("item1", 8);
        order.addItems(item1, 1);
        boolean e = false;
        boolean a = order.isEmpty();
        assertEquals(e,a);
    }
    @Test
    public void testRemoveItems() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("item1", 8);
        Order.Item item2 = new Order.Item("item2", 5);
        Order.Item item3 = new Order.Item("item3", 3);
        order.addItems(item1, 2);
        order.addItems(item2, 1);
        order.addItems(item3, 1);
        boolean e = true;
        boolean a = false;
        try {
            a = order.removeItems("item1",2);
        } catch(IllegalArgumentException | NoSuchElementException ee){
        }
        assertEquals(e,a);  
    } 
    @Test
    public void testRemoveWrongItems() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("item1", 8);
        Order.Item item2 = new Order.Item("item2", 5);
        order.addItems(item1, 2);
        order.addItems(item2, 1);
        try {
            order.removeItems("item3",2);
        } catch(NoSuchElementException ee){
        }
    } 
    @Test
    public void testRemoveNegItems() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("item1", 8);
        Order.Item item2 = new Order.Item("item2", 5);
        order.addItems(item1, 2);
        order.addItems(item2, 1);
        try {
            order.removeItems("item1", -3);
        } catch(IllegalArgumentException ee){
        }  
    } 
    @Test
    public void testRemoveInsufficientItems() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("item1", 8);
        Order.Item item2 = new Order.Item("item2", 5);
        order.addItems(item1, 2);
        order.addItems(item2, 1);
        try {
            order.removeItems("item1",4);
        } catch(IllegalArgumentException ee){
        } 
    } 
}