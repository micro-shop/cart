package cz.microshop.cart.service;

import cz.microshop.cart.model.Cart;
import cz.microshop.cart.model.Item;
import cz.microshop.cart.repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private IItemRepository itemRepository;

    @Autowired
    public CartService cartService;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item find(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public List<Item> create(List<Item> cartList) {
        return itemRepository.saveAll(cartList);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public Cart addItem(Long cartId, Item item) {
        Cart cart = cartService.find(cartId);
        Optional<Item> lineItemOptional = cart.getItems().stream().filter(cartItem -> item.getProductId().equals(cartItem.getProductId())).findFirst();
        Item lineItem = null;
        try {
            lineItem = lineItemOptional.get();
            lineItem.setQuantity(lineItem.getQuantity() + 1L);
            itemRepository.save(lineItem);
        } catch (NoSuchElementException e) {
            itemRepository.save(item);
        }

        cart.getItems().add(lineItem);
        return cartService.save(cart);
    }
}
