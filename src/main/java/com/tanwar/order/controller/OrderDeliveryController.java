package com.tanwar.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tanwar.order.domain.ShippingAddress;
import com.tanwar.order.service.OrderDeliveryService;

@RestController
public class OrderDeliveryController {
	
	@Autowired
	private OrderDeliveryService orderDeliveryService;
	
	@GetMapping(path = "/orders/{order-number}/address")
	public ShippingAddress getOrder(@PathVariable("order-number") Long orderNumber){
		return orderDeliveryService.getShippingDetails(orderNumber);
	}
	
}
