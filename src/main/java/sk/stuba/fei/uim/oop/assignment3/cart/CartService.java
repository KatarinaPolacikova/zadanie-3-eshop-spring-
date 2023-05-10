package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductIdRequest;

import java.util.List;

@Service
public class CartService implements ICartService{

    @Autowired
    private ICartRepository repo;

    @Autowired
    private IProductService bookService;

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
    public void delete(Long id) throws NotFoundException, IllegalOperationException {
        Cart cart = this.getById(id);
        this.repo.delete(cart);
    }

    @Override
    public Cart addToList(long id, ProductIdRequest body) throws NotFoundException, IllegalOperationException {
        return null;
    }

    @Override
    public Cart removeFromList(long id, ProductIdRequest body) throws NotFoundException, IllegalOperationException {
        return null;
    }

}
