
public class utils {
	public static int MIN_LENGTH = 8;
	
	/*
	 * contains logic for which characters are allowed
	 * @param c character to be checked
	 * @return true if c is allowed, false otherwise
	 */
	public static boolean isValidChar(char c) {
		if (c < '!' || c > '~')
			return false;
		return true;
	}
	
	/*
	 * checks if pwd is a valid password according to the following rules:
	 * at least one lowercase letter, uppercase letter, number, and special
	 * character, and all characters must be valid as defined in isValidChar
	 * @param pwd password to be verified
	 * @return true if pwd is valid, false otherwise
	 */
	public static boolean isValid(String pwd) {
		if (pwd.length() < MIN_LENGTH)
			return false;
		boolean hasLower = false;
		boolean hasUpper = false;
		boolean hasNumber = false;
		boolean hasSpecial = false;
		char current;
		for (int i = 0; i < pwd.length(); ++i) {
			current = pwd.charAt(i);
			if(!utils.isValidChar(current))
				return false;
			else if(current >= 'a' && current <= 'z') //range of lowercase letters
				hasLower = true;
			else if(current >= 'A' && current <= 'Z') //range of uppercase letters
	            hasUpper = true;
	        else if(current >= '0' && current <= '9') //range of numbers
	            hasNumber = true;
	        else //if it's valid, and not a letter or number, it's a special char
	            hasSpecial = true;
		}
		
		if(hasLower && hasUpper && hasNumber && hasSpecial) //all conditions met
	        return true;
	    return false;
	}
}
