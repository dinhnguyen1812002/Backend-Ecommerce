package com.Project.Store.services;

import com.Project.Store.entity.*;
import com.Project.Store.repository.IProductRepository;
import com.Project.Store.repository.IUserRepository;
import com.Project.Store.repository.OrderDetailRepo;
import com.Project.Store.security.jwt.AuthTokenFilter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderDetailServices {
    private static String ORDER_PLACE = "Place";
    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IUserRepository userRepository;
    public void orderInput(OrderInput orderInput){

        List<OrderQuantity> orderQuantityList = orderInput.getOrderQuantities();

        for (OrderQuantity orderQuantity : orderQuantityList) {
            Product product = productRepository.findById(orderQuantity.getProductID()).get();

            String current_user = AuthTokenFilter.CURRENT_USER;
            User user = userRepository.findById(current_user).get();
            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getName(),
                    orderInput.getAddress(),
                    orderInput.getPhoneNumber(),
                    ORDER_PLACE,
                    product.getPrice() * orderQuantity.getQuantity(),
                    product,
                    user
            );
            orderDetailRepo.save(orderDetail);
        }

    }
}
