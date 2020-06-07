package com.tanwar.order.controller;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanwar.order.domain.Order;
import com.tanwar.order.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(path = "/orders")
	public List<Order> getOrders(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate){
		return orderService.getOrders(startDate, endDate);
	}
	
	@GetMapping(path = "/orders/{order-number}")
	public Order getOrder(@PathVariable("order-number") Long orderNumber){
		return orderService.getOrder(orderNumber);
	}
	
}
