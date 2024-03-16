package com.Project.Store.services;

import java.util.List;
import java.util.Optional;

import com.Project.Store.repository.IProductRepository;
import com.Project.Store.repository.IUserRepository;
import com.Project.Store.repository.OrderDetailRepo;
import com.Project.Store.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.Store.entity.OrderDetail;
import com.Project.Store.entity.OrderInput;
import com.Project.Store.entity.OrderQuantity;
import com.Project.Store.entity.Product;
import com.Project.Store.entity.User;


@Service
public class OrderDetailService {

    private static final String ORDER_PLACE = "PLACED";
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    public void orderInput(OrderInput orderInput){

        List<OrderQuantity> orderQuantityList = orderInput.getOrderQuantities();

        for (OrderQuantity orderQuantity : orderQuantityList) {
            Optional<Product> productOptional = productRepository.findById(orderQuantity.getProductID());

            if (productOptional.isPresent()) {
                Product product = productOptional.get();

                String current_user = AuthTokenFilter.CURRENT_USER;
                Optional<User> userOptional = userRepository.findByUsername(current_user);

                if (userOptional.isPresent()) {
                    User user = userOptional.get();
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
                } else {
                    System.err.println("User with ID " + current_user + " not found.");
                }
            } else {
                System.err.println("Product with ID " + orderQuantity.getProductID() + " not found.");
            }
        }
    }

}

