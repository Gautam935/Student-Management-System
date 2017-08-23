package org.seusl.fas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Gautam Kumar
 *
 */

@Entity
@Table(name = "StudentLogin")
public class StudentLogin {

	@Id
	@Column(name="student_stuId")
	private String id;
	@Column(name="password")
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
