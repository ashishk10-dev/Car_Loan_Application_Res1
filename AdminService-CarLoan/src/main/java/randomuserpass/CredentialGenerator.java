package randomuserpass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class CredentialGenerator {

	 public static String generateUsername(String firstName, String lastName, String dobString) {
	        LocalDate dob = LocalDate.parse(dobString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        return (firstName.charAt(0) + lastName + dob.getYear()).toLowerCase();
	    }

	    public static String generatePassword(String firstName, String dobString) {
	        LocalDate dob = LocalDate.parse(dobString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        String base = firstName.toLowerCase() + dob.getDayOfMonth() + "@";
	        return base + String.format("%04d", new Random().nextInt(10000));
	    }
		
}
