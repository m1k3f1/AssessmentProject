package assessment.project.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.google.gson.Gson;

import assessment.project.entity.AssessmentTransaction;
import assessment.project.entity.SumTransaction;

public class Utils {

	public static String generateSecretKey() throws NoSuchAlgorithmException {
		String generatedKey = "";
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		generator.init(256);
		SecretKey secretKey = generator.generateKey();
		generatedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

		return generatedKey;
	}

	public static void writeToFile(AssessmentTransaction transaction) throws Exception {
		File file = null;
		file = verifyFile(transaction.getUserId());

		FileInputStream fis = new FileInputStream(file);
		List<AssessmentTransaction> result = new ArrayList<AssessmentTransaction>();
		if (fis.available() > 0) {
			ObjectInputStream ois = new ObjectInputStream(fis);
			boolean notNull = true;
			while (notNull) {
				Object obj = null;
				try {
					obj = ois.readObject();
				} catch (Exception e) {
				}
				if (obj != null) {
					result.add((AssessmentTransaction) obj);
				} else {
					notNull = false;
				}
			}
			ois.close();
		}

		FileOutputStream outputStream = new FileOutputStream(file);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		for (AssessmentTransaction assessmentTr : result) {
			objectOutputStream.writeObject(assessmentTr);
		}
		objectOutputStream.writeObject(transaction);
		objectOutputStream.close();

	}

	public static List<AssessmentTransaction> readTransactions(Integer name) throws Exception {
		File file = null;
		try {
			file = verifyFile(name);
		} catch (Exception e) {
			e.printStackTrace();
		}

		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		List<AssessmentTransaction> result = new ArrayList<AssessmentTransaction>();
		boolean notNull = true;
		while (notNull) {
			Object obj = null;
			try {
				obj = ois.readObject();
			} catch (Exception e) {
			}
			if (obj != null) {
				result.add((AssessmentTransaction) obj);
			} else {
				notNull = false;
			}
		}
		ois.close();

		return result;
	}

	public static String jsonFromObject(AssessmentTransaction transaction) {
		Gson gSon = new Gson();
		String result = gSon.toJson(transaction);

		return result;
	}

	public static String jsonFromObject(SumTransaction transaction) {
		Gson gSon = new Gson();
		String result = gSon.toJson(transaction);

		return result;
	}

	public static File verifyFile(Integer name) throws Exception {
		String path = Utils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		File result = null;
		File file = new File(path + name);
		if (!file.exists()) {
			if (!file.createNewFile())
				throw new Exception("Error: not posible to create file [" + file + "].");
			result = file;
		} else {
			result = file;
		}
		file = null;
		return result;
	}

}
