package com.token.md5tofile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class Md5ToFile {
	public static String getDigest(InputStream is, MessageDigest md, int byteArraySize)  
            throws NoSuchAlgorithmException,  
            IOException {  
		md.reset();  
		byte[] bytes = new byte[byteArraySize];  
		int numBytes;  
		while ((numBytes = is.read(bytes)) != -1) {  
		md.update(bytes, 0, numBytes);  
		}  
		byte[] digest = md.digest();  
		String result = new String(Hex.encodeHex(digest));  
		return result;  
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException{
	    MessageDigest md = MessageDigest.getInstance("MD5");  
	      
        String digest = Md5ToFile.getDigest(new FileInputStream("D:/glm/work/apache-tomcat-8.0.21/webapps/marketDoc/uploadtmp/apk/b7ade54f-8c10-4117-9b89-985e3816dc33.apk"), md, 2048);  
        System.out.println(digest);
	}
}
