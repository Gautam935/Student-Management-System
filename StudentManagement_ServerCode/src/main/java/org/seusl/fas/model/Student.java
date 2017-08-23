package org.seusl.fas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Gautam Kumar
 *
 */
@Entity
@Table(name = "student")
public class Student {

	@Id
	@Column(name = "stuId")
	private String id;

	@Column(name = "Name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "telNo")
	private String telNo;

	@Column(name = "email")
	private String email;

	@Column(name = "acedemicYear")
	private String acedemicYear;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAcedemicYear() {
		return acedemicYear;
	}

	public void setAcedemicYear(String acedemicYear) {
		this.acedemicYear = acedemicYear;
	}

}
