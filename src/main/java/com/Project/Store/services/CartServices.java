package com.Project.Store.services;

import com.Project.Store.entity.Cart;
import com.Project.Store.entity.Product;
import com.Project.Store.entity.User;
import com.Project.Store.repository.CartRepository;
import com.Project.Store.repository.IProductRepository;
import com.Project.Store.repository.IUserRepository;
import com.Project.Store.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServices {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;

    public void addToCart(Long productId){
        Product product = productRepository.findById(productId).get();
        String current_user = AuthTokenFilter.CURRENT_USER;
        User user = null;
        if(current_user!= null){
            user = userRepository.findByUsername(current_user).get();
        }
        if(product != null && user!= null ){
            Cart cart= new Cart(product, user);
            cartRepository.save(cart);
        }
    }
    public List<Cart> getCartDetail(){
        String current_user = AuthTokenFilter.CURRENT_USER;
        User user = userRepository.findByUsername(current_user).get();
        return cartRepository.findByUser(user);
    }
}
