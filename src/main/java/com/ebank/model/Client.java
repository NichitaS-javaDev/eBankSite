package com.ebank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name = "clients")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "count_number")
	private Long countNumber;
	private double amount;
	private char currency;
	@Column(name = "card_number")
	private Long cardNumber;
	private int password;
	@JoinColumn(name = "login")
	@OneToOne(optional = false)
	private Profile profile;
	
	private static int CLIENT_INDEX;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getCountNumber() {
		return countNumber;
	}
	public void setCountNumber(Long countNumber) {
		// TODO validation needed
		this.countNumber = countNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public char getCurrency() {
		return currency;
	}
	public void setCurrency(char currency) {
		this.currency = currency;
	}
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		// TODO validation needed
		this.cardNumber = cardNumber;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		// TODO validation needed
		this.password = password;
	}
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Client createFakeClient() {
		Client client = new Client();
		client.setId(++CLIENT_INDEX);
		client.setAmount(Math.random());
		client.setCardNumber(Double.valueOf(Math.random()).longValue());
		client.setCountNumber(Double.valueOf(Math.random()).longValue());
		client.setCurrency('$');
		client.setPassword(0000);
		client.setFirstName("NaN" + String.valueOf(CLIENT_INDEX));
		client.setLastName("NaN");
		
		return client;
	}
}
