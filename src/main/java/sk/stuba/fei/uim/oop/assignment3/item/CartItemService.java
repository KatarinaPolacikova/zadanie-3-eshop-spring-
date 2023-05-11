package sk.stuba.fei.uim.oop.assignment3.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService implements ICartItemService {

    @Autowired
    ICartItemRepository repo;

    @Override
    public CartItem save(CartItem item) {
        return this.repo.save(item);
    }
}
