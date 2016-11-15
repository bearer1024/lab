package uk.ac.le.cs.CO3098.bean;

import java.security.MessageDigest;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

/**
 * Demonstrates how to generate hash using Java
 */
public class HashGenerator {

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.print("Please enter a string:");
        String data = sn.nextLine();
        
        HashGenerator hg = new HashGenerator();
        String hash = hg.getMD5Hash(data);
        System.out.println("The MD5 hash is:"+hash);
 
         hash = hg.getSHA256(data);
        System.out.println("The SHA256 hash is:"+hash);
 
        
    }

    /**
     * Returns a hexadecimal encoded SHA256 hash for the input String.
     * @param data
     * @return 
     */
    
    public static String getSHA256(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    		
    		
    public static String getMD5Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    /**
     * Use javax.xml.bind.DatatypeConverter class in JDK to convert byte array
     * to a hexadecimal string. Note that this generates hexadecimal in upper case.
     * @param hash
     * @return 
     */
    private static String bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    } 
    
}