package com.Project.Store.controller;

import com.Project.Store.entity.Cart;
import com.Project.Store.entity.OrderInput;
import com.Project.Store.entity.Product;
import com.Project.Store.entity.User;
import com.Project.Store.exception.NotFoundException;
import com.Project.Store.exception.PaymentException;
import com.Project.Store.payload.response.MessageResponse;
import com.Project.Store.repository.CartRepository;
import com.Project.Store.repository.IProductRepository;
import com.Project.Store.repository.IUserRepository;
import com.Project.Store.security.jwt.AuthTokenFilter;
import com.Project.Store.services.CartServices;
import com.Project.Store.services.CheckoutService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
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

    @GetMapping("/getCartDetail")
    public List<Cart> cartDetail(){
        List<Cart> cartDetail =  cartServices.getCartDetail();
        return ResponseEntity.ok(cartDetail).getBody();
    }
    @GetMapping("/checkout")
    public ResponseEntity<?> checkoutCart() {
        try {
            cartServices.checkoutCart();
            return ResponseEntity.ok(new MessageResponse("Checkout successful. Cart cleared."));
        } catch (PaymentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse(e.getMessage()));
        }
    }
}
