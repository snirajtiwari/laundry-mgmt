package com.laundry.ordermgmt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laundry.ordermgmt.repository.entity.Orders;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long> {

	List<Orders> findByCustomerName(String customerName);

	List<Orders> findByOrderStatus(String orderStatus);

}
