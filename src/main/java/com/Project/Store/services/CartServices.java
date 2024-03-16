package com.Project.Store.services;

import com.Project.Store.entity.*;
import com.Project.Store.exception.PaymentException;
import com.Project.Store.repository.CartRepository;
import com.Project.Store.repository.IProductRepository;
import com.Project.Store.repository.IUserRepository;
import com.Project.Store.security.jwt.AuthTokenFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



@Service
public class CartServices {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderDetailService orderDetailService;
    private static final String ORDER_PLACE = "PLACED";
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
    @Transactional(rollbackFor = PaymentException.class)
    public void checkoutCart() throws PaymentException {
        String current_user = AuthTokenFilter.CURRENT_USER;
        List<Cart> cartItems = cartRepository.findByUserUsername(current_user);

        double totalAmount = calculateTotalAmount(cartItems);
        boolean paymentSuccess =processPayment(totalAmount);

        if (paymentSuccess) {
            cartRepository.deleteAll(cartItems);
        } else {
            throw new PaymentException("Payment failed. Please try again.");
        }
    }



    private double calculateTotalAmount(List<Cart> cartItems) {
        double totalAmount = 0.0;
        for (Cart cartItem : cartItems) {
            totalAmount += cartItem.getProduct().getPrice();
        }
        return totalAmount;
    }
    private boolean processPayment(double totalAmount) {
        return true;
    }


}
