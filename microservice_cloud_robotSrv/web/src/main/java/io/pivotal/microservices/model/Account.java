package io.pivotal.microservices.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Account DTO - used to interact with the {@link WebAccountsService}.
 * 
 * @author Paul Chapman
 */
@JsonRootName("Account")
public class Account {

	protected Long id;
	protected String number;
	protected String name;
	protected BigDecimal balance;

	/**
	 * Default constructor for JPA only.
	 */
	protected Account() {
		balance = BigDecimal.ZERO;
	}

	public long getId() {
		return id;
	}

	/**
	 * Set JPA id - for testing and JPA only. Not intended for normal use.
	 * 
	 * @param id
	 *            The new id.
	 */
	protected void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	protected void setNumber(String accountNumber) {
		this.number = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String owner) {
		this.name = owner;
	}

	public BigDecimal getBalance() {
		return balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	protected void setBalance(BigDecimal value) {
		balance = value;
		balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	@Override
	public String toString() {
		return number + " [" + name + "]: $" + balance;
	}

}
