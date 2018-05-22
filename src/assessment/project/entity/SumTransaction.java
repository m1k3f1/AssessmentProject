package assessment.project.entity;

import java.math.BigDecimal;

public class SumTransaction {
	Integer userId;
	BigDecimal sum;
	
	public SumTransaction() {
		
	}

	public SumTransaction(Integer userId, BigDecimal sum) {
		super();
		this.userId = userId;
		this.sum = sum;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

}
