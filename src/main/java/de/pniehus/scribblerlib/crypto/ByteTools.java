package de.pniehus.scribblerlib.crypto;

import javax.xml.bind.DatatypeConverter;

/**
 * This class contains method to work with byte[]
 * @author Phil Niehus
 *
 */
public class ByteTools {
	
	/**
	 * Returns the given byte array as hex string
	 * @param array
	 * @return
	 */
	public static String byteArrayToHexString(byte[] array){
		return DatatypeConverter.printHexBinary(array);
	}
	
	/**
	 * Returns the given hex string as byte array
	 * @param hex
	 * @return
	 */
	public static byte[] hexStringtoByteArray(String hex){
		return DatatypeConverter.parseHexBinary(hex);
	}
	
}
