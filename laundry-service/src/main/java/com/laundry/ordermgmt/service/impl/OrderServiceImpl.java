package com.laundry.ordermgmt.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.laundry.ordermgmt.constant.ApplicationMessages;
import com.laundry.ordermgmt.dto.ColumnFilterDTO;
import com.laundry.ordermgmt.dto.PaginationRequestDTO;
import com.laundry.ordermgmt.dto.PaginationResponseDTO;
import com.laundry.ordermgmt.repository.OrderPagingRepository;
import com.laundry.ordermgmt.repository.OrderRepository;
import com.laundry.ordermgmt.repository.entity.Orders;
import com.laundry.ordermgmt.service.OrderService;
import com.laundry.ordermgmt.util.SequenceGenerator;

@Component
@Transactional
public class OrderServiceImpl implements OrderService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private OrderPagingRepository orderPagingRepository;

	@Override
	public Orders createOrder(Orders order) {

		// Generate Sequence
		order.setOrderId(SequenceGenerator.getPrimaryKey(Orders.class));

		// Set system dates
		order.setOrderReceivedDate(new Date());
		order.setOrderModifiedDate(new Date());

		// save and return user
		return orderRepository.save(order);
	}

	@Override
	public Orders updateOrder(Long orderId, Orders order) {
		Orders orderInDB = orderRepository.findById(orderId).orElseThrow(
				() -> new ValidationException(String.format(ApplicationMessages.FIELD_DOES_NOT_EXIST_IN_DB, orderId)));

		order.setOrderId(orderInDB.getOrderId());
		order.setOrderModifiedDate(new Date());
		return orderRepository.save(order);
	}

	@Override
	public Orders getOrder(Long orderId) {
		return orderRepository.findById(orderId).orElseThrow(
				() -> new ValidationException(String.format(ApplicationMessages.FIELD_DOES_NOT_EXIST_IN_DB, orderId)));

	}

	@Override
	public List<Orders> getOrderList(Integer rows) {
		return Lists.newArrayList(orderPagingRepository
				.findAll(PageRequest.of(0, rows, Sort.by(Sort.Direction.DESC, "orderModifiedDate"))));
	}

	@Override
	public void deleteOrder(Long orderId) {
		orderRepository.deleteById(orderId);
	}

	@Override
	public PaginationResponseDTO<Orders> getOrderPaginationData(PaginationRequestDTO pageRequestDTO) {

		PaginationResponseDTO<Orders> response = new PaginationResponseDTO<Orders>();

		Integer pageNo = pageRequestDTO.getPageNo();
		Integer pageSize = pageRequestDTO.getPageSize();

		List<ColumnFilterDTO> filterInfo = pageRequestDTO.getFilterInfo();

		String orderColumnName = pageRequestDTO.getOrderColumnName();
		String orderColummValue = pageRequestDTO.getOrderColumnValue();

		Integer startIndex = (pageNo * pageSize) - pageSize;
		Integer endIndex = pageSize;

		System.out.println("Start Index : " + startIndex);
		System.out.println("End Index : " + endIndex);

		StringBuilder query = new StringBuilder("select * from orders");

		// Filter query
		query = getFilterQuery(filterInfo, query);

		// Order by formation
		if (!StringUtils.isEmpty(orderColummValue)) {
			query.append(" order by ");
			query.append(orderColumnName);
			query.append(" ").append(orderColummValue);
		}

		query.append(" limit ");
		query.append(startIndex);
		query.append(",");
		query.append(endIndex);

		System.out.println("Query - " + query);

		List<Orders> data = jdbcTemplate.query(query.toString(), new RowMapper<Orders>() {

			@Override
			public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
				Orders order = new Orders();
				order.setOrderId(rs.getLong("ORDER_ID"));
				order.setCustomerAddress(rs.getString("CUSTOMER_ADDRESS"));
				order.setCustomerName(rs.getString("CUSTOMER_NAME"));
				order.setEmailId(rs.getString("EMAIL_ID"));
				order.setEstimatedDeliveryDate(rs.getDate("ESTIMATED_DELIVERY_DATE"));
				order.setOrderReceivedDate(rs.getDate("ORDER_RECEIVED_DATE"));
				order.setOrderModifiedDate(rs.getDate("ORDER_MODIFIED_DATE"));
				order.setMobileNo(rs.getLong("MOBILE_NUMBER"));
				order.setOrderStatus(rs.getString("ORDER_STATUS"));
				order.setReceivedAmount(rs.getDouble("RECEIVED_AMOUNT"));
				order.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));
				order.setTotalItems(rs.getLong("TOTAL_ITEMS"));
				return order;
			}
		});

		response.setPageNo(pageNo);
		response.setPageSize(pageSize);
		response.setData(data);
		if (pageRequestDTO.isTotalRequired()) {
			response.setTotal(getOrderCount(pageRequestDTO));
		} else {
			response.setTotal(pageRequestDTO.getTotal());
		}
		return response;
	}

	@Override
	public Long getOrderCount(PaginationRequestDTO pageRequestDTO) {
		StringBuilder query = new StringBuilder("select count(1) from orders");
		query = getFilterQuery(pageRequestDTO.getFilterInfo(), query);
		return jdbcTemplate.queryForObject(query.toString(), Long.class);
	}

	private StringBuilder getFilterQuery(List<ColumnFilterDTO> filterInfo, StringBuilder query) {
		// Filter query formation
		if (!CollectionUtils.isEmpty(filterInfo)) {
			query.append(" where ");

			for (int i = 0; i < filterInfo.size(); i++) {
				if (i > 0) {
					query.append(" and ");
				}
				query.append(filterInfo.get(i).getFilterColumnName());
				query.append("=");
				query.append("'");
				query.append(filterInfo.get(i).getFilterColumnValue());
				query.append("'");
			}
		}
		return query;

	}

	@Override
	public Map<String, Long> getOrderStatus() {
		Map<String, Long> data = jdbcTemplate.query(
				"select count(order_id), order_status from orders group by order_status",
				new ResultSetExtractor<Map<String, Long>>() {

					@Override
					public Map<String, Long> extractData(ResultSet rs) throws SQLException, DataAccessException {
						Map<String, Long> data = new HashMap<String, Long>();
						while (rs.next()) {
							data.put(rs.getString(2), rs.getLong(1));
						}
						return data;
					}
				});

		return data;
	}

}
