package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> getAllBooks() {
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> addBook(@RequestBody ProductRequest body) throws NotFoundException {
        return new ResponseEntity<>(new ProductResponse(this.service.create(body)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse getBook(@PathVariable("id") Long bookId) throws NotFoundException {
        return new ProductResponse(this.service.getById(bookId));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse updateBook(@PathVariable("id") Long bookId, @RequestBody ProductUpdateRequest body) throws NotFoundException {
        return new ProductResponse(this.service.update(bookId, body));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable("id") Long bookId) throws NotFoundException {
        this.service.delete(bookId);
    }

    @GetMapping(value = "/{id}/amount", produces = MediaType.APPLICATION_JSON_VALUE)
    public Amount getAmount(@PathVariable("id") Long bookId) throws NotFoundException {
        return new Amount(this.service.getAmount(bookId));
    }

    @PostMapping(value = "/{id}/amount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Amount addAmount(@PathVariable("id") Long bookId, @RequestBody Amount body) throws NotFoundException {
        return new Amount(this.service.addAmount(bookId, body.getAmount()));
    }
}
