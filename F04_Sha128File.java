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
* Funktion: errechnet den SHA-128 Hashwert einer Datei
* Function: calculates the SHA-128-hash of a file
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
import java.util.Arrays;

public class F04_Sha128File {

	public static void main(String[] args) throws Exception {
		System.out.println("F04 SHA-128 Hashfunktion mit einer Datei");
		System.out.println("\nHinweis: Dieses ist keine offizielle Implementierung"
				+ "\nsondern eine Möglichkeit, aus einem 32 Byte langen Array"
				+ "\nein 16 Byte langes Array zu erzeugen !");
		String filenameString = "a11_test_1mb.dat";
		byte[] hashByte = generateSha128(filenameString);
		System.out.println("\nSHA-128-Hashwert der Datei:" + filenameString);
		System.out.println("hashByte Länge:" + hashByte.length + " Data:" + printHexBinary(hashByte));

		System.out.println("\nF04 SHA-128 Hashfunktion mit einer Datei beendet");
	}

	public static byte[] generateSha128(String filenameString) throws IOException, NoSuchAlgorithmException {
		// Achtung: das ist keine offizielle Implementierung
		File file = new File(filenameString);
		byte[] bufferByte = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bufferByte);
		fis.close();
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(bufferByte);
		return Arrays.copyOf(md.digest(), 16);
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