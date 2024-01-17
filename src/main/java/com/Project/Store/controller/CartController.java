package com.Project.Store.controller;

import com.Project.Store.entity.Cart;
import com.Project.Store.entity.Product;
import com.Project.Store.entity.User;
import com.Project.Store.exception.NotFoundException;
import com.Project.Store.payload.response.MessageResponse;
import com.Project.Store.repository.CartRepository;
import com.Project.Store.repository.IProductRepository;
import com.Project.Store.repository.IUserRepository;
import com.Project.Store.security.jwt.AuthTokenFilter;
import com.Project.Store.services.CartServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    IProductRepository productRepository;
    @Autowired
    IUserRepository userRepository;

    @Autowired
    CartServices cartServices;
    @GetMapping("/add/{productId}")
    public ResponseEntity<MessageResponse> addToCart(@PathVariable("productId") Long productId){
        cartServices.addToCart(productId);
        return ResponseEntity.ok(new MessageResponse("Ban vua quang tien qua cua so"));
    }
//    @GetMapping("/getCartDetail")
//    public List<Cart> getCartDetail(){
//        return cartServices.getCartDetail();
//    }
    @GetMapping("/getCartDetail")
    public List<Cart> cartDetail(){
        List<Cart> cartDetail =  cartServices.getCartDetail();
        return ResponseEntity.ok(cartDetail).getBody();
    }
}
