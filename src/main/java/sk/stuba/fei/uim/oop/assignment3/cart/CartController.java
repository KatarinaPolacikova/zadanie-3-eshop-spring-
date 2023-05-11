package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResponse> addCart() {
        return new ResponseEntity<>(new CartResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CartResponse> getAllCarts() {
        return this.service.getAll().stream().map(CartResponse::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse getCart(@PathVariable("id") long cartId) throws NotFoundException {
        return new CartResponse(this.service.getById(cartId));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long cartId) throws NotFoundException, IllegalOperationException {
        this.service.delete(cartId);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse addToCart(@PathVariable("id") Long cartId, @RequestBody CartEntry body) throws NotFoundException, IllegalOperationException {
        return new CartResponse(this.service.addToCart(cartId, body));

    }

    @GetMapping("/{id}/pay")
    public String payForShoppingCart(@PathVariable("id") Long id) throws NotFoundException, IllegalOperationException {
        return this.service.payForShoppingCart(id);
    }
}
