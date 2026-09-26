package io.egen.api.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;


@Entity
@Scope("session")
@Table(name = "User")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT e FROM User e"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT e FROM User e WHERE e.email=:pEmail") })

		@NamedNativeQueries({
		@NamedNativeQuery(name = "User.authenticateNative", query = "SELECT u.* FROM User u WHERE u.name=? AND u.id=:? COLLATE SQL_Latin1_General_CP1_CS_AS") })
		@SqlResultSetMapping(name = "UserEntityMapping", entities = {
		@EntityResult(entityClass = User.class, fields = { 
				
				 @FieldResult(name = "id", column = "id"),
                 @FieldResult(name = "firstname", column = "firstname"),
                 @FieldResult(name = "lastname", column = "lastname"),
                 @FieldResult(name = "gender", column = "gender"),
                 @FieldResult(name = "phone", column = "phone"),
                 @FieldResult(name = "city", column = "city"),
                 @FieldResult(name = "state", column = "state"),
                 @FieldResult(name = "email", column = "email"),
                 @FieldResult(name = "username", column = "username"),
                 @FieldResult(name = "password", column = "password"),
                 @FieldResult(name = "role", column = "role"),

		}) })

public  class User {

	@Id
	private String id;
	
	private String firstname;
	private String lastname;

	private String gender;
	private String phone;
	

	
	@Column(unique=true)
	private String email;
	
	
	private String city;
	private String state;
	private String username;
	private String password;
	private String role="User";
	
	public User() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}