package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CartResponse {
    private Long id;
    private List<CartEntry> shoppingList;
    private boolean payed;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.shoppingList = cart.getProductsInCart().stream().map(CartEntry::new).collect(Collectors.toList());
        this.payed = cart.isPayed();
    }
}
