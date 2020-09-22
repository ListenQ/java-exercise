package com.example.demo.test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;

import org.bouncycastle.util.Integers;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;

public class SmUtils {
	
	
//	 ecParameters.put(Integers.valueOf(192), new ECGenParameterSpec("prime192v1")); // a.k.a P-192
//     ecParameters.put(Integers.valueOf(239), new ECGenParameterSpec("prime239v1"));
//     ecParameters.put(Integers.valueOf(256), new ECGenParameterSpec("prime256v1")); // a.k.a P-256
//
//     ecParameters.put(Integers.valueOf(224), new ECGenParameterSpec("P-224"));
//     ecParameters.put(Integers.valueOf(384), new ECGenParameterSpec("P-384"));
//     ecParameters.put(Integers.valueOf(521), new ECGenParameterSpec("P-521"));
	
	private static final String pubKey = "3059301306072A8648CE3D020106082A811CCF5501822D0342000413832c75c9793c8705d626ab14bf616a191a324af31ce7fc7fe08723ca4983efb3d7cc6c62b9ee128e747544fe67a9ad669ee976e67c61f7269134922b3d4e7f";
	private static final String prikey = "";
	private static final String key = "Zhuorui@2019e7eKW1xyj@!r&fyG51fa";
	
	
	public static void main(String[] args) {
		
		KeyPair pair = SecureUtil.generateKeyPair("SM2");
		byte[] bs2 = pair.getPublic().getEncoded();
		byte[] bs = pair.getPrivate().getEncoded();
		String pubStr = HexUtil.encodeHexStr(bs2);
		String priStr = HexUtil.encodeHexStr(bs);
		System.out.println("公钥："+priStr+"\n私钥："+pubStr);
		
//		String encyStr = "02abe32b0d16d35466e90c02d2bae390359f4911c2e04bea712ebf565d622df9a3edbe309ea8a81a7bd211b6bdb1798bafb475f7c296b39f6e2fe69cd457613ea09427b0f757567df0a67a7b11e098e4b06f07a39df9fe9a4386d00f";
		SM2 sm22 = SmUtil.sm2(null, pubKey);
		
		String encrypt = sm22.encryptHex("123456", KeyType.PublicKey);
		System.out.println(encrypt);
		
//		String utf8Str2 = StrUtil.utf8Str(sm22.decryptStr(encyStr, KeyType.PrivateKey));
//		System.out.println("sm2自定义秘钥解密后："+utf8Str2);
		
		
		
	}


}
