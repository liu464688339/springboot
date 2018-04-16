package com.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.util.StringHelper;

/**
 * 
 * Json工具类
 * 
 * @author 
 */
public class JSONHelper {
	/**
	 * 定义记录日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(JSONHelper.class);
	private ObjectMapper mapper;

	public JSONHelper() {

	}

	public JSONHelper(Inclusion inclusion) {
		mapper = new ObjectMapper();
		// 设置输出包含的属性
		mapper.getSerializationConfig().withSerializationInclusion(inclusion);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		mapper.getDeserializationConfig().with(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	public JSONHelper(Inclusion inclusion, boolean isIgnore) {
		mapper = new ObjectMapper();
		// 设置输出包含的属性
		mapper.getSerializationConfig().withSerializationInclusion(inclusion);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		mapper.getDeserializationConfig().with(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		if (isIgnore) {
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
	}

	/**
	 * 创建输出全部属性到Json字符串的Binder.
	 */
	public static JSONHelper buildNormalBinder() {
		return new JSONHelper(Inclusion.ALWAYS);
	}
	
	/**
	 * 
	 * 创建输出全部属性到Json字符串的Binder,忽略多余json的属性字段.
	 * @return
	 */
	public static JSONHelper buildNormalIgnoreBinder() {
		return new JSONHelper(Inclusion.ALWAYS, true);
	}

	/**
	 * 创建只输出非空属性到Json字符串的Binder.
	 */
	public static JSONHelper buildNonNullBinder() {
		return new JSONHelper(Inclusion.NON_NULL);
	}

	/**
	 * 创建只输出初始值被改变的属性到Json字符串的Binder.
	 */
	public static JSONHelper buildNonDefaultBinder() {
		return new JSONHelper(Inclusion.NON_DEFAULT);
	}

	/**
	 * 如果JSON字符串为Null或"null"字符串,返回Null. 如果JSON字符串为"[]",返回空集合.
	 * 
	 * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句: List<MyBean> beanList =
	 * binder.getMapper().readValue(listString, new
	 * TypeReference<List<MyBean>>() {});
	 * 
	 * @param jsonString
	 *            {@link String} JSON字符串
	 * @param clazz
	 *            {@link Class} 待转换目标对象
	 * @return 转换后的对象
	 */
	public <T> T parse(String jsonString, Class<T> clazz) {
		if (StringHelper.isEmpty(jsonString)) {
			return null;
		}
		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 将对象格式化成JSON格式字符串
	 * 
	 * @param arg
	 *            {@link Object} 待格式化的参数
	 * @return {@link String} 格式后的JSON字符串
	 */
	public String format(Object arg) {
		try {
			return mapper.writeValueAsString(arg);
		} catch (IOException e) {
			logger.warn("write to json string error:" + arg, e);
			return null;
		}
	}

	/**
	 * 设置转换日期类型的format pattern,如果不设置默认打印Timestamp毫秒数.
	 * 
	 * @param pattern
	 *            {@link String} 日期格式
	 */
	public void setDateFormat(String pattern) {
		if (StringHelper.isEmpty(pattern)) {
			DateFormat df = new SimpleDateFormat(pattern);
			mapper.getSerializationConfig().withDateFormat(df);
			mapper.getDeserializationConfig().withDateFormat(df);
		}
	}

	/**
	 * 取出Mapper做进一步的设置或使用其他序列化API.
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
}
