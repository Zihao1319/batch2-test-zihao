package batch2_test.batch2.test.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.constraints.NotNull;

public class Cart implements Serializable {

    @NotNull(message = "You must add at least 1 item")
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

}
