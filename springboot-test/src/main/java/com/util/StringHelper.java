package com.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.util.StringUtils;

/***
 * 字符串操作助手
 * 
 * @author longbc
 */
public class StringHelper extends StringUtils {

	/**
	 * 去除字符串前后空格
	 * 
	 * @param str
	 *            {@link String} 待去除空格的字符串
	 * @return {@link String} 去除空格后的字符串
	 */
	public static String trim(String str) {
		if (isEmpty(str)) {
			return str;
		}
		return str.trim();
	}

	/**
	 * Replace all occurences of a substring within a string with another
	 * string.
	 * 
	 * @param inString
	 *            String to examine
	 * @param oldPattern
	 *            String to replace
	 * @param newPattern
	 *            String to insert
	 * @return a String with the replacements
	 */
	public static String replaceAll(String inString, String oldPattern, String newPattern) {
		if (null == inString || oldPattern == null || newPattern == null) {
			return inString;
		}
		String rlt = inString;
		StringBuffer sb = new StringBuffer();
		while (true) {
			int idx = rlt.indexOf(oldPattern);
			if (idx < 0)
				break;
			sb.delete(0, sb.length());
			if (idx > 0) {
				sb.append(rlt.substring(0, idx));
			}
			sb.append(newPattern);
			sb.append(rlt.substring(idx + oldPattern.length()));
			rlt = sb.toString();
		}
		return rlt;
	}

	/**
	 * 获取唯一的标识值
	 * 
	 * @return {@link String}
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 根据用","隔开的字符Id转换成list
	 * 
	 * @param id
	 *            {@link String}
	 * @return {@link List}
	 */
	public static List<Integer> getListId(String id) {
		String str[] = id.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < str.length; i++) {
			list.add(Integer.parseInt(str[i]));
		}
		return list;
	}

	/**
	 * 判断两个字符串是否相等
	 * 
	 * @param arg1
	 *            {@link String}
	 * @param arg2
	 *            {@link String}
	 * @return {@link Boolean}
	 */
	public static Boolean isEqualString(String arg1, String arg2) {
		return arg1.equals(arg2);
	}

	/**
	 * 格式化字符串
	 * 
	 * @param arg
	 *            {@link String}
	 * @param objects
	 *            {@link Object}
	 * @return {@link String}
	 */
	public static String formatterString(String arg, Object... objects) {
		return MessageFormat.format(arg, objects);
	}

	/**
	 * 
	 * String字符串转整形数组
	 * 
	 * @param ids
	 *            {@link String} 字符串
	 * @param sign
	 *            {@link String} 符号
	 * @return
	 */
	public static int[] StringToArray(String ids, String sign) {
		String[] t = ids.split(sign);
		int[] arrays = new int[t.length];
		for (int i = 0; i < t.length; i++) {
			arrays[i] = Integer.parseInt(t[i]);
		}
		return arrays;
	}

	/**
	 * uncode 转中文
	 * 
	 * @param dataStr
	 *            {@link String} 目标字符串
	 * @return
	 */
	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}

	/**
	 * 中文 转 uncode
	 * 
	 * @param dataStr
	 *            {@link String} 目标字符串
	 * @return
	 */
	public static String encodeUnicode(final String dataStr) {
		String retString = "";
		for (int i = 0; i < dataStr.length(); i++) {
			retString += "\\u" + Integer.toHexString(dataStr.charAt(i) & 0xffff);
		}
		return retString;
	}
}