package assessment.project.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import assessment.project.entity.AssessmentTransaction;
import assessment.project.entity.SumTransaction;
import assessment.project.utility.Utils;

public class PersistFileSystem implements Operations {

	@Override
	public List<String> add(AssessmentTransaction transaction) throws Exception {
		List<String> result = new ArrayList<String>();
		String secret = "";
		secret = Utils.generateSecretKey();
		transaction.setTransactionId(secret);

		Utils.writeToFile(transaction);
		result.add(Utils.jsonFromObject(transaction));

		return result;
	}

	@Override
	public List<String> list(Integer userId) throws Exception {
		List<String> result = new ArrayList<String>();
		List<AssessmentTransaction> list = new ArrayList<AssessmentTransaction>();
		list = Utils.readTransactions(userId);

		for (AssessmentTransaction transaction : list) {
			result.add(Utils.jsonFromObject(transaction));
		}
		return result;
	}

	@Override
	public List<String> sum(Integer userId) throws Exception {
		List<String> result = new ArrayList<String>();
		List<AssessmentTransaction> list = new ArrayList<AssessmentTransaction>();

		list = Utils.readTransactions(userId);
		BigDecimal add = BigDecimal.ZERO;
		for (AssessmentTransaction transaction : list) {
			add = add.add(transaction.getAmount());
		}

		SumTransaction sum = new SumTransaction();
		sum.setSum(add);
		sum.setUserId(userId);
		result.add(Utils.jsonFromObject(sum));

		return result;
	}

	@Override
	public List<String> show(Integer userId, String transactionId) throws Exception {
		List<String> result = new ArrayList<String>();
		List<AssessmentTransaction> list = new ArrayList<AssessmentTransaction>();
		list = Utils.readTransactions(userId);
		String request = transactionId.substring(0, 43) + "=";
		for (AssessmentTransaction transaction : list) {
			String original = transaction.getTransactionId();
			if (original.equals(request)) {
				result.add(Utils.jsonFromObject(transaction));
			}
		}
		return result;
	}

}
