package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

public interface ICartService {
    Cart create();
    List<Cart> getAll();
    Cart getById(Long id) throws NotFoundException;
    void delete(Long id) throws NotFoundException, BadRequestException;
    Cart addToCart(long id, CartEntry body) throws NotFoundException, BadRequestException;
    String payForShoppingCart(Long id) throws NotFoundException, BadRequestException;
}
