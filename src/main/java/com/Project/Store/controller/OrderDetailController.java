package com.Project.Store.controller;

import com.Project.Store.entity.ERole;
import com.Project.Store.entity.OrderInput;
import com.Project.Store.services.OrderDetailServices;
import com.Project.Store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailController {
    @Autowired
    private OrderDetailServices orderDetailServices;
    @PostMapping("/placeOrder")
    public void placeOrder(@RequestBody OrderInput orderInput){
        orderDetailServices.orderInput(orderInput);

    }
}
