package batch2_test.batch2.test.Model;

import java.io.Serializable;

import jakarta.validation.constraints.Min;

public class Item implements Serializable{

    private String name;

    @Min(value = 1, message = "You must have minimum 1 item")
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item [name=" + name + ", quantity=" + quantity + "]";
    }

}
