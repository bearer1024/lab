package uk.ac.le.cs.CO3098.bean;
import java.security.*;
import java.math.*;

import java.security.MessageDigest;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

/**
 * Demonstrates how to generate MD5 hash using Java
 * @author JJ
 */
public class MD5HashGenerator {

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.print("Please enter data for which MD5 is required:");
        String data = sn.nextLine();
        
        MD5HashGenerator sj = new MD5HashGenerator();
        String hash = sj.getMD5Hash(data);
        System.out.println("The MD5 (hexadecimal encoded) hash is:"+hash);
    }

    /**
     * Returns a hexadecimal encoded MD5 hash for the input String.
     * @param data
     * @return 
     */
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