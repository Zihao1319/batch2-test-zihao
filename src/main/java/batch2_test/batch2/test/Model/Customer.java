package batch2_test.batch2.test.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Customer implements Serializable {

    @NotNull(message = "Name must not be empty")
    @Size(min = 2, message = "Name must be at least 2 characters")
    @NotEmpty(message = "Name must not be empty")
    private String custName;

    @NotNull(message = "Address must not be empty")
    @NotEmpty(message = "Address must not be empty")
    private String address;

    private Cart cart;

    private List<String> cartItems;

    private Quotation quotation;

    private float totalCost;

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<String> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<String> cartItems) {
        this.cartItems = cartItems;
    }

}
