import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	private String username;
	private byte[] passwordHash;
	private static String hashFunc = "SHA-256";
	
	/*
	 * Creates a new user; expects valid password
	 * @param uname username
	 * @param pwd password
	 * @return true if successful, false otherwise
	 */
	public User (String uname, String pwd) {
		username = uname;
		MessageDigest myDigest;
		try {
			myDigest = MessageDigest.getInstance(hashFunc);
			passwordHash = myDigest.digest(pwd.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * checks if an attempted username matches the true username
	 * @param uname attempted username
	 * @return true if match false otherwise
	 */
	public boolean checkUname (String uname) {
		if(uname.equals(username))
			return true;
		return false;
	}
	
	/*
	 * checks if an attempted password matches the true password
	 * @param uname attempted password
	 * @return true if match false otherwise
	 */
	public boolean checkPwd (String pwd) {
		MessageDigest myDigest;
		try {
			myDigest = MessageDigest.getInstance(hashFunc);
			byte[] attemptHash = myDigest.digest(pwd.getBytes());
			if(MessageDigest.isEqual(attemptHash, passwordHash))
				return true;
			else
				return false;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
	}
}
