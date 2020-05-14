package com.laundry.ordermgmt.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.laundry.ordermgmt.repository.entity.Orders;

@Repository
public interface OrderPagingRepository extends PagingAndSortingRepository<Orders, Long> {

	List<Orders> findAllByOrderStatus(String orderStatus, Pageable pageable);

	List<Orders> findAllByCustomerName(String customerName, Pageable pageable);

}
