
package com.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

	private Integer id;
	private String email;
	private String first_name;
	private String last_name;
	private String avatar;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Data() {
	}

	/**
	 * 
	 * @param first_name
	 * @param last_name
	 * @param id
	 * @param avatar
	 * @param email
	 */
	public Data(Integer id, String email, String first_name, String last_name, String avatar) {
		super();
		this.id = id;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.avatar = avatar;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String firstname) {
		this.first_name = firstname;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String lastName) {
		this.last_name = lastName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
