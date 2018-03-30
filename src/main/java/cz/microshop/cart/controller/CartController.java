package cz.microshop.cart.controller;

import cz.microshop.cart.model.Cart;
import cz.microshop.cart.model.Item;
import cz.microshop.cart.service.CartService;
import cz.microshop.cart.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    CartService cartService;

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<ArrayList<Cart>> saveAll(@RequestBody ArrayList<Cart> cartList)   {
        return new ResponseEntity<>((ArrayList<Cart>) cartService.save(cartList), HttpStatus.OK);
    }

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
    public ResponseEntity<HttpStatus> addItem(@PathVariable Long id, @RequestBody Item item)   {
        itemService.addItem(id, item);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/removeItem", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HttpStatus> removeItem(@PathVariable Long id)   {
        itemService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/clear", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Cart> clear(@PathVariable Long id)   {
        Cart cart = cartService.find(id);
        cart.getItems().forEach(item -> {
            itemService.delete(item.getId());
        });
        cart.getItems().clear();
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
