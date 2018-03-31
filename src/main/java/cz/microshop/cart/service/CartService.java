package cz.microshop.cart.service;

import cz.microshop.cart.model.Cart;
import cz.microshop.cart.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private ICartRepository cartRepository;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart find(Long id) {
        Cart byCartId = cartRepository.findByCartId(id);
        return byCartId;
    }

    public List<Cart> save(List<Cart> cartList) {
        return cartRepository.saveAll(cartList);
    }

    public Cart save(Cart cart) {
        if (cart.getCartId() != null) {
            Cart dbCart = find(cart.getCartId());
            if (dbCart != null) cart.setId(dbCart.getId());
        }
        if (cart.getCartId() == null) cart.setCartId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);

        cart.getItems().forEach(item -> {
            if (item.getId() == null) {
                item.setItemId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
            }
        });
        return cartRepository.save(cart);
    }

    public void delete(Long id) {
        cartRepository.deleteByCartId(id);
    }
}
