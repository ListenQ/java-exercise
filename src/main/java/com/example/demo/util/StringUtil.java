package com.example.demo.util;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 *
 * @author zhuorui
 * @date 2020/10/28
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
public class StringUtil {

    private static final Pattern P_IPV_4;
    private static final Pattern P_IPV_6;
    private static final Pattern P_MAC;

    static {
        // ipv4
        P_IPV_4 = Pattern.compile("^(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$");

        // ipv6
        P_IPV_6 = Pattern.compile("^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:)|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}(:[0-9A-Fa-f]{1,4}){1,2})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){1,3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){1,4})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){1,5})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){1,6})|(:(:[0-9A-Fa-f]{1,4}){1,7})|(([0-9A-Fa-f]{1,4}:){6}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){0,4}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(:(:[0-9A-Fa-f]{1,4}){0,5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}))$");

        //mac
        P_MAC = Pattern.compile("^[a-fA-F0-9]{2}(:[a-fA-F0-9]{2}){5}$");
    }

    /**
     * 判断字符串是否不为null或去空格后长度为0
     *
     * @param s 字符串
     * @return true，不为null或去空格后长度为0
     */
    public static boolean isNotEmpty(String s) {
        return s != null && s.trim().length() > 0;
    }

    /**
     * 判断至少一个字符串是否不为null或去空格后长度为0
     *
     * @param ss 字符串数组
     * @return 如果至少一个字符串不为null或去空格后长度为0，则返回true。
     */
    public static boolean isNotEmptyAny(String... ss) {
        for (String s : ss) {
            if (isNotEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断至少一个字符串是否为null或去空格后长度为0
     *
     * @param ss 字符串数组
     * @return 如果至少一个字符串为null或去空格后长度为0，则返回true。
     */
    public static boolean isEmptyAny(String... ss) {
        for (String s : ss) {
            if (isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断所有字符串是否不为null或去空格后长度为0
     *
     * @param ss 字符串数组
     * @return 如果所有字符串不为null或去空格后长度为0，则返回true。
     */
    public static boolean isNotEmptyAll(String... ss) {
        for (String s : ss) {
            if (isEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 把一个对象数组用分隔字符串连接成一个字符串。
     *
     * @param objs        对象数组
     * @param splitString 分割字符串
     * @return 连接后的字符串
     */
    public static <T> String join(T[] objs, String splitString) {
        StringBuilder s = new StringBuilder();
        if (objs.length > 0) {
            s.append(objs[0]);
            for (int i = 1; i < objs.length; i++) {
                s.append(splitString).append(objs[i]);
            }
        }

        return s.toString();
    }

    /**
     * 把一个对象列表用分隔字符串连接成一个字符串。
     *
     * @param objList     对象列表
     * @param splitString 分割字符串
     * @return 连接后的字符串
     */
    public static String join(List<?> objList, String splitString) {
        StringBuilder s = new StringBuilder();
        if (objList.size() > 0) {
            s.append(objList.get(0));
            for (int i = 1, ii = objList.size(); i < ii; i++) {
                s.append(splitString).append(objList.get(i));
            }
        }

        return s.toString();
    }

    /**
     * 把一个对象数组的列表的某一列数据用分隔字符串连接成一个字符串。
     *
     * @param objList     对象数组的列表
     * @param splitString 分割字符串
     * @return 连接后的字符串
     */
    public static <T> String join(List<T[]> objList, int columnIndex,
                                  String splitString) {
        StringBuilder s = new StringBuilder();
        if (objList.size() > 0) {
            s.append(objList.get(0)[columnIndex]);
            for (int i = 1, ii = objList.size(); i < ii; i++) {
                s.append(splitString).append(objList.get(i)[columnIndex]);
            }
        }

        return s.toString();
    }

    /**
     * 判断字符串是否为null或去空格后长度为0
     *
     * @param s 字符串
     * @return true，为null或去空格后长度为0
     */
    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    /**
     * 判断所有字符串是否为null或去空格后长度为0
     *
     * @param ss
     * @return
     */
    public static boolean isEmptyAll(String... ss) {
        for (String s : ss) {
            if (isNotEmpty(s)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 是否是邮件地址格式
     *
     * @param s 字符串
     * @return true，是
     */
    public static boolean isEmail(String s) {
        return s.matches("^[a-zA-Z0-9._-]+@([a-zA-Z0-9_-])+(\\.[a-zA-Z0-9_-]+)+$");
    }

    /**
     * 只含有汉字、数字、字母、下划线不能以下划线开头和结尾
     *
     * @param s 字符串
     * @return true，是
     */
    public static boolean isName(String s) {
        return s.matches("^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$");
    }

    /**
     * 是否是手機號碼格式
     *
     * @param s 字符串
     * @return true，是
     */
    public static boolean isMobile(String s) {
        if (isEmpty(s)) {
            return false;
        }
        // 增加虚拟运营商号码段..
        return s.matches("^1[34578]\\d{9}$");
    }


    public static String encryptMobile(String mobile) {
        if (mobile == null) {
            return null;
        }
        int encryptLength = mobile.length() > 4 ? mobile.length() - 4 : mobile
                .length() / 2;
        StringBuilder sb = new StringBuilder(mobile.length());
        for (int i = 0; i < encryptLength; i++) {
            sb.append("*");
        }
        sb.append(mobile.substring(encryptLength));
        return sb.toString();
    }

    public static String encryptEmail(String email) {
        if (email == null) {
            return null;
        }

        String[] split = email.split("@");
        //noinspection AlibabaUndefineMagicConstant
        if (split.length != 2) {
            return null;
        }

        String username = split[0];
        String mailTail = split[1];

        int encryptLength = username.length() > 4 ? username.length() - 4
                : username.length() / 2;
        encryptLength = encryptLength == 0 ? 1 : encryptLength;

        StringBuilder sb = new StringBuilder(username.length());

        sb.append(username.substring(0, username.length() - encryptLength));

        for (int i = 0; i < encryptLength; i++) {
            sb.append("*");
        }
        sb.append("@");
        sb.append(mailTail);

        return sb.toString();

    }

    /**
     * @param str 要转换的字符串
     * @return String 型值
     * @功能 将英文字符串首字母转为大写
     */
    public static String toUpCaseFirst(String str) {
        if (str == null || "".equals(str)) {
            return str;
        } else {
            char[] temp = str.toCharArray();
            temp[0] = str.toUpperCase().toCharArray()[0];
            str = String.valueOf(temp);
        }

        return str;
    }

    public static String polishZero(String str) {
//		if (str.length() >= 32)
//			return str;
//		String polish = "00000000000000000000000000000000";
//		return polish.substring(str.length()) + str;

        return polishZero(str, 32);
    }

    public static String polishZero(String str, int length) {
        if (str.length() >= length) {
            return str;
        }
        int size = length - str.length();
        StringBuffer polish = new StringBuffer();
        for (int i = 0; i < size; i++) {
            polish.append("0");
        }
        return polish + str;
    }


    /**
     * Mac 格式校验
     *
     * @param mac
     * @return
     */
    public static Boolean isMac(String mac) {
        if (isEmpty(mac)) {
            return false;
        }

        Matcher m = P_MAC.matcher(mac);

        if (!m.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是不是一个软件版本
     *
     * @param s
     * @return
     */
    public static boolean isSoftwareVer(String s) {
        return s.matches("\\d{1,5}\\.\\d{1,5}\\.\\d{1,5}");
    }

    /**
     * 是不是一个软件名字
     * 只能含有数字、字母、下划线，不能以下划线开头和结尾
     *
     * @param s
     * @return
     */
    public static boolean isSoftwareName(String s) {
        return s.matches("^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$");
    }


    /**
     * 日期时间
     * <p>
     * 格式201512121212
     *
     * @param s
     * @return
     */
    public static boolean isYYMMDDHHMM(String s) {
        return s.matches("((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))(([0-1]?[0-9]|2[0-3])([0-5][0-9])$)");
    }

    /**
     * 首字母大写
     *
     * @param s
     * @return
     */
    public static String captureFirstLetter(String s) {
        return s == null || s.trim() == "" ? null : s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 根据xml消息类型获取对应的类名
     *
     * @param type
     * @param eventType
     * @return
     */
    public static String type2MessageClassName(String type, String eventType) {
        if (!StringUtil.isNotEmpty(type)) {
            return null;
        }
        String className = "";
        type = type.trim();
        //noinspection AlibabaUndefineMagicConstant
        if (!"event".equals(type)) {
            className = "com.cloud.message." + StringUtil.captureFirstLetter(type) + "message";
        }
        return className;
    }

    public static String appendSpace(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        StringBuffer newStr = new StringBuffer();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                continue;
            }

            if (i > 0) {
                //noinspection AlibabaAvoidComplexCondition
                if (((charArray[i - 1] >= 47 && charArray[i - 1] <= 57)
                        || (charArray[i - 1] >= 65 && charArray[i - 1] <= 90)
                        || (charArray[i - 1] >= 97 && charArray[i - 1] <= 122))

                        &&

                        (((charArray[i] >= 47 && charArray[i] <= 57)
                                || (charArray[i] >= 65 && charArray[i] <= 90)
                                || (charArray[i] >= 97 && charArray[i] <= 122)))) {
                    newStr.append(charArray[i]);
                    continue;
                }
            }

            newStr.append(' ');
            newStr.append(charArray[i]);
        }
        return newStr.toString();
    }


    public static boolean isIPAddress(String ip) {
        if (P_IPV_4.matcher(ip).matches() || P_IPV_6.matcher(ip).matches()) {
            return true;
        }

        return false;
    }

    /**
     * 只能含有数字、字母
     *
     * @param s
     * @return
     */
    public static boolean isNumberAndLetter(String s) {
        return s.matches("^[a-zA-Z0-9_]+$");
    }


    /**
     * 判断字符串是否为URL
     *
     * @param urls 用户头像key
     * @return true:是URL、false:不是URL
     */
    public static boolean isHttpUrl(String urls) {
        boolean isurl = false;
        //设置正则表达式
        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";
        //比对
        Pattern pat = Pattern.compile(regex.trim());
        Matcher mat = pat.matcher(urls.trim());
        //判断是否匹配
        isurl = mat.matches();
        if (isurl) {
            isurl = true;
        }
        return isurl;
    }

    /**
     * 生成随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 字符串第一个字符转小写
     *
     * @author: zhouyuan
     * @create: 2019/8/28
     **/
    public static String toLowerCaseFirst(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 股票代码补0
     *
     * @param code
     * @param len
     * @return
     */
    public static String codeFillZero(String code, int len) {
        return String.format("%0" + len + "d", Long.parseLong(code));
    }





    /**
     * 股票代码补0
     *
     * @param code
     * @return
     */
    public static String codeFillZero(String code) {
        return codeFillZero(code, 5);
    }

    /**
     * 股票代码去除0
     *
     * @param code
     * @return
     * @author zhangjunhao
     * @date 2020/8/27
     */
    public static String codeDepriveZero(String code) {
        if (isEmpty(code)) {
            return code;
        }
        return code.replaceFirst("^0*", "");
    }

    /**
     * 判断字符串是否为汉字组合
     *
     * @param s
     * @return
     * @author lizhihao
     * @date 2019/11/29
     */
    public static boolean isChinese(String s) {
        String chRegx = "^[\u4e00-\u9fa5]+$";
        return s.matches(chRegx);
    }

    /**
     * 简单判断字符串是否为身份证号码的格式
     *
     * @param s
     * @return
     * @author lizhihao
     * @date 2019/11/29
     */
    public static boolean isCardNo(String s) {
        String cardRegx = "\\d{15}(\\d{2}[0-9xX])?";
        return s.matches(cardRegx);
    }


    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     *
     * @param value value
     * @return Object
     * @author shenyangkun
     * @date 2020/11/16
     */
    public static int length(String value) {
        int valueLength = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c >= '\u0391' && c <= '\uFFE5') {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

}
