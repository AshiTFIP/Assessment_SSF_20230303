package ibf2022ssf.assessment.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ibf2022ssf.assessment.model.Item;
import ibf2022ssf.assessment.model.ShippingAddress;
import ibf2022ssf.assessment.repository.ItemRepo;
import ibf2022ssf.assessment.service.ItemService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = {"/cart"})
public class PurchaseOrderController {

    @Autowired
    ItemRepo itmRepo;
    ItemService itmService;

    @GetMapping
     public String getCart(Model model, HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        if (null == cart) {
            	session.setAttribute("cart", itmRepo.getShoppingCart());
            	}
        model.addAttribute("item", new Item());
        return "view1";
    }

    @PostMapping
    public String postCart(@Valid Item item, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            return "view1";
        }
        List<Item> cart = (List<Item>)session.getAttribute("cart");
        cart.add(item);
        session.setAttribute("cart", itmRepo.getShoppingCart());
        model.addAttribute("cart", cart);
        model.addAttribute("item", new Item());
        return "view1";
    }

    @GetMapping(path = {"/shippingaddress"})
    public String getShippingAddress(Model model, HttpSession session) {
       model.addAttribute("shippingaddress", new ShippingAddress());
       return "view2";
   }

   @PostMapping(path = {"/shippingaddress"})
   public String postShippingAddress(@Valid ShippingAddress shippingAddress, BindingResult result, HttpSession session, Model model) {
    
       if (result.hasErrors()) {
           return "view2";
       }
       session.setAttribute("shippingaddress", shippingAddress);
       List<Item> cart = (List<Item>)session.getAttribute("cart");
       List<String> items = itmService.listOfItems(cart);
       Float totalCost = itmService.totalCost(quotation, items);       
       return "view3";
   }
}
