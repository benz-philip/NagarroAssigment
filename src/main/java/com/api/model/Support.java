
package com.api.model;

public class Support {

	private String url;
	private String text;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Support() {
	}

	/**
	 * 
	 * @param text
	 * @param url
	 */
	public Support(String url, String text) {
		super();
		this.url = url;
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
