package cz.microshop.cart.repository;

import cz.microshop.cart.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface IItemRepository extends MongoRepository<Item, String> {

    Item findByItemId(Long itemId);
    //Item deleteItem(Long itemId);
}
