package cz.microshop.cart.repository;

import cz.microshop.cart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface ICartRepository extends MongoRepository<Cart, String> {

    Cart findByCartId(Long id);
    Long deleteByCartId(Long id);
}
