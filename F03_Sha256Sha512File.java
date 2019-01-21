package net.bplaced.javacrypto.hashandmac;

/*
* Herkunft/Origin: http://javacrypto.bplaced.net/
* Programmierer/Programmer: Michael Fehr
* Copyright/Copyright: frei verwendbares Programm (Public Domain)
* Copyright: This is free and unencumbered software released into the public domain.
* Lizenttext/Licence: <http://unlicense.org>
* getestet mit/tested with: Java Runtime Environment 8 Update 191 x64
* getestet mit/tested with: Java Runtime Environment 11.0.1 x64
* Datum/Date (dd.mm.jjjj): 18.01.2019
* Funktion: errechnet den SHA-256 und SHA-512-Hashwert einer Datei
* Function: calculates the SHA-256 and SHA-512-hash of a file
*
* Sicherheitshinweis/Security notice
* Die Programmroutinen dienen nur der Darstellung und haben keinen Anspruch auf eine korrekte Funktion, 
* insbesondere mit Blick auf die Sicherheit ! 
* Prüfen Sie die Sicherheit bevor das Programm in der echten Welt eingesetzt wird.
* The program routines just show the function but please be aware of the security part - 
* check yourself before using in the real world !
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class F03_Sha256Sha512File {

	public static void main(String[] args) throws Exception {
		System.out.println("F03 SHA-256 bzw. SHA-512 Hashfunktion mit einer Datei");
				
		String filenameString = "a11_test_1mb.dat";
		byte[] hashByte = generateSha256(filenameString);
		System.out.println("\nSHA-256-Hashwert der Datei:" + filenameString);
		System.out.println(
				"hashByte Länge:" + hashByte.length + " Data:" + printHexBinary(hashByte));
		
		hashByte = generateSha512(filenameString);
		System.out.println("\nSHA-512-Hashwert der Datei:" + filenameString);
		System.out.println(
				"hashByte Länge:" + hashByte.length + " Data:" + printHexBinary(hashByte));
		
		System.out.println("\nF03 SHA-256 bzw. SHA-512 Hashfunktion mit einer Datei beendet");
	}

	public static byte[] generateSha256(String filenameString) throws IOException, NoSuchAlgorithmException {
		File file = new File(filenameString);
		byte[] bufferByte = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bufferByte);
		fis.close();
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(bufferByte);
		return md.digest();
	}
	public static byte[] generateSha512(String filenameString) throws IOException, NoSuchAlgorithmException {
		File file = new File(filenameString);
		byte[] bufferByte = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bufferByte);
		fis.close();
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(bufferByte);
		return md.digest();
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