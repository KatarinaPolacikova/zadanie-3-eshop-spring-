package sk.stuba.fei.uim.oop.assignment3.product;

import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

public interface IProductService {

    List<Product> getAll();
    Product create(ProductRequest request) throws NotFoundException;
    Product getById(long id) throws NotFoundException;
    Product update(long id, ProductUpdateRequest request) throws NotFoundException;
    void delete(long id) throws NotFoundException;
    int getAmount(long id) throws NotFoundException;
    int addAmount(long id, int increment) throws NotFoundException;
}
