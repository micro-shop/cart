package cz.microshop.cart.service;

import cz.microshop.cart.model.Cart;
import cz.microshop.cart.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private ICartRepository orderRepository;

    public List<Cart> findAll() {
        return orderRepository.findAll();
    }

    public List<Cart> create(List<Cart> cartList) {
        return orderRepository.saveAll(cartList);
    }
}
