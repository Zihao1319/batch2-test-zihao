package batch2_test.batch2.test.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import batch2_test.batch2.test.Model.Cart;
import batch2_test.batch2.test.Model.Item;

@Service
public class CartService {

    public Map<String, Integer> addItem(Item item, Cart cart) {

        Map<String, Integer> itemList = (HashMap<String, Integer>) cart.getItemList();

        String currItem = item.getName();

        if (itemList.containsKey(currItem)) {
            System.out.println("true");
            // int newQuantity = itemList.get(currItem) + item.getQuantity();
            System.out.println(itemList.get(currItem));
            System.out.println(item.getQuantity());
            itemList.put(currItem, itemList.get(currItem) + item.getQuantity());
        }
        itemList.put(currItem, item.getQuantity());

        return itemList;
    }

    public List<String> convertToArrList(Map<String, Integer> itemList) {
        Set<String> keySet = itemList.keySet();
        List<String> listOfKeys = new ArrayList<String>(keySet);
        return listOfKeys;
    }

}
