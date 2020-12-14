package com.example.demo.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.*;

public class MD5Util {
    /**
     * * @Description MD5 , plainText.getBytes("UTF-8") * @param plainText *
     * * @return
     */
    public static String toMD5(String plainText) {
        StringBuffer rlt = new StringBuffer();
        try {
            rlt.append(md5String(plainText.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            System.out.println(" CipherHelper toMD5 exception.");
            e.printStackTrace();
        }
        return rlt.toString();
    }

    /**
     * MD5
     * @param params  HashMap<String,String>
     * @param secret String
     * @return
     * @throws IOException
     */
    public static String getSignature(HashMap<String, String> params, String secret) {
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, String> param : entrys) {
            basestring.append(param.getKey()).append("=").append(param.getValue());
        }
        return getSignature(basestring.toString(), secret);
    }

    /**
     * MD5
     * @param sigstr
     * @param secret
     * @return
     */
    public static String getSignature(String sigstr, String secret) {
        StringBuilder basestring = new StringBuilder(sigstr);
        basestring.append("#");
        basestring.append(toMD5(secret));
        return toMD5(basestring.toString());
    }

    public static byte[] md5Raw(byte[] data) {
        byte[] md5buf = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5buf = md5.digest(data);
        } catch (Exception e) {
            md5buf = null;
            System.out.println("md5Raw error.");
            e.printStackTrace();
        }
        return md5buf;
    }

    public static String md5String(byte[] data) {
        String md5Str = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5Str = "";
            byte[] buf = md5.digest(data);
            for (int i = 0; i < buf.length; i++) {
                md5Str += byte2Hex(buf[i]);
            }
        } catch (Exception e) {
            md5Str = null;
            System.out.println("md5String error.");
            e.printStackTrace();
        }
        return md5Str;
    }

    public static String byte2Hex(byte b) {
        String hex = Integer.toHexString(b);
        if (hex.length() > 2) {
            hex = hex.substring(hex.length() - 2);
        }
        while (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    public static String byte2Hex(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        String hash = formatter.toString();
        formatter.close();
        return hash;
    }
}
