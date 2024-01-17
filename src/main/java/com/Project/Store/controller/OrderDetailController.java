package com.Project.Store.controller;

import com.Project.Store.entity.OrderInput;
import com.Project.Store.payload.response.MessageResponse;
import com.Project.Store.services.OrderDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailController {
    @Autowired
    private OrderDetailServices orderDetailServices;
    @PostMapping("/placeOrder")
    public ResponseEntity<MessageResponse> placeOder(@RequestBody OrderInput orderInput){
        orderDetailServices.orderInput(orderInput);
        return ResponseEntity.ok(new MessageResponse("Ban vua quang tien qua cua so"));
    }
}
