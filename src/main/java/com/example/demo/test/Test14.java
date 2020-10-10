package com.example.demo.test;

import java.security.KeyPair;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.GlobalBouncyCastleProvider;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

public class Test14 {
	
	private static String content = "test文字123";
	private static String key = "Zhuorui@2019e7eKW1xyj@!r&fyG51fa";

	public static void main(String[] args) {

		//TODO sm3摘要加密算法
		String digestHex = SmUtil.sm3(content);
		System.out.println("sm3加密后："+digestHex);

		////////////////////////////////////////////////////////////////
		
		//TODO sm4对称加密 ，一定要128bit秘钥 ==> 32位字符串 ==> 16位的byte数组
		byte[] stringToBytes = hexStringToBytes(key);
		String sm4 = SmUtil.sm4(stringToBytes).encryptHex(content);
		System.out.println("sm4固定秘钥加密后："+sm4);
		
		SymmetricCrypto crypto = SmUtil.sm4();
		String sm42 = crypto.encryptHex(content);
		System.out.println("sm4随机秘钥加密后："+sm42);
		
		String decryptStr = SmUtil.sm4(stringToBytes).decryptStr(sm4);
		System.out.println("sm4固定秘钥解密后："+decryptStr);
		
		//随机秘钥须使用加密的SymmetricCrypto对象来解密才行
		String decryptStr2 = crypto.decryptStr(sm42);
		System.out.println("sm4随机秘钥解密后："+decryptStr2);
		
		////////////////////////////////////////////////////////////////
		
		//TODO sm2 非对称加密
		//使用随机生成的密钥对加密或解密
		SM2 sm2 = SmUtil.sm2();
		
		// 公钥加密，私钥解密
		String encryptBcd = sm2.encryptBcd(content, KeyType.PublicKey);
		System.out.println("sm2随机秘钥加密后："+encryptBcd+"**"+encryptBcd.length());
		String utf8Str = StrUtil.utf8Str(sm2.decryptFromBcd(encryptBcd, KeyType.PrivateKey));
		System.out.println("sm2随机秘钥解密后："+utf8Str);
		
		// 生成秘钥key
		KeyPair pair = SecureUtil.generateKeyPair("SM2");
		byte[] privateKey = pair.getPrivate().getEncoded();
		byte[] publicKey = pair.getPublic().getEncoded();
		String pubStr = bytesToHexString(publicKey);
		String priStr = bytesToHexString(privateKey);
		System.out.println("公钥："+pubStr+"\n私钥："+priStr);
		// 使用自定义密钥对加密或解密
		SM2 sm22 = SmUtil.sm2(priStr, pubStr);
		String encryptBcd2 = sm22.encryptHex(content, KeyType.PublicKey);
		System.out.println("sm2自定义秘钥加密后："+encryptBcd2);
		String utf8Str2 = StrUtil.utf8Str(sm22.decryptStr(encryptBcd2, KeyType.PrivateKey));
		System.out.println("sm2自定义秘钥解密后："+utf8Str2);
		
	}



	public static String bytesToHexString(byte[] src){ 
		StringBuilder stringBuilder = new StringBuilder(""); 
		if (src == null || src.length <= 0) { 
			return null; 
		} 
		for (int i = 0; i < src.length; i++) { 
			int v = src[i] & 0xFF; 
			String hv = Integer.toHexString(v); 
			if (hv.length() < 2) { 
				stringBuilder.append(0); 
			} 
			stringBuilder.append(hv); 
		} 
		return stringBuilder.toString(); 
	} 

	public static byte[] hexStringToBytes(String hexString) { 
		if (hexString == null || hexString.equals("")) { 
			return null; 
		} 
		hexString = hexString.toUpperCase(); 
		int length = hexString.length() / 2; 
		char[] hexChars = hexString.toCharArray(); 
		byte[] d = new byte[length]; 
		for (int i = 0; i < length; i++) { 
			int pos = i * 2; 
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1])); 
		} 
		return d; 
	} 


	private static byte charToByte(char c) { 
		return (byte) "0123456789ABCDEF".indexOf(c); 
	} 
	
	
	
	/**
	 * 字符串转换成为16进制(无需Unicode编码)
	 * @param str
	 * @return
	 */
	public static String str2HexStr(String str) {
	    char[] chars = "0123456789ABCDEF".toCharArray();
	    StringBuilder sb = new StringBuilder("");
	    byte[] bs = str.getBytes();
	    int bit;
	    for (int i = 0; i < bs.length; i++) {
	        bit = (bs[i] & 0x0f0) >> 4;
	        sb.append(chars[bit]);
	        bit = bs[i] & 0x0f;
	        sb.append(chars[bit]);
	        // sb.append(' ');
	    }
	    return sb.toString().trim();
	}
	
	
	/**
	 * 16进制直接转换成为字符串(无需Unicode解码)
	 * @param hexStr
	 * @return
	 */
	public static String hexStr2Str(String hexStr) {
	    String str = "0123456789ABCDEF";
	    char[] hexs = hexStr.toCharArray();
	    byte[] bytes = new byte[hexStr.length() / 2];
	    int n;
	    for (int i = 0; i < bytes.length; i++) {
	        n = str.indexOf(hexs[2 * i]) * 16;
	        n += str.indexOf(hexs[2 * i + 1]);
	        bytes[i] = (byte) (n & 0xff);
	    }
	    return new String(bytes);
	}
	
	

}
