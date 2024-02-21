package com.project.giftsandflowers.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.giftsandflowers.model.Cart;
import com.project.giftsandflowers.repository.CartRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CartController {
	
	@Autowired
	private CartRepository cartRepository;
	
	@GetMapping("/carts")
	public List<Cart> getAllCarts(){
		return cartRepository.findAll();
	}
	
	@PostMapping("/carts")
	public Cart createCart(@RequestBody Cart cart) {
		return cartRepository.save(cart);
	}
	
	@GetMapping("/carts/{id}")
	public ResponseEntity<Cart> getCartById(@PathVariable Long id){
		Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart item not exist:"+id));
		return ResponseEntity.ok(cart);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/carts/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCart(@PathVariable Long id){
		Cart cart = cartRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("cart item not exist with id"+id));
		
		cartRepository.delete(cart);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/carts")
	public ResponseEntity<Map<String, Boolean>> deleteAllCarts(){
		List<Cart> cart = cartRepository.findAll();
		cartRepository.deleteAll(cart);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
