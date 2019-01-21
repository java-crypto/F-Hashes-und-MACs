package net.bplaced.javacrypto.hashandmac;

/*
* Herkunft/Origin: http://javacrypto.bplaced.net/
* Programmierer/Programmer: Michael Fehr
* Copyright/Copyright: frei verwendbares Programm (Public Domain)
* Copyright: This is free and unencumbered software released into the public domain.
* Lizenttext/Licence: <http://unlicense.org>
* getestet mit/tested with: Java Runtime Environment 8 Update 191 x64
* getestet mit/tested with: Java Runtime Environment 11.0.1 x64
* Datum/Date (dd.mm.jjjj): 21.01.2019
* Funktion: errechnet den HMAC256 und HMAC512-Wert einer Datei
* Function: calculates the HMAC256 and HMAC512 value of a file
*
* Sicherheitshinweis/Security notice
* Die Programmroutinen dienen nur der Darstellung und haben keinen Anspruch auf eine korrekte Funktion, 
* insbesondere mit Blick auf die Sicherheit ! 
* Prüfen Sie die Sicherheit bevor das Programm in der echten Welt eingesetzt wird.
* The program routines just show the function but please be aware of the security part - 
* check yourself before using in the real world !
*/

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class F05_HmacFile {

	public static void main(String[] args) throws Exception {
		System.out.println("F05 HMAC 256 & 512 mit einer Datei");
				
		String filenameString = "a11_test_1mb.dat";
		byte[] hmacKeyByte = "1234567890".getBytes("utf-8");
		
		byte[] hmacByte = generateHmac256(filenameString, hmacKeyByte);
		System.out.println("\nHMAC256-Wert der Datei:" + filenameString);
		System.out.println(
				"hmacByte Länge:" + hmacByte.length + " Data:" + printHexBinary(hmacByte));
		
		hmacByte = generateHmac512(filenameString, hmacKeyByte);
		System.out.println("\nHMAC512-Wert der Datei:" + filenameString);
		System.out.println(
				"hmacByte Länge:" + hmacByte.length + " Data:" + printHexBinary(hmacByte));
		
		System.out.println("\nF05 HMAC 256 & 512 mit einer Datei beendet");
	}

	public static byte[] generateHmac256(String filenameString, byte[] keyByte) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
		byte[] resultMacByte = null;
		File file = new File(filenameString);
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
		Mac mac = Mac.getInstance("HmacSHA256"); // HmacSHA512
	    SecretKeySpec keySpec = new SecretKeySpec(keyByte, "HmacSHA256"); // HmacSHA512
		mac.init(keySpec);
	    byte[] content = new byte[1024];
	    int readSize;
	    while ((readSize = is.read(content)) != -1) {
	        mac.update(content, 0, readSize);
	    }
		resultMacByte = mac.doFinal();
		is.close();
		return resultMacByte;
	}
	
	public static byte[] generateHmac512(String filenameString, byte[] keyByte) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
		byte[] resultMacByte = null;
		File file = new File(filenameString);
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
		Mac mac = Mac.getInstance("HmacSHA512"); // HmacSHA256
	    SecretKeySpec keySpec = new SecretKeySpec(keyByte, "HmacSHA512"); // HmacSHA256
		mac.init(keySpec);
	    byte[] content = new byte[1024];
	    int readSize;
	    while ((readSize = is.read(content)) != -1) {
	        mac.update(content, 0, readSize);
	    }
		resultMacByte = mac.doFinal();
		is.close();
		return resultMacByte;
	}
	
	public static String printHexBinary(byte[] bytes) {
		final char[] hexArray = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
}