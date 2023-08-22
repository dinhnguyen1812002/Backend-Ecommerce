package com.Project.Store.repository;

import com.Project.Store.entity.Cart;
import com.Project.Store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Long> {
     List<Cart> findAllByUserIdOrderByCreatedDateDesc(User userId);
    List<Cart> deleteByUser(User user);
}
