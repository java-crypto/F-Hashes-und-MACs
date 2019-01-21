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
* Funktion: leitet 2 Passwörter mittels HKDF256 ab
* Function: derives 2 passwords using HKDF256
*
* Sicherheitshinweis/Security notice
* Die Programmroutinen dienen nur der Darstellung und haben keinen Anspruch auf eine korrekte Funktion, 
* insbesondere mit Blick auf die Sicherheit ! 
* Prüfen Sie die Sicherheit bevor das Programm in der echten Welt eingesetzt wird.
* The program routines just show the function but please be aware of the security part - 
* check yourself before using in the real world !
* 
* Das Programm benötigt die nachfolgende Bibliothek:
* The programm uses this external library:
* source code: https://github.com/patrickfav/hkdf
* jar-Datei: https://mvnrepository.com/artifact/at.favre.lib/hkdf
* 
*/

import java.nio.charset.StandardCharsets;
import at.favre.lib.crypto.HKDF;

public class F06_Hkdf256ByteArray {

	public static void main(String[] args) throws Exception {
		System.out.println("F06 HKDF-256 mit einem Byte Array");

		String passwordString = "wenig Entropie";
		byte[] passwordByte = passwordString.getBytes("utf-8");
		System.out.println("\npasswordByte Länge:" + passwordByte.length + " Data:" + printHexBinary(passwordByte));
		byte[] saltByte = new byte[32];

		String usageString = "aes-cbc";
		byte[] hkdfByte = generateHkdf256(passwordByte, usageString, saltByte, 16);
		System.out.println("\nHKDF-256-Wert für die Anwendung:" + usageString);
		System.out.println("HKDFByte Länge:" + hkdfByte.length + " Data:" + printHexBinary(hkdfByte));

		usageString = "hmac";
		hkdfByte = generateHkdf256(passwordByte, usageString, saltByte, 16);
		System.out.println("\nHKDF-256-Wert für die Anwendung:" + usageString);
		System.out.println("HKDFByte Länge:" + hkdfByte.length + " Data:" + printHexBinary(hkdfByte));

		System.out.println("\nF06 HKDF-256 mit einem Byte Array beendet");
	}

	public static byte[] generateHkdf256(byte[] rawPasswordByte, String usageString, byte[] staticSaltByte,
			int outputLengthInt) {
		HKDF hkdf = HKDF.fromHmacSha256(); // .fromHmacSha256 oder .fromHmacSha512
		// extract the "raw" data to create output with concentrated entropy
		byte[] pseudoRandomKey = hkdf.extract(staticSaltByte, rawPasswordByte);
		// create expanded bytes for e.g. AES secret key and IV
		return hkdf.expand(pseudoRandomKey, usageString.getBytes(StandardCharsets.UTF_8), outputLengthInt);
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