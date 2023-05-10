package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.ProductIdRequest;

import java.util.List;

public interface ICartService {
    Cart create();
    List<Cart> getAll();
    Cart getById(Long id) throws NotFoundException;
    void delete(Long id) throws NotFoundException, IllegalOperationException;
    Cart addToList(long id, ProductIdRequest body) throws NotFoundException, IllegalOperationException;
    Cart removeFromList(long id, ProductIdRequest body) throws NotFoundException, IllegalOperationException;
}
