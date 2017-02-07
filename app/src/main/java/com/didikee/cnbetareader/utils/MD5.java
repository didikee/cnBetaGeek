package com.didikee.cnbetareader.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by didik 
 * Created time 2017/2/7
 * Description: 
 */

public class MD5 {
    public static String md5(String content){
        byte[] result;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(content.getBytes("UTF-8"));
            result = messageDigest.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return "";
        }
        return byte2Hex(result);
    }
    private static String byte2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b: bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = "0" + hex;   // one byte to double-digit hex
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
