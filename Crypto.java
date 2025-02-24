import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

public class Crypto {

    // PBKDF2 function that takes a password and salt, and returns the salted hash
    public static String pbkdf2Hash(String password, String salt) {
        try {
            // Convert salt to bytes
            byte[] saltBytes = salt.getBytes();

            // Define the iteration count and key length
            int iterations = 1000;
            int keyLength = 256;

            // Create a PBEKeySpec from the password and salt
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, keyLength);

            // Get a SecretKeyFactory instance for PBKDF2
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            // Generate the hash
            byte[] hash = skf.generateSecret(spec).getEncoded();

            // Return the hash as a Base64 encoded string
            return Base64.getEncoder().encodeToString(hash);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Example of how to use this function
    public static void testHash() {
        // Sample password and salt
        String password = "mySecretPassword";
        String salt = "mySaltValue";

        // Hash the password
        String hashedPassword = pbkdf2Hash(password, salt);

        // Output the result
        System.out.println("Hashed password: " + hashedPassword);
    }
}
