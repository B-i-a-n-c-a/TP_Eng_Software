package es1;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
//Crypto é usada apenas para encriptação e gerenciamento de acesso e não deve ser instanciada
public final class Crypto {

    private Crypto() {
        throw new UnsupportedOperationException("Esta classe não pode ser instanciada.");
    }

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

    public static String generateSalt(int length) {
        // Use SecureRandom for cryptographic-quality randomness
        SecureRandom random = new SecureRandom();

        // Create a byte array to hold the salt
        byte[] salt = new byte[length];
        // Fill the byte array with random bytes
        random.nextBytes(salt);

        // Encode the byte array to a Base64 string for easy storage and use
        return Base64.getEncoder().encodeToString(salt);
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
