package cz.microshop.cart.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="cart")
public class Cart {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    //private Long id;
    private String id;
    private Long cartId;
    private Long userId;
    //@DBRef
    private List<Item> items;

    public Cart() {
        cartId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
