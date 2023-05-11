package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.assignment3.item.CartItem;

@Getter
@NoArgsConstructor
public class CartEntry {
    private Long productId;
    private int amount;

    public CartEntry(CartItem t){
        this.productId = t.getProduct().getId();
        this.amount = t.getAmount();
    }
}
