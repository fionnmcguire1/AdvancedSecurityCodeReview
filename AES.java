/************************************************************/
/*** Date : Dec, 12th, 2011                               ***/
/*** Description: The implementation of Block cipher Mode ***/
/***              AES to generate a random number         ***/
/************************************************************/


import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.math.*;
import java.util.*;


public class AES
{
	BlumBlum blumblum;
	BigInteger initNum;
	public static byte[] byteRandomNum;
	
	public AES()
	{
		blumblum = new BlumBlum();
		
		// pick up the initial random number whose length can be divided by 16 with no remainder
		while(true)
		{
			initNum = blumblum.generateRondomNumber();
			System.out.println(initNum.toByteArray().length % 16);
			if(((initNum.toByteArray().length) % 16) == 0)
				break;
		}
		byteRandomNum = initNum.toByteArray();
	}
	
	public static BigInteger generateRondomNumber2()
	{	            
		try
		{
			// using Java Api to implement AES encryption
			KeyGenerator kgen = KeyGenerator.getInstance("AES");  
			kgen.init(128);  
			SecretKey secretKey = kgen.generateKey(); 
			
			// Nopadding to prevent the number's length become longer and longer
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding"); 
			
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			
			byte[] temp = cipher.doFinal(byteRandomNum); 
			
			byteRandomNum = temp;
			
			BigInteger result = new BigInteger(temp);
			
			result = result.abs();
			
			return result;
		} catch (NoSuchAlgorithmException e) {  
                e.printStackTrace();  
        } catch (NoSuchPaddingException e) {  
                e.printStackTrace();  
        } catch (InvalidKeyException e) {  
                e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
                e.printStackTrace();  
        } catch (BadPaddingException e) {  
                e.printStackTrace();  
        }  
		return null;
	}
}




