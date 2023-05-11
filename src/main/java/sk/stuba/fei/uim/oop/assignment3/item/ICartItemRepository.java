package sk.stuba.fei.uim.oop.assignment3.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAll();
    CartItem findCartItemById(Long id);
}
