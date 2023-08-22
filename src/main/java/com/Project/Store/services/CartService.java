package com.Project.Store.services;

import com.Project.Store.DTO.AddToCartDto;
import com.Project.Store.DTO.CartDto;
import com.Project.Store.DTO.CartItemDto;
import com.Project.Store.entity.Cart;
import com.Project.Store.entity.CartCost;
import com.Project.Store.entity.Product;
import com.Project.Store.entity.User;
import com.Project.Store.exception.NotFoundException;
import com.Project.Store.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CartService {
    @Autowired
    private ICartRepository cartRepository;
    public CartService(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    public void addToCart(AddToCartDto addToCartDto, Product product, User user){
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserIdOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getProduct().getPrice()* cartItemDto.getQuantity());
        }
        return new CartDto(cartItems,totalCost);
    }

    private CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }
    public void updateCartItem(AddToCartDto cartDto, User user,Product product){
        Cart cart = cartRepository.getOne(Long.valueOf(cartDto.getId()));
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }
    public void deleteCartItem(Long id,Long userId) throws NotFoundException {
        if (!cartRepository.existsById(id))
            throw new NotFoundException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);

    }

    public void deleteCartItems(Long userId) {
        cartRepository.deleteAll();
    }


    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }


}
