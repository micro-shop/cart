package cz.microshop.cart.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="item")
public class Item {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    //private Long id;
    private String id;
    private Long itemId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    private Long productId;
    private String productName;
    private BigDecimal unitPrice;
    private Long quantity;

    public Item() {
        //itemId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
