import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.nio.ByteBuffer;

public class GCMEncryption {

    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 256; // bits
    private static final int TAG_LENGTH = 128; // bits (common for GCM)
    private static final int IV_LENGTH = 12; // bytes (96 bits - common for GCM)

    public static byte[] encrypt(byte[] plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        byte[] iv = new byte[IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] ciphertext = cipher.doFinal(plaintext);

        // Prepend the IV to the ciphertext
        ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + ciphertext.length);
        byteBuffer.put(iv);
        byteBuffer.put(ciphertext);
        return byteBuffer.array();
    }

    public static byte[] decrypt(byte[] encryptedData, SecretKey key) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.wrap(encryptedData);
        byte[] iv = new byte[IV_LENGTH];
        byteBuffer.get(iv);
        byte[] ciphertext = new byte[byteBuffer.remaining()];
        byteBuffer.get(ciphertext);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        return cipher.doFinal(ciphertext);
    }

    public static void main(String[] args) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(KEY_SIZE, new SecureRandom());
        SecretKey secretKey = keyGen.generateKey();

        String originalText = "This is some confidential data!";
        byte[] plaintext = originalText.getBytes("UTF-8");

        byte[] encrypted = encrypt(plaintext, secretKey);
        System.out.println("Encrypted data (IV + Ciphertext): " + Arrays.toString(encrypted));

        byte[] decrypted = decrypt(encrypted, secretKey);
        String decryptedText = new String(decrypted, "UTF-8");
        System.out.println("Decrypted text: " + decryptedText);

        // Example of what happens with an incorrect key:
        KeyGenerator keyGen2 = KeyGenerator.getInstance("AES");
        keyGen2.init(KEY_SIZE, new SecureRandom());
        SecretKey wrongKey = keyGen2.generateKey();

        try {
            byte[] attemptDecryptWrongKey = decrypt(encrypted, wrongKey);
            String wrongDecryptedText = new String(attemptDecryptWrongKey, "UTF-8");
            System.out.println("Decrypted with wrong key: " + wrongDecryptedText);
        } catch (javax.crypto.AEADBadTagException e) {
            System.err.println("Decryption failed (as expected) due to: " + e.getMessage());
        }
    }
}
