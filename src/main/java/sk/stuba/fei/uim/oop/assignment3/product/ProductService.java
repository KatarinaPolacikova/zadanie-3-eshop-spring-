package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository repo;

    @Override
    public List<Product> getAll() {return  this.repo.findAll();}

    @Override
    public Product create(ProductRequest request){
        Product product = this.repo.save(new Product(request));
        return product;
    }

    @Override
    public Product getById(long id) throws NotFoundException {
        Product product = this.repo.findProductById(id);
        if(product == null){
            throw new NotFoundException();
        }
        return product;
    }

    @Override
    public Product update(long id, ProductUpdateRequest request) throws NotFoundException {
        Product product = this.getById(id);
        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        return this.repo.save(product);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        Product product = this.getById(id);
        this.repo.delete(product);
    }

    @Override
    public int getAmount(long id) throws NotFoundException {
        return this.getById(id).getAmount();
    }

    @Override
    public int addAmount(long id, int increment) throws NotFoundException {
        Product product = this.getById(id);
        product.setAmount(product.getAmount() + increment);
        this.repo.save(product);
        return product.getAmount();
    }
}
