package assessment.project.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;

import assessment.project.entity.AssessmentTransaction;
import assessment.project.transaction.Operations;
import assessment.project.transaction.PersistDataBase;
import assessment.project.transaction.PersistFileSystem;

public class Main {

	public static void main(String[] args) {
		if (args.length > 1) {
			AssessmentTransaction transaction = new AssessmentTransaction();
			String operator = args[1];
			String transaction_id = "";
			String json = "";
			int persist = 0;

			Properties properties = new Properties();
			try {
				properties.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("resources/properties/app.properties"));
				persist = Integer.parseInt(properties.getProperty("assessment.persist"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (operator.equalsIgnoreCase("ADD")) {
				operator = args[1];
				for (int i = 2; i < args.length; i++) {
					json = json + " " + args[i];
				}
				Gson gson = new Gson();
				json = json.trim().replace("“", "\"").replace("”", "\"");
				transaction = gson.fromJson(json, AssessmentTransaction.class);
			} else {
				if (operator.length() > 4) {
					operator = "SHOW";
				}
				transaction_id = args[1];
			}

			transaction.setUserId(Integer.parseInt(args[0]));
			List<String> result = new ArrayList<String>();
			if (persist == 1) {
				Operations operations = new PersistFileSystem();
				if (operator.equalsIgnoreCase("ADD")) {
					try {
						result = operations.add(transaction);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (operator.equalsIgnoreCase("LIST")) {
					try {
						result = operations.list(transaction.getUserId());
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (operator.equalsIgnoreCase("SUM")) {
					try {
						result = operations.sum(transaction.getUserId());
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (operator.equalsIgnoreCase("SHOW")) {
					try {
						result = operations.show(transaction.getUserId(), transaction_id);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					result.add("Invalid operation");
				}

			} else {
				Operations operations = new PersistDataBase();
				if (operator.equalsIgnoreCase("ADD")) {
					try {
						try {
							result = operations.add(transaction);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (operator.equalsIgnoreCase("LIST")) {
					try {
						result = operations.list(transaction.getUserId());
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (operator.equalsIgnoreCase("SUM")) {
					try {
						result = operations.sum(transaction.getUserId());
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (operator.equalsIgnoreCase("SHOW")) {
					try {
						result = operations.show(transaction.getUserId(), transaction_id);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					result.add("Invalid operation");
				}
			}
			if (result.size() > 0) {
				for (String item : result) {
					System.out.println(item);
				}
			} else {
				System.out.println("Transaction not found");
			}

		} else {
			System.out.println("Number of parameters incorrect, please try again");
		}
	}
}