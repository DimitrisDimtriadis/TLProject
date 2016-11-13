package org.tl2project.Models;

import java.io.Serializable;
import javax.persistence.*;



@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;

	

	

	

	public User(){}

	public User(String username,String email,String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long userId) {
		this.id = userId;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}