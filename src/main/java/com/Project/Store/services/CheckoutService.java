package com.Project.Store.services;

import com.Project.Store.entity.Cart;
import com.Project.Store.entity.OrderInput;
import com.Project.Store.exception.PaymentException;
import com.Project.Store.repository.CartRepository;
import com.Project.Store.security.jwt.AuthTokenFilter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Transactional
    public void checkout(OrderInput orderInput) throws PaymentException {
        // Get the username from the JWT token
         String current_user = AuthTokenFilter.CURRENT_USER;

        // Retrieve the user's cart
        List<Cart> cartItems = cartRepository.findByUserUsername(current_user);

        double totalAmount = calculateTotalAmount(cartItems);

        boolean paymentSuccess = processPayment(totalAmount);

        if (paymentSuccess) {
            for (Cart cartItem : cartItems) {
                orderDetailService.orderInput(orderInput);
            }
            cartRepository.deleteAll(cartItems);
        } else {
            throw new PaymentException("Payment failed. Please try again.");
        }
    }

    private double calculateTotalAmount(List<Cart> cartItems) {
        double totalAmount = 0;
        for (Cart cartItem : cartItems) {
            totalAmount += cartItem.getProduct().getPrice();
        }
        return totalAmount;
    }

    private boolean processPayment(double totalAmount) {
        return true;
    }
}
