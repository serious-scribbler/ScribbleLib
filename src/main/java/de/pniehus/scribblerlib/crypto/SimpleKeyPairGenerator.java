package de.pniehus.scribblerlib.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SimpleKeyPairGenerator {
	
	public static final int DEFAULT_KEY_LENGTH= 2048;
	
	private KeyPairGenerator keyPairGen;
	
	public SimpleKeyPairGenerator(int keyLength) throws NoSuchAlgorithmException {
		keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(keyLength, SecureRandom.getInstanceStrong());
	}
	
	public SimpleKeyPairGenerator() throws NoSuchAlgorithmException {
		this(DEFAULT_KEY_LENGTH);
	}
	
	public KeyPair generateKeyPair() {
		return keyPairGen.generateKeyPair();
	}
	
}
