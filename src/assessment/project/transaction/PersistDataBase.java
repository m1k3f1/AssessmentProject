package assessment.project.transaction;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import assessment.project.entity.AssessmentTransaction;
import assessment.project.entity.SumTransaction;
import assessment.project.hibernate.HibernateUtil;
import assessment.project.utility.Utils;

public class PersistDataBase implements Operations {

	@Override
	public List<String> add(AssessmentTransaction transaction) throws NoSuchAlgorithmException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<String> result = new ArrayList<String>();

		transaction.setTransactionId(Utils.generateSecretKey());
		session.save(transaction);

		tx.commit();
		result.add(Utils.jsonFromObject(transaction));

		return result;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> list(Integer userId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		Query query = null;
		StringBuilder builder = new StringBuilder();

		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		builder.append("FROM AssessmentTransaction WHERE userId = :userId");
		query = session.createSQLQuery(builder.toString());
		
		query.setParameter("userId", userId);
		List<AssessmentTransaction> transaction = (List<AssessmentTransaction>) query.list();
		tx.commit();
		session.close();
		
		List<String> list = new ArrayList<String>();
		for (AssessmentTransaction transact : transaction) {
			list.add(Utils.jsonFromObject(transact));
		}

		return list;
	}

	@Override
	public List<String> sum(Integer userId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		Query query = null;
		StringBuilder builder = new StringBuilder();
		SumTransaction sum = new SumTransaction();

		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		builder.append("SELECT sum(amount) FROM AssessmentTransaction WHERE userId = :userId");
		query = session.createSQLQuery(builder.toString());
		
		query.setParameter("userId", userId);
		
		BigDecimal result = (BigDecimal) query.list();
		
		sum.setSum(result);
		sum.setUserId(userId);
		tx.commit();
		session.close();
		
		List<String> list = new ArrayList<String>();
		if (sum != null) {
			list.add(Utils.jsonFromObject(sum));
		}
		
		
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> show(Integer userId, String transactionId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		Query query = null;
		StringBuilder builder = new StringBuilder();

		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		builder.append("FROM AssessmentTransaction WHERE userId = :userId and transactionId = :transactionId");
		query = session.createSQLQuery(builder.toString());
		
		query.setParameter("userId", userId);
		query.setParameter("transactionId", transactionId);
		
		List<AssessmentTransaction> transaction = (List<AssessmentTransaction>) query.list();
		tx.commit();
		session.close();
		
		List<String> list = new ArrayList<String>();
		if (transaction != null) {
			for (AssessmentTransaction transact : transaction) {
				list.add(Utils.jsonFromObject(transact));
			}
		}

		return list;
	}

}
