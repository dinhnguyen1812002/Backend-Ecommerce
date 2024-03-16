package com.Project.Store.controller;

import com.Project.Store.entity.OrderInput;
import com.Project.Store.exception.PaymentException;
import com.Project.Store.payload.response.MessageResponse;
import com.Project.Store.services.CheckoutService;
import com.Project.Store.services.OrderDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailServices;
    @Autowired
    private CheckoutService checkoutService;
    @PostMapping("/placeOrder")
    public ResponseEntity<MessageResponse> placeOder(@RequestBody OrderInput orderInput){
        orderDetailServices.orderInput(orderInput);
        return ResponseEntity.ok(new MessageResponse("Ban vua quang tien qua cua so"));
    }
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(OrderInput orderInput) {
        try {
            checkoutService.checkout(orderInput);
            return ResponseEntity.ok("Checkout successful");
        } catch (PaymentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed. Please try again.");
        }
    }
}
