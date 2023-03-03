package batch2_test.batch2.test.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import batch2_test.batch2.test.Model.Cart;
import batch2_test.batch2.test.Model.Customer;
import batch2_test.batch2.test.Model.Item;
import batch2_test.batch2.test.Model.Quotation;
import batch2_test.batch2.test.Service.CartService;
import batch2_test.batch2.test.Service.QuotationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PurchaseOrderController {

    @Autowired
    private CartService cartSvc;

    @Autowired
    private QuotationService quoteSvc;

    @GetMapping("/")
    public String getCartDisplay(HttpSession session, Model model) {

        Cart cart = (Cart) session.getAttribute("cart");

        if (null == cart) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        model.addAttribute("item", new Item());
        model.addAttribute("cart", cart);
        return "view1";
    }

    @PostMapping("/")
    public String addItem(@ModelAttribute(name = "cart") Cart cart, @Valid @ModelAttribute(name = "item") Item item,
            BindingResult binding, HttpSession session, Model model) throws NullPointerException {

        if (binding.hasErrors()) {
            return "redirect:/";
        }

        cart = (Cart) session.getAttribute("cart");
        System.out.printf(">>>>oldcart: %s\n", cart.getItemList().toString());

        Cart updatedCart = new Cart(cartSvc.addItem(item, cart));

        System.out.printf(">>>>new cart: %s\n", updatedCart.getItemList().toString());

        // model.addAttribute("item", new Item());
        model.addAttribute("cart", updatedCart);
        return "view1";
    }

    @PostMapping("/shippingaddress")
    public String postCart(@Valid @ModelAttribute("cart") Cart cart, BindingResult binding, Item item,
            HttpSession session, Model model) {

        if (binding.hasErrors()) {
            System.out.println("error in view 1");
            return "view1";
        }

        cart = (Cart) session.getAttribute("cart");

        System.out.printf("parsed >>>>> %s\n ", cart.getItemList().toString());

        model.addAttribute("customer", new Customer());

        return "view2";
    }

    @PostMapping("/checkout")
    public String postCheckout(@Valid @ModelAttribute("customer") Customer customer,
            BindingResult binding, HttpSession session, Model model) throws Exception {

        if (binding.hasErrors()) {
            System.out.println("error in view 2");
            return "view2";
        }

        Cart cart = (Cart) session.getAttribute("cart");

        customer.setCart(cart);
        customer.setCartItems(cartSvc.convertToArrList(cart.getItemList()));

        System.out.printf("customer address >>>>> %s\n ", customer.getAddress());
        System.out.printf("customer carts >>>>> %s\n ", customer.getCart().getItemList().toString());
        System.out.printf("customer carts >>>>> %s\n ", customer.getCartItems().toString());

        Quotation quotation = quoteSvc.getQuotations(customer.getCartItems());

        customer.setTotalCost(quoteSvc.calculateCost(quotation, cart));

        model.addAttribute("quotation", quotation);
        model.addAttribute("customer", customer);

        session.invalidate();

        return "view3";
    }

}
