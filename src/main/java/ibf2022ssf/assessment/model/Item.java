package ibf2022ssf.assessment.model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Item {
    //@Autowired
    //RedisTemplate<String, String> template;

    @NotNull(message="Item name cannot be empty")
    @Pattern(regexp = "(Apple|Orange|Bread|Cheese|Chicken|Mineral Water|Instant Noodles)", message="We do not stock <itemname>")
    private String name;

    @Min(value=1, message="You must add atleast 1 item")
    private Integer quantity;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
}
