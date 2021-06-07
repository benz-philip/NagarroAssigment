
package com.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	private String page;

	private String total;
	private String totalPages;
	private List<Data> data = null;
	private Support support;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public User() {
	}

	/**
	 * 
	 * @param total
	 * @param perPage
	 * @param data
	 * @param totalPages
	 * @param page
	 * @param support
	 */
	public User(String page, String total, String totalPages, List<Data> data, Support support) {
		super();
		this.page = page;
		this.total = total;
		this.totalPages = totalPages;
		this.data = data;
		this.support = support;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public Support getSupport() {
		return support;
	}

	public void setSupport(Support support) {
		this.support = support;
	}

}
