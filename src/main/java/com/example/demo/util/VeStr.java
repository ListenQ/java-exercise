package com.example.demo.util;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Clob;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class VeStr {
	
	protected final static Log log = LogFactory.getLog(VeStr.class);
	
	/**
	 * 判断是否空字符串：true空，false非空。
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		return str==null || str.trim().length()<1;
	}
	
	/**
	 * 读取文件
	 * @param fileName 文件路径，相对于项目的classpath目录。
	 * @param encoding 编码
	 * @param isTab 是否保留原文件的空白字符，true保留，false不保留。
	 * @return
	 */
	public static String file2String(String fileName,String encoding,boolean isTab){
		return VeStr.stream2Str(VeStr.class.getResourceAsStream(fileName),encoding,false);
	}
	
	/**
	 * 读取文件
	 * @param fileName 文件路径，绝对目录。
	 * @param encoding 编码
	 * @param isTab 是否保留原文件的空白字符，true保留，false不保留。
	 * @return
	 */
	public static String absolutelyFile2String(String fileName,String encoding,boolean isTab){
		FileInputStream is = null;
		try {
			File file = new File(fileName);
			is = new FileInputStream(file);
			return VeStr.stream2Str(is,encoding,false);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		finally{
			if(is!=null){
				try {
					is.close();
				} catch (Exception e) {
					log.info(e.getMessage());
				}
			}
		}
		return "";
	}
	
	/**
	 * InputStream转换成String
	 * @param is 输入流
	 * @param encoding 编码
	 * @param isTab 是否保留原文件的空格、换行等符号，true保留，false不保留。
	 * @return
	 */
	public static String stream2Str(InputStream is,String encoding,boolean isTab){
		BufferedReader reader = null;
		InputStreamReader isr =  null;
		try{
			String tabStr = "";
			if(isTab){
				tabStr = "\r\n";
			}
			if(VeStr.isNull(encoding)){
				isr = new InputStreamReader(is);
			}
			else{
				isr = new InputStreamReader(is,encoding);
			}
			reader = new BufferedReader(isr);   
	        StringBuilder sb = new StringBuilder();   
	        String line = null;   
	        while ((line = reader.readLine()) != null) {  
                sb.append(line.trim()+tabStr);   
            }
			return sb.toString();
		}
		catch(Exception e){
			log.info(e.getMessage());
		}
		finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					log.info(e.getMessage());
				}
			}
			if(isr!=null){
				try {
					isr.close();
				} catch (IOException e) {
					log.info(e.getMessage());
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					log.info(e.getMessage());
				}
			}
		}
		return "";
	}
	
	/**
	 * 字符串转换为输入流
	 * @param str
	 * @param encoding
	 * @return
	 */
	public static InputStream str2Stream(String str,String encoding){
		try {
			byte[] strBytes = null;
			if(VeStr.isNull(encoding)){
				strBytes = str.getBytes();
			}
			else{
				strBytes = str.getBytes(encoding.trim());
			}
			return new ByteArrayInputStream(strBytes);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 是否全部是汉字
	 */
	public static boolean isChinese(String s) {
		Pattern p1 = Pattern.compile("^[\u4e00-\u9fa5]+$");
		Matcher m1 = p1.matcher(s);
		return m1.find();
	}

	/**
	 * 是否汉字
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		return (c + "").matches("[\u4E00-\u9FA5]");
	}

	/**
	 * 判断字符串是否全数字
	 * 
	 * @param validString
	 * @return
	 */
	public static boolean isDigit(String validString) {
		if (validString == null)
			return false;
		byte[] tempbyte = validString.getBytes();
		for (int i = 0; i < validString.length(); i++) {
			// by=tempbyte[i];
			if ((tempbyte[i] < 48) || (tempbyte[i] > 57)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param validString
	 * @return
	 */
	public static boolean isDouble(String validString) {
		if (validString == null)
			return false;
		int k = 0;
		byte[] tempbyte = validString.getBytes();
		for (int i = 0; i < validString.length(); i++) {
			if (tempbyte[i] == 46) {
				k++;
			}
		}
		if (k > 1)
			return false; // k>1
		for (int i = 0; i < validString.length(); i++) {
			if (tempbyte[i] != 46) { // 46
				// ,48
				if ((tempbyte[i] < 48) || (tempbyte[i] > 57)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @param validString
	 * @return
	 */
	public static boolean isChar(String validString) {
		if (validString == null)
			return false;
		byte[] tempbyte = validString.getBytes();
		for (int i = 0; i < validString.length(); i++) {
			if ((tempbyte[i] < 48) || ((tempbyte[i] > 57) & (tempbyte[i] < 65)) || (tempbyte[i] > 122)
					|| ((tempbyte[i] > 90) & (tempbyte[i] < 97))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param validString
	 * @return
	 */
	public static boolean isLetter(String validString) {
		if (validString == null)
			return false;
		byte[] tempbyte = validString.getBytes();
		for (int i = 0; i < validString.length(); i++) {
			if ((tempbyte[i] < 65) || (tempbyte[i] > 122) || ((tempbyte[i] > 90) & (tempbyte[i] < 97))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断数组中是否包含字符串
	 * 
	 * @param sz
	 * @param str
	 * @return
	 */
	public static boolean isSzCstr(String[] sz, String str) {
		if (sz == null || sz.length < 1 || StringUtils.isBlank(str)) {
			return false;
		}
		for (String s : sz) {
			if (str.equals(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param source
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static String Replace(String source, String oldString, String newString) {
		if (source == null)
			return null;
		StringBuffer output = new StringBuffer();
		int lengOfsource = source.length();
		int lengOfold = oldString.length();
		int posStart = 0;
		int pos;
		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengOfold;
		}
		if (posStart < lengOfsource) {
			output.append(source.substring(posStart));
		}
		return output.toString();
	}

	/**
	 * 
	 * @param source
	 * @param findString
	 * @return
	 */
	public static boolean isInclude(String source, String findString) {
		if (source == null)
			return false;
		if (findString == null)
			return false;
		int posStart = 0;
		int pos = source.indexOf(findString, posStart);
		boolean jj = false;
		if (pos >= 0) {
			jj = true;
		}
		return jj;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String toHtml(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		s = Replace(s, "\r\n", "\n");
		// s = Replace(s,"\"","'");
		s = Replace(s, "\\n", "<br>");
		s = Replace(s, "\n", "<br>");
		s = Replace(s, "  ", "&nbsp;&nbsp;");
		// s = Replace(s,"","'");
		s = Replace(s, "'", "&#39;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "\\", "&#92;");
		s = clearToXml(s);
		return s;
	}

	public static String clearToXml(String str) {
		char[] a = new char[str.length()];
		for (int index = 0; index < str.length(); index++) {
			a[index] = str.charAt(index);
			if ((int) a[index] < 32) {
				a[index] = 32;
			}
		}
		return String.valueOf(a);
	}

	/**
	 * 格式化页面输入值存入数据库 可用作XML格式化
	 * 
	 * @param s
	 * @return
	 */
	public static String formatHtml(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		s = s.trim();
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, "'", "&#39;");
		s = Replace(s, "\"", "&quot;");
		return s;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String toHtml1(String s) {
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		s = Replace(s, "\r\n", "\n");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "\n", "");
		s = Replace(s, "  ", "&nbsp;&nbsp;");
		// s = Replace(s,"","'");
		s = Replace(s, "'", "&#39;");
		s = Replace(s, "\\", "&#92;");
		return s;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String unHtml(String s) {
		s = Replace(s, "<br>", "\n");
		s = Replace(s, "<br/>", "\n");
		s = Replace(s, "<br />", "\n");
		s = Replace(s, "<br >", "\n");
		s = Replace(s, "</br>", "\n");
		s = Replace(s, "<BR>", "\n");
		s = Replace(s, "<BR/>", "\n");
		s = Replace(s, "&lt;", "<");
		s = Replace(s, "&nbsp;", " ");
		s = Replace(s, "&gt;", ">");
		return s;
	}

	/**
	 * 去掉html语法 <>中间的内容<br>
	 * <b></b> <span></span> <a href=""></a>
	 * 
	 * @param html
	 * @return
	 */
	public static String clearHtml(String html) {
		if (StringUtils.isBlank(html)) {
			return "";
		}

		return html.replaceAll("<[^>]+>", "");
	}

	/**
	 * 去掉表头内容
	 * 
	 * @param s
	 * @return
	 */
	public static String unTitle(String str) {
		String s = str;
		s = StringUtils.replace(s, "</span>", "");
		int i = StringUtils.indexOf(s, ">");
		s = StringUtils.substring(s, i + 1);
		return s;
	}

	/*
	 * 替换XML中不能有的特殊字符
	 */
	public static String toXmlFormat(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		// &lt; < 小于号
		// &gt; > 大于号
		// &amp; & 和,与
		// &apos; ' 单引号
		// &quot; " 双引号
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "'", "&apos;");
		s = Replace(s, "\"", "&quot;");
		return s;
	}

	/**
	 * 把number格式化为正常的字符串，去除科学计数法
	 * 
	 * @param n
	 * @return
	 */
	public static String formatNumber(Number n) {
		if (n == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("#.######");
		String num = df.format(n);
		return num;
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		Random jjj = new Random();

		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}

//	public static String numtochinese(String input) {
//		String result = "";
//		result = Money.getChnmoney(input);
//		return result;
//	}

	/**
	 * 补齐开始日期到秒.使其格式为YYYY-MM-DD HH24:MI:SS
	 * 
	 * @param ksrq
	 * @return String
	 */
	public static String KsrqString(String ksrq) {
		String newKsrq = "";
		// 形参字符串长度
		Integer Stringlength = ksrq.trim().length();
		switch (Stringlength) {
		case 10:
			newKsrq = ksrq + " 00:00:00";
			break;
		case 13:
			newKsrq = ksrq + ":00:00";
			break;
		case 16:
			newKsrq = ksrq + ":00";
			break;
		default:
			newKsrq = ksrq;
			break;
		}
		return newKsrq;
	}

	/**
	 * 补齐结束日期到秒.使其格式为YYYY-MM-DD HH24:MI:SS
	 * 
	 * @param ksrq
	 * @return String
	 */
	public static String JsrqString(String jsrq) {
		String newJsrq = "";
		// 形参字符串长度
		Integer Stringlength = jsrq.trim().length();
		switch (Stringlength) {
		case 10:
			newJsrq = jsrq + " 23:59:59";
			break;
		case 13:
			newJsrq = jsrq + ":59:59";
			break;
		case 16:
			newJsrq = jsrq + ":59";
			break;
		default:
			newJsrq = jsrq;
			break;
		}
		return newJsrq;
	}

	/**
	 * Description 将数字金额转换为中文金额
	 */
	public static String DoNumberCurrencyToChineseCurrency(BigDecimal bigdMoneyNumber) {
		// 中文金额单位数组
		String[] straChineseUnit = new String[] { "分", "角", "圆", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟" };
		// 中文数字字符数组
		String[] straChineseNumber = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String strChineseCurrency = "";
		// 零数位标记
		boolean bZero = true;
		// 中文金额单位下标
		int ChineseUnitIndex = 0;
		try {
			if (bigdMoneyNumber.intValue() == 0)
				return "零圆整";
			// 处理小数部分，四舍五入
			double doubMoneyNumber = Math.round(bigdMoneyNumber.doubleValue() * 100);
			// 是否负数
			boolean bNegative = doubMoneyNumber < 0;
			// 取绝对值
			doubMoneyNumber = Math.abs(doubMoneyNumber);
			// 循环处理转换操作
			while (doubMoneyNumber > 0) {
				// 整的处理(无小数位)
				if (ChineseUnitIndex == 2 && strChineseCurrency.length() == 0)
					strChineseCurrency = strChineseCurrency + "整";
				// 非零数位的处理
				if (doubMoneyNumber % 10 > 0) {
					strChineseCurrency = straChineseNumber[(int) doubMoneyNumber % 10]
							+ straChineseUnit[ChineseUnitIndex] + strChineseCurrency;
					bZero = false;
				}
				// 零数位的处理
				else {
					// 元的处理(个位)
					if (ChineseUnitIndex == 2) {
						// 段中有数字
						if (doubMoneyNumber > 0) {
							strChineseCurrency = straChineseUnit[ChineseUnitIndex] + strChineseCurrency;
							bZero = true;
						}
					}
					// 万、亿数位的处理
					else if (ChineseUnitIndex == 6 || ChineseUnitIndex == 10) {
						// 段中有数字
						if (doubMoneyNumber % 1000 > 0)
							strChineseCurrency = straChineseUnit[ChineseUnitIndex] + strChineseCurrency;
					}
					// 前一数位非零的处理
					if (!bZero)
						strChineseCurrency = straChineseNumber[0] + strChineseCurrency;

					bZero = true;
				}
				doubMoneyNumber = Math.floor(doubMoneyNumber / 10);
				ChineseUnitIndex++;
			}
			// 负数的处理
			if (bNegative)
				strChineseCurrency = "负" + strChineseCurrency;
		} catch (Exception e) {
			e.printStackTrace();
			return "金额超出限制,请检查金额是否正确!";
		}
		return strChineseCurrency;
	}

	/**
	 * 
	 * @param mobile要判断的号码
	 * @return 返回相应类型：1代表联通；2代表移动；3代表其它移动运营商
	 */
	private static String ydtel = "134,135,136,137,138,139,150,151,152,154,157,158,159,188,133,153,180,189,187,188";

	private static String lttel = "130,131,132,155,156,185,186";

	@SuppressWarnings("unchecked")
	public static String getMobileType(String mobile) {
		if (StringUtils.isBlank(mobile) || mobile.length() < 10) {
			return "0";
		}
		if (mobile.startsWith("0") || mobile.startsWith("+860")) {
			mobile = mobile.substring(mobile.indexOf("0") + 1, mobile.length());
		}
		if (ydtel.indexOf(mobile.substring(0, 3)) >= 0) {
			return "1";
		} else if (lttel.indexOf(mobile.substring(0, 3)) >= 0) {
			return "2";
		} else {
			return "0";
		}

		// List chinaUnicom = (List) Arrays.asList(new String[] { "130", "131",
		// "132", "133", "153", "156","189" });
		// List chinaMobile1 = (List) Arrays.asList(new String[] { "134", "135",
		// "136", "137", "138", "139","151", "158", "159",
		// "150","188" });
		// List chinaMobile2 = (List) Arrays.asList(new String[] { "1340",
		// "1341", "1342", "1343", "1344", "1345", "1346",
		// "1347", "1348" });
		// boolean bolChinaUnicom = (((java.util.List<String>)
		// chinaUnicom).contains(mobile.substring(0, 3)));
		// boolean bolChinaMobile1 = (((java.util.List<String>)
		// chinaMobile1).contains(mobile.substring(0, 3)));
		// boolean bolChinaMobile2 = (((java.util.List<String>)
		// chinaMobile2).contains(mobile.substring(0, 4)));
		// if (bolChinaUnicom)
		// return "1";// 联通
		// if (bolChinaMobile1 || bolChinaMobile2)
		// return "2"; // 移动
		// return "0"; // 其它移动运营商
	}

	// 分解深发展银行成功返回的字符串
	public static Properties parseStringToProperties(String data, String token) {
		String PROPERTY_DELIMER = "=";
		if (data == null)
			return null;
		if (token == null || token.length() == 0) {
		}

		StringTokenizer tokenizer = new StringTokenizer(data, token);
		Properties props = new Properties();

		if (tokenizer.countTokens() == 0) {
			// throw new NoSuchElementException("");
			return null;
		}

		while (tokenizer.hasMoreTokens()) {
			String element = tokenizer.nextToken();

			if (element.indexOf(PROPERTY_DELIMER) != -1)
				props.put(element.substring(0, element.indexOf(PROPERTY_DELIMER)), element.substring(element
						.indexOf(PROPERTY_DELIMER) + 1));
		}
		return props;
	}

	/**
	 * 处理特殊字符串。 用于过滤字符串在javascript中报错的情况 把" ' \ 替换为中文符号
	 * 
	 * @param source
	 * @return
	 */
	public static String procStr(String source) {
		if (StringUtils.isBlank(source)) {
			return source;
		}
		try {
			source = source.replaceAll("\n", "");
			source = source.replaceAll("\r", "");
			source = source.replaceAll("\"", "“");
			source = source.replaceAll("'", "‘");
			source = source.replace("\\", "＼");
			source = source.replace(";", "；");
		} catch (Exception e) {
			e.printStackTrace();
			return source;
		}
		return source.trim();
	}

	/**
	 * 处理特殊字符串()。
	 * 
	 * @param source
	 * @return
	 */
	public static String procKh(String source) {
		if (StringUtils.isBlank(source)) {
			return source;
		}
		try {
			source = source.replaceAll("\\(", " ");
			source = source.replaceAll("\\)", "");
			source = source.replaceAll("\\（", " ");
			source = source.replaceAll("\\）", "");
			source = source.replaceAll("'", "‘");
		} catch (Exception e) {
			e.printStackTrace();
			return source;
		}
		return source.trim();
	}

	/**
	 * 处理特殊字符串",，，"。
	 * 
	 * @param source
	 * @return
	 */
	public static String procComma(String source) {
		if (StringUtils.isBlank(source)) {
			return source;
		}
		try {
			source = source.replaceAll("\\，", ",");
			source = source.replaceAll("\\,", ",");
		} catch (Exception e) {
			e.printStackTrace();
			return source;
		}
		return source.trim();
	}

	/**
	 * 处理Bean所有属性为String的首尾空格和单引号
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static void replaceStringBeginEndSpace(Object bean) throws IllegalAccessException, InvocationTargetException {
		if (bean == null) {
			return;
		}
		Class c = bean.getClass();
		Field field[] = c.getDeclaredFields();
		for (int j = 0; j < field.length; j++) {
			if ("java.lang.String".equals(field[j].getType().getName())) {
				String name = field[j].getName();
				String value = "";
				try {
					value = BeanUtils.getProperty(bean, name);
				} catch (NoSuchMethodException e) {
				}
				if (StringUtils.isNotEmpty(value)) {
					value = procStr(value);
					BeanUtils.setProperty(bean, name, value);
				}
			}
		}
	}

	/**
	 * 去掉首尾空格和'单引号
	 * 
	 * @param s
	 * @return
	 */
	public static String filterStr(String s) {
		if (StringUtils.isNotBlank(s)) {
			s = s.replace("'", "");
		}
		return StringUtils.trim(s);
	}

	public static String formatXmlTag(String s) {
		if (StringUtils.isBlank(s)) {
			return "";
		}
		String p = "<[^>]+>";
		Pattern p1 = Pattern.compile(p);
		Matcher m1 = p1.matcher(s);
		while (m1.find()) {
			String old = m1.group(0);
			String tmp = old.replace("\r", "").replace("\n", "");
			s = s.replace(old, tmp);
		}
		return s;

	}

	/**
	 * 获得左边正确的汉字的位置
	 * 
	 * @param source
	 *            原始字符串
	 * @param maxByteLen
	 *            截取的字节数
	 * @param flag
	 *            表示处理汉字的方式。1表示遇到半个汉字时补全，-1表示遇到半个汉字时舍
	 */
	public static int leftindex(String source, int maxByteLen, int flag) {
		if (source == null || maxByteLen <= 0) {
			return 0;
		}
		byte[] bStr = source.getBytes();
		if (maxByteLen >= bStr.length)
			return bStr.length;
		String cStr = new String(bStr, maxByteLen - 1, 2);
		if (cStr.length() == 1 && source.contains(cStr)) {
			maxByteLen += flag;
		}
		return maxByteLen;
	}

	/**
	 * 返回两个字符串中相同的字数在较短的字符串中的比例
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static double getSimilarity(String str1, String str2) {
		int length1 = str1.length();
		int length2 = str2.length();
		if (length1 == 0 || length2 == 0) {
			return 0;
		}
		int k = 0;
		for (int i = 0; i < length1; i++) {
			for (int j = 0; j < length2; j++) {
				if (str1.substring(i, i + 1).equals(str2.substring(j, j + 1))) {
					k++;
					break;
				}
			}
		}
		if (length1 >= length2) {
			return k / (double) length2;
		} else {
			return k / (double) length1;
		}
	}

	/**
	 * 将传入的以','的字符串分解成SQL语句中的IN（'',''）能查询的语句；
	 * 
	 * @param source
	 *            原始字符串
	 * @return '','' 字符串
	 */
	public static String getStringToSqlIn(String source) {
		String stmp = "";
		if (StringUtils.isBlank(source)) {
			return source;
		}
		try {
			source = source.replaceAll("，", ",");
			source = source.replaceAll(",", ",");
			source = source.replaceAll("，", ",");
			source = source.replaceAll("，", ",");
			stmp = source + ",";
			String[] str = stmp.split(",");
			for (int i = 0; i < str.length; i++) {
				if (i == 0) {
					stmp = "'" + str[i].trim() + "'";
				} else {
					stmp = stmp + ",'" + str[i].trim() + "'";
				}
			}
		} catch (Exception e) {
			return source;
		}
		return stmp;
	}

	/**
	 * 
	 * @param arr
	 *            String数组
	 * @param prepend
	 *            连接符
	 * @return 返回一个用指定连接符连接的数组，当prepend为null时，用半角","连接
	 */
	public static String arrayToString(String[] arr, String prepend) {
		if (prepend == null) {
			prepend = ",";
		}
		StringBuffer sb = new StringBuffer();
		for (String s : arr) {
			sb.append(s).append(prepend);
		}
		int l = sb.lastIndexOf(prepend);
		sb.delete(l, l + 1);
		return sb.toString();
	}

	public static String proctel(String tel) {
		tel = StringUtils.trimToEmpty(tel);
		if (StringUtils.isNotBlank(tel)) {
			// tel = tel.replaceAll("-", "");
			tel = tel.replaceAll("/", "");
			tel = tel.replaceAll("^\\D*", "");// 开头的非数字替换为空
			tel = tel.replaceAll("\\D*$", "");// 结尾的非数字替换为空
			tel = tel.replaceAll("(\\D^-)+", ",");// 中间的非数字替换,
		}
		return tel;
	}

	/**
	 * 截取中文和英文
	 * 
	 * @param splitStr
	 * @param bytes
	 * @return
	 */
	public static String splitIt(String splitStr, int bytes) {
		int cutLength = 0;
		int byteNum = bytes;
		byte bt[] = splitStr.getBytes();
		if (bt.length <= bytes) {
			return splitStr;
		}
		System.out.println("Length of this String ===>" + bt.length);
		if (bytes > 1) {
			for (int i = 0; i < byteNum; i++) {
				if (bt[i] < 0) {
					cutLength++;
				}
			}
			if (cutLength % 2 == 0) {
				cutLength /= 2;
			} else {
				cutLength = 0;
			}
		}
		int result = cutLength + --byteNum;
		if (result > bytes) {
			result = bytes;
		}
		if (bytes == 1) {
			if (bt[0] < 0) {
				result += 2;
			} else {
				result += 1;
			}
		}
		String substrx = new String(bt, 0, result);
		return substrx;
	}

	/**
	 * 
	 * 替换敏感字符
	 * 
	 * @param sourceStr
	 *            需过滤的字符串
	 * @param replaceStr
	 *            敏感字符替换为replaceStr
	 * @return 处理过后的字符串
	 * 
	 */
	public static String keyWordFilter(String sourceStr, String replaceStr) {
		Pattern pattern = null;
		// 从words.properties初始化正则表达式字符串

		StringBuffer patternBuf = new StringBuffer("");
		try {
			InputStream fs = VeStr.class.getClassLoader().getResourceAsStream("words.properties");
			// String separ = System.getProperty("file.separator"); //文件分隔符
			// String spath =
			// Constants.APPPRTH+separ+"info"+separ+"bbs"+separ+"words.properties";
			// FileInputStream fs=new FileInputStream(spath);
			Properties pro = new Properties();
			pro.load(fs);
			fs.close();
			Enumeration enu = pro.propertyNames();
			patternBuf.append("(");
			while (enu.hasMoreElements()) {
				patternBuf.append((String) enu.nextElement() + "|");
			}
			patternBuf.deleteCharAt(patternBuf.length() - 1);
			patternBuf.append(")");
			// unix换成UTF-8
			pattern = Pattern.compile(new String(patternBuf.toString().getBytes("ISO-8859-1"), "UTF-8"));
			// win下换成gb2312
			// pattern = Pattern.compile(new
			// String(patternBuf.toString().getBytes("ISO-8859-1"), "gb2312"));

			Matcher m = pattern.matcher(sourceStr);
			sourceStr = m.replaceAll(replaceStr);
		} catch (Exception ioEx) {
			ioEx.printStackTrace();
		}
		return sourceStr;
	}

	public static String getBirthDateFromCard(String cardNumber) {
		if (StringUtils.isBlank(cardNumber)) {
			return "";
		}
		String card = cardNumber.trim();
		String year = "";
		String month = "";
		String day = "";
		if (card.length() == 18) { // 处理18位身份证
			year = card.substring(6, 10);
			month = card.substring(10, 12);
			day = card.substring(12, 14);
		} else if (card.length() == 15) { // 处理非18位身份证
			year = card.substring(6, 8);
			month = card.substring(8, 10);
			day = card.substring(10, 12);
			year = "19" + year;
		} else {
			return "";
		}
		if (month.length() == 1) {
			month = "0" + month; // 补足两位
		}
		if (day.length() == 1) {
			day = "0" + day; // 补足两位
		}
		return year + "-" + month + "-" + day;
	}

	/**
	 * 通过身份证号码获取性别 女返回0 男返回1 其他不合法的返回空
	 * 
	 * 
	 * @param cardNumber
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String getGenderFromCard(String cardNumber) {
		if (StringUtils.isBlank(cardNumber)) {
			return "";
		}
		String card = StringUtils.trimToEmpty(cardNumber);
		String xb = "";
		if (card.length() == 18) { // 处理18位身份证
			xb = StringUtils.substring(card, 16, 17);
		} else if (card.length() == 15) { // 处理非18位身份证
			xb = StringUtils.substring(card, 14, 15);
		} else {
			return "";
		}
		if (!NumberUtils.isDigits(xb)) {
			return "";
		}
		// 奇数是男
		if (NumberUtils.toInt(xb) % 2 == 0) {
			return "0";
		} else {
			return "1";
		}
	}

	/**
	 * 功能：把15位身份证转换成18位
	 * 
	 * @param id
	 * @return newid or id
	 */
	public static final String getIDCard_18bit(String id) {
		// 若是15位，则转换成18位；否则直接返回ID
		if (15 == id.length()) {
			final int[] W = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
			final String[] A = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
			int i, j, s = 0;
			String newid;
			newid = id;
			newid = newid.substring(0, 6) + "19" + newid.substring(6, id.length());
			for (i = 0; i < newid.length(); i++) {

				j = Integer.parseInt(newid.substring(i, i + 1)) * W[i];
				s = s + j;
			}
			s = s % 11;
			newid = newid + A[s];

			return newid;
		} else {
			return id;
		}

	}

	/**
	 * 得到服务器的网卡IP地址
	 * 
	 * @return
	 */
	public static List<String> getLocalhost() {
		List list = new ArrayList();
		try {
			Enumeration<?> e1 = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
			while (e1.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e1.nextElement();
				Enumeration<?> e2 = ni.getInetAddresses();
				while (e2.hasMoreElements()) {
					String ip = "";
					InetAddress ia = (InetAddress) e2.nextElement();
					if (ia instanceof Inet6Address)
						continue;
					ip = ia.getHostAddress();
					list.add(ip);
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 判断IP是否是内网IP true是内网 false是外网
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean isNwIP(String ip) {
		List l = getLocalhost();
		if (l.contains(ip)) {
			return true;
		}
		return false;
	}

	/**
	 * 汉字转码，全角汉字转换成半角汉字
	 * 
	 * @param QJstr
	 * @return
	 */
	public static final String SBCchange(String QJstr) {
		String outStr = "";
		String Tstr = "";
		byte[] b = null;

		for (int i = 0; i < QJstr.length(); i++) {
			try {
				Tstr = QJstr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (b[2] == -1) {
				b[3] = (byte) (b[3] + 32);
				b[2] = 0;
				try {
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else
				outStr = outStr + Tstr;
		}
		return outStr;
	}

//	public static String encodeBase64(String s, String encode) {
//		if (s == null)
//			return null;
//		if (StringUtils.isBlank(encode)) {
//			encode = "UTF-8";
//		}
//		BASE64Encoder en64 = new BASE64Encoder();
//		byte[] b = null;
//		try {
//			b = s.getBytes(encode);
//			return en64.encode(b);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

//	public static String decodeBase64(String s, String encode) {
//		if (s == null)
//			return null;
//		if (StringUtils.isBlank(encode)) {
//			encode = "UTF-8";
//		}
//		BASE64Decoder de64 = new BASE64Decoder();
//
//		byte[] cc;
//		try {
//			cc = de64.decodeBuffer(s);
//			return new String(cc, encode);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	/**
	 * 从城市的list map中取一个编号相同的map
	 * 
	 * @param dlmc
	 *            城市名称获取MAP
	 * @param city
	 *            城市list 一般是在application中的
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map acity(String csmc, List<Map> city) {
		if (city == null || city.size() < 1) {
			return null;
		}

		for (int i = 0; i < city.size(); i++) {
			Map m = city.get(i);
			if (csmc.equals((String) m.get("CSMC"))) {
				return m;
			}
		}
		return null;

	}

	/**
	 * 生成类似如：“param.add(t_gqsqb.getSq_compid());”字符串的方法
	 * 
	 * @param str
	 *            需要的字段用“,”分隔
	 * @param beanName
	 *            BEAN的名称
	 * @return
	 */
	public static String getBeanToListStr(String str, String beanName) {
		StringBuffer s = new StringBuffer();
		String[] arr = str.split(",");
		for (String k : arr) {
			k = StringUtils.trim(k.toUpperCase());
			String get = k.substring(0, 1).toUpperCase() + k.toLowerCase().substring(1);
			s.append("\nparam.add(").append(beanName).append(".get").append(get).append("());");
		}
		return s.toString();
	}

	/**
	 * 汉字替换为空
	 * 
	 * @param source
	 * @param des
	 * @return
	 */
	public static String replaceHz(String source, String des) {
		if (StringUtils.isNotBlank(source)) {
			String regEx = "[\\W]";
			Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
			Matcher mat = pat.matcher(source);
			String s = mat.replaceAll(des);
			return s;
		}
		return "";
	}

	/**
	 * 截取字符串的字节数
	 * 
	 * @param str
	 * @param len
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String substrByte(String str, int len) {
		if (str == null) {
			return "";
		}
		byte[] strByte = str.getBytes();
		int strLen = strByte.length;
		if (len >= strLen || len < 1) {
			return str;
		}
		int count = 0;
		for (int i = 0; i < len; i++) {
			int value = (int) strByte[i];
			if (value < 0) { // 通过尝试发现（gb2312，utf8）汉字确实会占用多于一个字节（2，3），而且都是负值
				count++;
			}
		}
		if (count % 2 != 0) { // 如果platform默认编码是utf8时会出错，某些置不能打印出汉字

			len = (len == 1) ? len + 1 : len - 1;
		}
		return new String(strByte, 0, len);
	}

	/**
	 * 取打印模板的PATH
	 * 
	 * @param folder
	 *            ticket-机票正常票和退票 bx-保险订单 pz-票证 train-火车票 gq-改签 jsjc-接机送机
	 * @param fileName
	 * @return
	 */
	public static String getPrintMb(String folder, String fileName) {
		if (fileName.indexOf("/") == -1) {
			return "/asms/printmb/" + folder + "/" + fileName;
		} else {
			return "/asms/printmb/" + folder + fileName.substring(fileName.lastIndexOf("/"));
		}
	}

	/**
	 * clob转string
	 * 
	 * @param clob
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String clobToString(Clob clob) throws Exception {
		StringBuffer sb = new StringBuffer();
		Reader rd = null;
		BufferedReader bfrd = null;

		try {
			if (clob != null) {
				rd = clob.getCharacterStream();
				bfrd = new BufferedReader(rd);

				if (bfrd != null) {
					String s = null;
					while ((s = bfrd.readLine()) != null) {
						sb.append(s);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bfrd != null) {
				bfrd.close();
			}
			if (rd != null) {
				rd.close();
			}
		}

		return sb.toString();
	}

	/**
	 * 根据XML生成解析后台XML的字符串
	 * 
	 * @param xml
	 * @param recordName
	 *            xml变量 如rec_xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getOracleXmlStr(String xml, String recordName) {
		StringBuffer s = new StringBuffer();
		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(StringUtils.trimToEmpty(xml));
		Document doc = null;
		List<Element> slist = null;
		String rootName = null;
		try {
			doc = builder.build(in);
			Element ele = doc.getRootElement();
			rootName = ele.getName();
			slist = ele.getChildren();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// String[] arr = str.split(",");
		s.append("  --解析XML\n");
		s.append("  v_xmlstr := pkg_common.f_parse_xml(p_xml,v_error);");
		s.append("  IF NOT xmldom.IsNull(v_xmlstr) THEN\n");
		s.append("    v_nl := xmldom.getElementsByTagName(v_xmlstr, '" + StringUtils.trimToEmpty(rootName) + "');\n");
		s.append("    --获取SQL节点\n");
		s.append("    v_node := xmldom.item(v_nl, 0);\n");
		s.append("    --获得SQL节点下所有的子节点\n");
		s.append("    v_childnl := xmldom.getChildNodes(v_node);\n");
		s.append("    --获得子节点的数量\n");
		s.append("    v_len := xmldom.getLength(v_childnl);\n");
		s.append("    \n");
		s.append("    FOR i IN 0 .. v_len - 1 LOOP\n");
		s.append("      --如果没有匹配的字段则忽略\n");
		s.append("      BEGIN\n");
		s.append("        v_node     := xmldom.item(v_childnl, i);\n");
		s.append("        v_nodename := xmldom.getNodeName(v_node);\n");
		s.append("        BEGIN\n");
		s.append("          v_nodevalue := xmldom.getNodeValue(xmldom.getFirstChild(v_node));\n");
		s.append("          IF v_nodevalue = 'null' THEN\n");
		s.append("            v_nodevalue := '';\n");
		s.append("          END IF;\n");
		s.append("        EXCEPTION\n");
		s.append("          WHEN OTHERS THEN\n");
		s.append("        	  v_nodevalue := NULL;\n");
		s.append("        END;\n");
		s.append("        CASE v_nodename\n");
		if (slist != null) {
			for (Element e : slist) {
				String name = StringUtils.trimToEmpty(e.getName());
				s.append("          WHEN '" + name.toUpperCase() + "' THEN\n");
				s.append("            " + recordName + "." + name.toLowerCase() + " :=v_nodevalue;\n");
			}
		}
		s.append("        END CASE;\n");

		s.append("      EXCEPTION\n");
		s.append("        WHEN OTHERS THEN\n");
		s.append("          NULL; --忽略异常继续循环\n");
		s.append("      END;\n");
		s.append("    END LOOP;\n");
		s.append("  ELSE\n");
		s.append("    v_error := 'xml文档不存在!';\n");
		s.append("    p_yy_bb_error('', v_error, to_CLOB(P_XML), '');\n");
		s.append("    OPEN p_cur FOR  SELECT 1 FROM dual WHERE 1 <> 1;\n");
		s.append("    RETURN;\n");
		s.append("  END IF;\n");
		return s.toString();
	}

	/**
	 * 判断是电话号码是手机还是座机
	 */
	public static Map<String, String> ifMobile(String telno) {
		Map<String, String> map = new HashMap<String, String>();
		if (telno.startsWith("1") && telno.length() == 11 && telno.indexOf("-") < 0) { // 以1开头，长度11判定为手机号码
			map.put("ismobile", "1"); // 是否手机号码 1是 2座机
			map.put("mbhd", telno.substring(0, 7)); // 手机中间4位号段
		}

		String[] schar = { "0", "086", "86", "+86", "860" }; // 号码前面一截需截取的字符串
		String sno = telno;
		int ti = 0;
		for (int i = 0; i < schar.length; i++) {
			if (telno.startsWith(schar[i])) {
				ti = i;
				sno = telno.substring(schar[i].length()); // 截取特定字符串后的号码
				if (sno.startsWith("1") && sno.length() == 11 && sno.indexOf("-") < 0) { // 以1开头，长度11判定为手机号码
					map.put("ismobile", "1");
					map.put("mbhd", sno.substring(0, 7));
				}
			}
		}

		if (null == map || map.size() <= 0) {
			map.put("ismobile", "2");
			map.put("mbhd", ti == 0 ? telno : sno); // 返回电话号码,如果是前缀为0截取的，返回原始号码，否则返回过滤后的号码
		}
		return map;
	}

	public static String getValue(Map map, String name) {
		Object o = map.get(name);
		return o == null ? "" : StringUtils.trimToEmpty(String.valueOf(o));
	}

	public static void main(String[] args) {

		System.out.println(getGenderFromCard("430103790823454"));
	}
}
