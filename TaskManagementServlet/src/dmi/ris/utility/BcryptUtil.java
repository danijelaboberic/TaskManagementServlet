package dmi.ris.utility;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class BcryptUtil {
	public static String hash(String password) {
		return BCrypt.withDefaults().hashToString(12, password.toCharArray());
	}
	public static boolean verifiy(String password, String bcryptHashString) {
		BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
		return result.verified;
	}

	public static void main(String[] args) {
		System.out.println(BcryptUtil.hash("123456"));
	}
}
