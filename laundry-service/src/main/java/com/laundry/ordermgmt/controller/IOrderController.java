package com.laundry.ordermgmt.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.laundry.ordermgmt.constant.SwaggerConstants;
import com.laundry.ordermgmt.constant.URLPath;
import com.laundry.ordermgmt.dto.PaginationRequestDTO;
import com.laundry.ordermgmt.dto.PaginationResponseDTO;
import com.laundry.ordermgmt.repository.entity.Orders;

import io.swagger.annotations.Api;

@Api(tags = { SwaggerConstants.ORDER_TAG })
@RequestMapping(value = URLPath.SLASH)
public interface IOrderController {

	@RequestMapping(value = URLPath.ORDERS, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	ResponseEntity<List<Orders>> getOrderList(@RequestParam Integer rows);

	@RequestMapping(value = URLPath.ORDERS_PAGE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	ResponseEntity<PaginationResponseDTO<Orders>> getOrderPaginationData(@RequestBody PaginationRequestDTO pageRequest);

	@RequestMapping(value = URLPath.ORDERS_PAGE_COUNT, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	ResponseEntity<Long> getOrderPaginationCount(@RequestBody PaginationRequestDTO pageRequest);

	@RequestMapping(value = URLPath.ORDER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	ResponseEntity<Orders> createOrder(@Valid @RequestBody Orders order) throws Exception;

	@RequestMapping(value = URLPath.ORDER_EXT, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	ResponseEntity<Orders> getOrder(@PathVariable(name = URLPath.PH_ORDER_ID) Long orderId) throws Exception;

	@RequestMapping(value = URLPath.ORDER_STATS, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	ResponseEntity<Map<String, Long>> getOrderStatus() throws Exception;

	@RequestMapping(value = URLPath.ORDER_EXT, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	ResponseEntity<Orders> updateOrder(@PathVariable(name = URLPath.PH_ORDER_ID) Long orderId,
			@Valid @RequestBody Orders order);

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = URLPath.ORDER_EXT, method = RequestMethod.DELETE)
	void deleteOrder(@PathVariable(name = URLPath.PH_ORDER_ID) Long orderId) throws Exception;
}
