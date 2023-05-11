package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

public interface ICartService {
    Cart create();
    List<Cart> getAll();
    Cart getById(Long id) throws NotFoundException;
    void delete(Long id) throws NotFoundException, IllegalOperationException;
    Cart addToCart(long id, CartEntry body) throws NotFoundException, IllegalOperationException;
    Cart removeFromCart(long id, CartEntry body) throws NotFoundException, IllegalOperationException;
}
