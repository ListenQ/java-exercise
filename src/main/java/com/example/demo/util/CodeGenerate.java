package com.example.demo.util;

/**
 * code产生器
 * CodeGenerate<BR>
 * 创建人：zhangqi <BR>
 * 时间：2017年10月11日-上午10:21:45 <BR>
 * @version 1.0.0
 * 
 */
public class CodeGenerate {
	 
    private static final char[] r=new char[]{'q', 'w', 'e', '8', 'a', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p', '5', 'i', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'n', '6', 'b', 'g', 'h','1'};
    
    private static final char b='o';
    
    private static final int binLen=r.length;
    
    private static final int s=8;
	
	
	/**
	 * code获取
	 * @param key
	 * @return
	 */
	public static String getCode(Long key) {
        char[] buf=new char[32];
        int charPos=32;

        while((key / binLen) > 0) {
            int ind=(int)(key % binLen);
            buf[--charPos]=r[ind];
            key /= binLen;
        }
        buf[--charPos]=r[(int)(key % binLen)];
        String str = new String(buf, charPos, (32 - charPos));
        if(str.length() < s) {
            StringBuilder sb=new StringBuilder();
            sb.append(b);
            for(int i=1; i < s - str.length(); i++) {
            	sb.append(0);
            }
            str+=sb.toString();
        }
        return str.toLowerCase();
    }
	
	
	public static void main(String[] args) {
//		Set<String> set = new HashSet<String>();
//		for(int i=1;i<=100L;i++){
//			String y = CodeGenerate.getProductCode(312456+Long.valueOf(i));
//			System.out.println(y);
//			set.add(y);
//		}
//		//1 w00000
//		System.out.println(set.size());
		
		System.out.println(getCode(System.currentTimeMillis()));
	}
}
