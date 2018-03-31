package cz.microshop.cart.controller;

import cz.microshop.cart.model.Cart;
import cz.microshop.cart.model.Item;
import cz.microshop.cart.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    CartService cartService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<Cart> save(@RequestBody Cart cart)   {
        return new ResponseEntity<>((Cart) cartService.save(cart), HttpStatus.OK);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Cart> find(@RequestParam Long id)   {
        return new ResponseEntity<>(cartService.find(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id)   {
        clear(id);
        cartService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/addItem", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<Cart> addItem(@PathVariable Long id, @RequestBody Item item)   {
        Cart cart = cartService.find(id);
        Item dbItem = cart.getItems().stream().filter(i -> i.getProductId().equals(item.getProductId())).findFirst().orElse(null);
        if (dbItem == null) {
            cart.getItems().add(item);
        } else {
            dbItem.setQuantity(dbItem.getQuantity() + 1L);
        }
        cart = cartService.save(cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/removeItem", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Cart> removeItem(@PathVariable Long id, @RequestParam Long itemId)   {
        Cart cart = cartService.find(id);
        cart.getItems().removeIf(item -> itemId.equals(item.getItemId()));
        cart = cartService.save(cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/clear", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Cart> clear(@PathVariable Long id)   {
        Cart cart = cartService.find(id);
        cart.getItems().clear();
        cartService.save(cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
