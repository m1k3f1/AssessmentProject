package assessment.project.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		System.out.println("SessionFactory start");
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				System.out.println("SessionFactory load correctly");
			} catch (Exception e) {
				System.out.println("SessionFactory fail to load");
				e.printStackTrace();
				System.out.println(e);
			}
		}

		return sessionFactory;
	}

}
