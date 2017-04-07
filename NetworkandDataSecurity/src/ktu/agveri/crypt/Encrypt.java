package ktu.agveri.crypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.Charset;
import ktu.agveri.crypt.Md5Encryption;

public  class Encrypt {

	Encrypt() {
		String text = ReadText(); 
		BigInteger n = new BigInteger(GetN()); 
		Encrypt(text, n);
	}

	public String ReadText() {
		String inFile = "Input.txt"; 
		String line = ""; 
		String text = ""; 
		
		try {
			FileReader reader = new FileReader(inFile); 
			BufferedReader bReader = new BufferedReader(reader);
			
			try {
				while ((line = bReader.readLine()) != null) {
					text = text + line;
				}
				//System.out.println("Successfully read plaintext from file!");
				bReader.close();
				return text;
			} catch (IOException e) {
				//System.out.println("Error reading file!");
			}

		} catch (FileNotFoundException e) {
			//System.out.println("File not found or does not exist!");
		}
                
		return text;
	}

	public String GetN() {
		String inFile = "Public.txt"; 
		String line = ""; 
		try {
			FileReader reader = new FileReader(inFile); 
			BufferedReader bReader = new BufferedReader(reader);

			try {
				line = bReader.readLine();
				bReader.close();
				return line;
			} catch (IOException e) {
				//System.out.println("Error reading file!");
			}
		} catch (FileNotFoundException e) {
			//System.out.println("File not found or does not exist!");
		}

		// Should never get here, keeps the compiler happy
		return line;
	}

	public void Encrypt(String text, BigInteger n) {
		int pos = 1; // Position of the char we are at (non-zero based)
		String toEncrypt = ""; 
                Md5Encryption md5 = new Md5Encryption();
                text = md5.hash(text);
		// Try to create and write to the file
		try {
			File outFile = new File("Encrypted.txt"); 
                        PrintWriter writer = new PrintWriter(outFile); 	
			BigInteger message = new BigInteger(text.getBytes(Charset.forName("ascii")));
			// System.out.println(message);
			final BigInteger TWO = new BigInteger("2");
			BigInteger ciphertext = message.modPow(TWO, n);

			writer.print(ciphertext);

			writer.close();
			//System.out.println("Successfully encrypted plaintext!");
		} catch (FileNotFoundException e) {
			//System.out.print("File not found or does not exist!");
		}
	}

	/*public static void main(String args[]) {
		Encrypt encryption = new Encrypt();
	}*/

}
