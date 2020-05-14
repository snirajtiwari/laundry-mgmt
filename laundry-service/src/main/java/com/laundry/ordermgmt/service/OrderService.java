package com.laundry.ordermgmt.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.laundry.ordermgmt.dto.PaginationRequestDTO;
import com.laundry.ordermgmt.dto.PaginationResponseDTO;
import com.laundry.ordermgmt.repository.entity.Orders;

public interface OrderService extends Serializable {

	Orders createOrder(Orders order);

	Orders updateOrder(Long orderId, Orders order);

	Orders getOrder(Long orderId);

	List<Orders> getOrderList(Integer noOfRows);

	PaginationResponseDTO<Orders> getOrderPaginationData(PaginationRequestDTO pageRequestDTO);

	Long getOrderCount(PaginationRequestDTO pageRequestDTO);

	Map<String, Long> getOrderStatus();

	void deleteOrder(Long orderId);

}
