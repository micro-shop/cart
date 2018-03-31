package cz.microshop.cart.service;

import cz.microshop.cart.model.Cart;
import cz.microshop.cart.model.Item;
import cz.microshop.cart.repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
        return itemRepository.findByItemId(id);
    }

    public List<Item> create(List<Item> cartList) {
        return itemRepository.saveAll(cartList);
    }

    public void delete(Long id) {
        Item byItemId = itemRepository.findByItemId(id);
        itemRepository.delete(byItemId);
    }

    @Transactional
    public Cart addItem(Long cartId, Item item) {
        Cart cart = cartService.find(cartId);
        Item lineItem = null;
        for (Item item1 : cart.getItems()) {
            if (item1.getProductId().equals(item.getProductId())) {
                lineItem = item1;
            }
        }
        if (lineItem != null) {
            lineItem.setQuantity(lineItem.getQuantity() + 1L);
            save(lineItem);
        } else {
            cart.getItems().add(item);
            cart = cartService.save(cart);
            save(item);
        }
        return cart;
        /*
        //Optional<Item> lineItemOptional = cart.getItems().stream().filter(cartItem -> item.getProductId().equals(cartItem.getProductId())).findFirst();
        //Item lineItem = null;
        try {
            //lineItem = lineItemOptional.get();
            lineItem.setQuantity(lineItem.getQuantity() + 1L);
            itemRepository.save(lineItem);
        } catch (NoSuchElementException e) {
            itemRepository.save(item);
        }*/
/*
        cart.getItems().add(lineItem);
        return cartService.saveart);(c*/
    }

    public Item save(Item item) {
        if (item.getItemId() == null) item.setItemId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        return itemRepository.save(item);
    }
}
