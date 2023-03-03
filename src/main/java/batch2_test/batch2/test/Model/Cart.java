package batch2_test.batch2.test.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Cart implements Serializable {

    // private List<Item> itemList = new LinkedList<>();
    @NotNull(message = "You must add at least 1 item")
    // @NotEmpty(message = "You must add at least 1 item")
    private Map<String, Integer> itemList = new HashMap<String, Integer>();

    public Map<String, Integer> getItemList() {
        return itemList;
    }

    public void setItemList(Map<String, Integer> itemList) {
        this.itemList = itemList;

    }

    public Cart() {
    }

    public Cart(Map<String, Integer> itemList) {
        this.itemList = itemList;
    }

    // public void addItem(Item item) {

    // String currItem = item.getName();
    // if (itemList.containsKey(currItem)) {
    // System.out.println("true");
    // // int newQuantity = itemList.get(currItem) + item.getQuantity();
    // System.out.println(itemList.get(currItem));
    // System.out.println(item.getQuantity());
    // itemList.put(currItem, itemList.get(currItem) + item.getQuantity());
    // }
    // itemList.put(currItem, item.getQuantity());
    // }

    // public List<Item> getItemList() {
    // return itemList;
    // }

    // public void setItemList(List<Item> itemList) {
    // this.itemList = itemList;
    // }

    // public void addItem(Item item) {
    // String currItem = item.getName();

    // if (currItem.equals
    // itemList.add(item);

    // }

}
