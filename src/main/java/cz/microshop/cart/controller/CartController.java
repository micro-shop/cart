package cz.microshop.cart.controller;

import cz.microshop.cart.model.Cart;
import cz.microshop.cart.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/order")
public class CartController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    CartService cartService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<ArrayList<Cart>> createOrder(@RequestBody ArrayList<Cart> cartList)   {
        return new ResponseEntity<ArrayList<Cart>>((ArrayList<Cart>) cartService.create(cartList), HttpStatus.OK);

    }

}
