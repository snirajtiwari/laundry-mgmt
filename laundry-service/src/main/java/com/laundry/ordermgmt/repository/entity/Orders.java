package com.laundry.ordermgmt.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "ORDERS")
public class Orders {

	@Id
	@Column(name = "ORDER_ID")
	private Long orderId;

	@Column(name = "CUSTOMER_NAME")
	@NotNull(message = "Customer Name is required")
	private String customerName;

	@Column(name = "TOTAL_ITEMS")
	private Long totalItems;

	@Column(name = "CUSTOMER_ADDRESS")
	private String customerAddress;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "MOBILE_NUMBER")
	private Long mobileNo;

	@Column(name = "ESTIMATED_DELIVERY_DATE")
	private Date estimatedDeliveryDate;

	@Column(name = "ORDER_RECEIVED_DATE")
	private Date orderReceivedDate;

	@Column(name = "ORDER_MODIFIED_DATE")
	private Date orderModifiedDate;

	@Column(name = "TOTAL_AMOUNT")
	private Double totalAmount;

	@Column(name = "RECEIVED_AMOUNT")
	private Double receivedAmount;

	@Column(name = "ORDER_STATUS")
	private String orderStatus;

	@Column(name = "COMMENTS")
	private String comments;

	@Column(name = "ACTUAL_DELIVERY_DATE")
	private Date actualDeliveryDate;

	public Long getOrderId() {
		return orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public String getEmailId() {
		return emailId;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public Date getOrderReceivedDate() {
		return orderReceivedDate;
	}

	public Date getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public Date getOrderModifiedDate() {
		return orderModifiedDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public Double getReceivedAmount() {
		return receivedAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setReceivedAmount(Double receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setOrderReceivedDate(Date orderReceivedDate) {
		this.orderReceivedDate = orderReceivedDate;
	}

	public void setOrderModifiedDate(Date orderModifiedDate) {
		this.orderModifiedDate = orderModifiedDate;
	}

	public Date getActualDeliveryDate() {
		return actualDeliveryDate;
	}

	public void setActualDeliveryDate(Date actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
