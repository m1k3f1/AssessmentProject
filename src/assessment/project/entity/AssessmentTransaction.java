package assessment.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AssessmentTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4993267946309833192L;
	@Column(name = "transaction_id")
	String transactionId;
	@Column(name = "amount")
	BigDecimal amount;
	@Column(name = "description")
	String description;
	@Column(name = "date")
	String date;
	@Column(name = "user_id")
	Integer userId;
	
	public AssessmentTransaction() {
		
	}

	public AssessmentTransaction(String transactionId, BigDecimal amount, String description, String date, Integer userId) {
		this.transactionId = transactionId;
		this.amount = amount;
		this.description = description;
		this.date = date;
		this.userId = userId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
