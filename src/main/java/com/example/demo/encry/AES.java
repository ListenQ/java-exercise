package com.example.demo.encry;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	/**偏移量**/
	private static final String IV_STRING = "930638615A9178FF"; 
	/**key**/
	public static final String KEY = "82E82F6C93045174";
	/**加密算法，工作模式，填充方式**/
	private static final String CIPHER = "AES/CBC/PKCS5Padding";
	
	
	
	/**
	 * 加密<BR>
	 * 方法名：encryptData<BR>
	 * 创建人：zhangqi <BR>
	 * 时间：2017年8月28日-下午4:32:11 <BR>
	 * @param key
	 * @param content
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	*/
	public static String encryptData(String key, String content) {
        try {
            byte[] encryptedBytes;
            byte[] byteContent = content.getBytes("UTF-8");
            // 注意，为了能与 iOS 统一
            // 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
            byte[] enCodeFormat = key.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            // 指定加密的算法、工作模式和填充方式
            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            encryptedBytes = cipher.doFinal(byteContent);
            // 同样对加密后数据进行 base64 编码
            return BASE64.encodeToString(encryptedBytes, BASE64.DEFAULT).replaceAll("\n", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


	/**
	 * 解密<BR>
	 * 方法名：decryptData<BR>
	 * 创建人：zhangqi <BR>
	 * 时间：2017年8月28日-下午4:48:48 <BR>
	 * @param key
	 * @param content
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	*/
	public static String decryptData(String key, String content) {
	    try {
	        // base64 解码
	        byte[] encryptedBytes = BASE64.decode(content, BASE64.DEFAULT);
	        byte[] enCodeFormat = key.getBytes();
	        SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");
	        byte[] initParam = IV_STRING.getBytes();
	        IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
	        Cipher cipher = Cipher.getInstance(CIPHER);
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
	        byte[] result = cipher.doFinal(encryptedBytes);
	        return new String(result, "UTF-8");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static void main(String[] args) {
		System.out.println(AES.encryptData(AES.KEY, "123456"));
		System.out.println(AES.encryptData(AES.KEY, "111111"));
		System.out.println(AES.encryptData(AES.KEY, "222222"));
		System.out.println(AES.encryptData(AES.KEY, "123456"));
	}
    

}
