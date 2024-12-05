package payment_recommendation.models.cart;

import java.math.BigDecimal;
import java.util.UUID;

public class CartItem {
    private final UUID id;
    private final String itemName;

    private BigDecimal price;

    public CartItem(UUID id, String itemName, BigDecimal price) {
        this.id = id;
        this.itemName = itemName;
        validatePrice(price);
        this.price = price;
    }

    private static void validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
