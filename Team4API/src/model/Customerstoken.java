package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the customerstokens database table.
 * 
 */
@Entity
@Table(name="customerstokens")
@NamedQuery(name="Customerstoken.findAll", query="SELECT c FROM Customerstoken c")
public class Customerstoken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int customerId;

	private String token;

	private Timestamp tokencreated;

	public Customerstoken() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getTokencreated() {
		return this.tokencreated;
	}

	public void setTokencreated(Timestamp tokencreated) {
		this.tokencreated = tokencreated;
	}

}