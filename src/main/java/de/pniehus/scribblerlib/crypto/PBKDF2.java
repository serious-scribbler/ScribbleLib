package de.pniehus.scribblerlib.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


/**
 * This class contains an implementation of PBKDF2
 * @author Phil Niehus
 *
 */
public class PBKDF2 {
	
	/**
	 * The recommended number of iterations for PBKDF2 (@link https://nakedsecurity.sophos.com/2016/08/18/nists-new-password-rules-what-you-need-to-know/)
	 */
	public static final int PBKDF2_ITERATIONS = 10000;
	/**
	 * The recommended salt length in bytes for PBKDF2 (@link https://nakedsecurity.sophos.com/2016/08/18/nists-new-password-rules-what-you-need-to-know/)
	 */
	public static final int PBKDF2_SALT_LENGTH = 16;
	/**
	 * The recommended derived key length for PBKDF2 (@link https://nakedsecurity.sophos.com/2016/08/18/nists-new-password-rules-what-you-need-to-know/)
	 */
	public static final int PBKDF2_DERIVED_KEY_LENGTH = 4096;
	
	/**
	 * Returns a PBKDF2 hash for the given password
	 * @param password
	 * @param salt The salt that is applied for hashing
	 * @param iterations The number of iterations
	 * @param derivedKeyLength The length of the key derived from the password and salt
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static byte[] getPBKDF2Hash(String password, byte[] salt, int iterations, int derivedKeyLength) throws NoSuchAlgorithmException, InvalidKeySpecException{
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		return f.generateSecret(spec).getEncoded();
	}
	
	/**
	 * Returns a secure random salt with the length of n
	 * @param n
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] getSecureRandomSalt(int n) throws NoSuchAlgorithmException{
		byte[] salt = new byte[n];
		SecureRandom.getInstanceStrong().nextBytes(salt);
		return salt;
	}
	
	/**
	 * Returns a PBKDF2Hash for the given password and salt using the recommended parameters
	 * @param password
	 * @param salt
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static String getPBKDF2Hash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException{
		return ByteTools.byteArrayToHexString(getPBKDF2Hash(password, salt, PBKDF2_ITERATIONS, PBKDF2_DERIVED_KEY_LENGTH));
	}
}
