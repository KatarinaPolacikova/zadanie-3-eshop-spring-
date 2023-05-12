package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.item.CartItem;
import sk.stuba.fei.uim.oop.assignment3.item.ICartItemService;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

import java.util.List;

@Service
public class CartService implements ICartService{

    @Autowired
    private ICartRepository repo;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICartItemService itemService;

    @Override
    public Cart create() {
        return this.repo.save(new Cart());
    }

    @Override
    public List<Cart> getAll() {
        return this.repo.findAll();
    }

    @Override
    public Cart getById(Long id) throws NotFoundException {
        Cart cart = this.repo.findCartById(id);
        if (cart == null) {
            throw new NotFoundException();
        }
        return cart;
    }

    @Override
    public void delete(Long id) throws NotFoundException{
        Cart cart = this.getById(id);
        this.repo.delete(cart);
    }

    @Override
    public Cart addToCart(long id, CartEntry body) throws NotFoundException, BadRequestException {
        Cart cart = this.getById(id);
        Product product = this.productService.getById(body.getProductId());

        if(cart.isPayed() || product.getAmount() < body.getAmount()){
            throw new BadRequestException();
        }

        for(CartItem item : cart.getProductsInCart()){
            if(item.getProduct().getId().longValue() == body.getProductId().longValue()){
                item.setAmount(item.getAmount() + body.getAmount());
                itemService.save(item);
                this.productService.addAmount(product.getId(),-body.getAmount());
                return this.repo.save(cart);
            }
        }
        CartItem item = new CartItem();
        item.setAmount(body.getAmount());
        item.setProduct(productService.getById(body.getProductId()));
        this.productService.addAmount(product.getId(),-body.getAmount());

        CartItem finalItem = itemService.save(item);
        cart.getProductsInCart().add(finalItem);
        return this.repo.save(cart);
    }

    @Override
    public String payForShoppingCart(Long id) throws NotFoundException, BadRequestException {
        Cart cart = this.getById(id);
        double price = 0;

        if (cart.isPayed()) {
            throw new BadRequestException();
        }
        for (CartItem item : cart.getProductsInCart()) {
            Product product = this.productService.getById(item.getProduct().getId());
            price = price + product.getPrice()*item.getAmount();
        }

        cart.setPayed(true);
        this.repo.save(cart);
        return Double.toString(price);
    }
}
