package assessment.project.transaction;

import java.util.List;

import assessment.project.entity.AssessmentTransaction;

public interface Operations {
	public List<String> add(AssessmentTransaction transaction) throws Exception;

	public List<String> list(Integer userId) throws Exception;

	public List<String> sum(Integer userId) throws Exception;

	public List<String> show(Integer userId, String transactionId) throws Exception;
}
