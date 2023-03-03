package ibf2022ssf.assessment.repository;
import java.util.List;
import org.springframework.stereotype.Repository;
import ibf2022ssf.assessment.model.Item;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Repository
public class ItemRepo {

    private List<Item> shoppingCart;

    public List<Item> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<Item> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addToCart(Item item){
        shoppingCart.add(item);
    }
    
}
