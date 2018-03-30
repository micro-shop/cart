package cz.microshop.cart.service;

import cz.microshop.cart.model.Cart;
import cz.microshop.cart.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private ICartRepository cartRepository;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart find(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public List<Cart> save(List<Cart> cartList) {
        return cartRepository.saveAll(cartList);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
}
