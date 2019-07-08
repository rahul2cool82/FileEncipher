/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.encipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Ra
 */
public class Decrypto {
    public Decrypto(Key key, String ipfile, String opfile, String ext){
        
	File inputFile = new File(ipfile);
	//File encryptedFile = new File(opfile+"\\text.encrypted");
	File decryptedFile = new File(opfile+"\\decrypted file"+ext);
		
	try {
	     //Crypto.fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
	     Crypto.fileProcessor(Cipher.DECRYPT_MODE,key,inputFile,decryptedFile);
	     //System.out.println("Sucess");
	 } catch (Exception ex) {
	    //System.out.println(ex.getMessage());
             ex.printStackTrace();
	 }
       } 
       
       static void fileProcessor(int cipherMode,Key key,File inputFile,File outputFile){
	 try {
	       Key secretKey = new SecretKeySpec(key.getEncoded() , "AES");
	       Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	       cipher.init(cipherMode, secretKey);

	       FileInputStream inputStream = new FileInputStream(inputFile);
	       byte[] inputBytes = new byte[(int) inputFile.length()];
	       inputStream.read(inputBytes);

	       byte[] outputBytes = cipher.doFinal(inputBytes);

	       FileOutputStream outputStream = new FileOutputStream(outputFile);
	       outputStream.write(outputBytes);

	       inputStream.close();
	       outputStream.close();

	    } catch (Exception e) {
		e.printStackTrace();
            }
     }
    
}
