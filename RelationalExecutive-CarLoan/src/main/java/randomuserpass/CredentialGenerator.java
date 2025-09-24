package randomuserpass;

import java.util.Random;
import com.er.model.EREnquiry;

public class CredentialGenerator {

    public static void generateAndSetCredentials(EREnquiry enquiry) {
        String firstName = enquiry.getFirstName();
        String lastName = enquiry.getLastName();
        int age = enquiry.getAge();
        long mobileNo = enquiry.getMobileNo();

        // Username: first character of firstName + lastName + last 2 digits of mobile
        String mobileSuffix = String.valueOf(mobileNo);
        if (mobileSuffix.length() >= 2) {
            mobileSuffix = mobileSuffix.substring(mobileSuffix.length() - 2);
        } else {
            mobileSuffix = "00";
        }

        String username = (firstName.charAt(0) + lastName + mobileSuffix).toLowerCase();

        // Password: firstname + age + @ + random 4-digit number
        String password = firstName.toLowerCase() + age + "@" + String.format("%04d", new Random().nextInt(10000));

        enquiry.setCustomerUsername(username);
        enquiry.setCustomerPassword(password);
    }
}
