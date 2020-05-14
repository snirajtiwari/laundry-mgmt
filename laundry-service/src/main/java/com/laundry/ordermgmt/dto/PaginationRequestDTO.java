package com.laundry.ordermgmt.dto;

import java.util.List;

public class PaginationRequestDTO {

	private Integer pageNo;

	private Integer pageSize;

	private List<ColumnFilterDTO> filterInfo;

	private String orderColumnName;

	private String orderColumnValue;

	private Long total;

	private boolean totalRequired;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderColumnName() {
		return orderColumnName;
	}

	public void setOrderColumnName(String orderColumnName) {
		this.orderColumnName = orderColumnName;
	}

	public String getOrderColumnValue() {
		return orderColumnValue;
	}

	public void setOrderColumnValue(String orderColumnValue) {
		this.orderColumnValue = orderColumnValue;
	}

	public List<ColumnFilterDTO> getFilterInfo() {
		return filterInfo;
	}

	public void setFilterInfo(List<ColumnFilterDTO> filterInfo) {
		this.filterInfo = filterInfo;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public boolean isTotalRequired() {
		return totalRequired;
	}

	public void setTotalRequired(boolean totalRequired) {
		this.totalRequired = totalRequired;
	}

}
