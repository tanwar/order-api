package com.tanwar.order.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import com.tanwar.order.domain.Order;

@Service
public class OrderService {

	// use JPA repo.

	public List<Order> getOrders(LocalDate startDate, LocalDate endDate) {
		return getOrdersFromRepo(startDate, endDate);
	}

	public Order getOrder(Long orderNumber) {
		List<Order> orders = getOrdersFromRepo(new LocalDate("2020-06-04"), new LocalDate("2020-06-10"));
		Optional<Order> orderOptional = orders.stream().filter(order -> orderNumber.equals(order.getOrderNumber()))
				.findFirst();
		return orderOptional.orElse(new Order()); // ideally throw 404.
	}

	// Method to be replaced with repo call
	private List<Order> getOrdersFromRepo(LocalDate startDate, LocalDate endDate) {

		List<Order> orders = new ArrayList<>();
		orders.add(createOrder(112L, "Pampers", 550.0, new LocalDate("2020-06-05")));
		orders.add(createOrder(113L, "Baby Shampoo", 350.0, new LocalDate("2020-06-05")));
		orders.add(createOrder(114L, "Baby Soap", 150.0, new LocalDate("2020-06-06")));
		orders.add(createOrder(115L, "Stroller", 10250.0, new LocalDate("2020-06-07")));
		orders.add(createOrder(116L, "Car Seat", 6250.0, new LocalDate("2020-06-08")));

		List<Order> filteredOrders = orders.stream()
				.filter(order -> startDate.isBefore(order.getOrderDate()) && endDate.isAfter(order.getOrderDate()))
				.collect(Collectors.toList());

		return Collections.unmodifiableList(filteredOrders);
	}

	private Order createOrder(Long orderNumber, String productName, Double price, LocalDate orderDate) {
		Order order = new Order();
		order.setOrderNumber(orderNumber);
		order.setProduct(productName);
		order.setPrice(price);
		order.setOrderDate(orderDate);
		return order;
	}

}
