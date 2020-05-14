package com.laundry.ordermgmt.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.laundry.ordermgmt.dto.PaginationRequestDTO;
import com.laundry.ordermgmt.dto.PaginationResponseDTO;
import com.laundry.ordermgmt.repository.entity.Orders;
import com.laundry.ordermgmt.service.OrderService;

@RestController
public class OrderController implements IOrderController {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private OrderService orderService;

	@Override
	public ResponseEntity<Orders> createOrder(Orders order) throws Exception {
		logger.info("Create Order : {}", order);

		return new ResponseEntity<Orders>(orderService.createOrder(order), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Orders> updateOrder(Long orderId, Orders order) {
		logger.info("Update Order : {}", orderId);
		return new ResponseEntity<Orders>(orderService.updateOrder(orderId, order), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Orders> getOrder(Long orderId) throws Exception {
		logger.info("Fetch Order : {}", orderId);
		return new ResponseEntity<Orders>(orderService.getOrder(orderId), HttpStatus.OK);
	}

	@Override
	public void deleteOrder(Long orderId) throws Exception {
		logger.info("Delete Order : {}", orderId);
		orderService.deleteOrder(orderId);
	}

	@Override
	public ResponseEntity<List<Orders>> getOrderList(Integer rows) {
		return new ResponseEntity<List<Orders>>(orderService.getOrderList(rows), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PaginationResponseDTO<Orders>> getOrderPaginationData(PaginationRequestDTO pageRequest) {
		return new ResponseEntity<PaginationResponseDTO<Orders>>(orderService.getOrderPaginationData(pageRequest),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Long> getOrderPaginationCount(PaginationRequestDTO pageRequest) {
		return new ResponseEntity<Long>(orderService.getOrderCount(pageRequest), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Long>> getOrderStatus() throws Exception {
		return new ResponseEntity<Map<String, Long>>(orderService.getOrderStatus(), HttpStatus.OK);
	}

}
