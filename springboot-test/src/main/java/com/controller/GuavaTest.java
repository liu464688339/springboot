package com.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bean.Test;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;
import com.mysql.fabric.xmlrpc.base.Array;
import com.google.common.collect.Table;

public class GuavaTest {

	public static void main(String[] args) throws Exception {
		// BiMapTest();
		// MultisetTest();
		TableTest();
	}

	/**
	 * 逆视图，根据value获取key
	 */
	static void BiMapTest() {
		BiMap<String, String> a = HashBiMap.create();
		a.put("123", "lh");
		a.put("456", "dj");
		System.out.println(a.get("123"));
		System.out.println(a.inverse().get("lh"));
	}

	static void MultisetTest() {
		Multiset<String> multiset = HashMultiset.create();
		multiset.add("a");
		multiset.add("b");
		multiset.add("c");
		multiset.add("d");
		multiset.add("a");
		multiset.add("b");
		multiset.add("c");
		multiset.add("b");
		multiset.add("b");
		multiset.add("b");
		System.out.println(multiset.count("a"));// 获取a的数量
		System.out.println(multiset.elementSet());// 获取去重后的集合元素列表
		// 获取去重后的元素和元素重复的数量
		Set<Entry<String>> sets = multiset.entrySet();
		for (Entry<String> s : sets) {
			System.out.println(s.getElement() + ":" + s.getCount());
		}
		Iterator<String> it = multiset.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	static void TableTest() throws Exception {
		List<Test> list = new ArrayList<Test>();
		Test t1 = new Test();
		t1.setMainCode("01");
		t1.setMainCodeName("fm");
		t1.setSubCode("01");
		t1.setSubCodeName("click");
		Test t2 = new Test();
		t2.setMainCode("01");
		t2.setMainCodeName("fm");
		t2.setSubCode("02");
		t2.setSubCodeName("search");
		Test t3 = new Test();
		t3.setMainCode("02");
		t3.setMainCodeName("music");
		t3.setSubCode("01");
		t3.setSubCodeName("listen");
		Test t4 = new Test();
		t4.setMainCode("02");
		t4.setMainCodeName("music");
		t4.setSubCode("02");
		t4.setSubCodeName("collect");
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);

	//     mainCode mainCodeName subCode subCodeName
    //  1  01       fm           01       click
    //  2  01       fm           02       search
    //  3  02       music        01       listen
    //  4  02       music        02       collect
		Class<Test> c1 = Test.class;
		Field[] fileds = c1.getDeclaredFields();
		Table<String, String, String> table = HashBasedTable.create();
		for (int i = 1; i <= list.size(); i++) {
			Test t = list.get(i - 1);
			for (int j = 0; j < fileds.length; j++) {
				Method m = (Method)c1.getMethod("get" + getMethodName(fileds[j].getName()));
				table.put(String.valueOf(i), fileds[j].getName().toString(),m.invoke(t).toString());
			}		
		}
		System.out.println(table.rowMap());	
		System.out.println(table.column("mainCodeName").get("1"));
		System.out.println(table.get("2", "subCodeName"));
		System.out.println(table.rowKeySet().toString());
		System.out.println(table.columnKeySet().toString());
	}

	// 把一个字符串的第一个字母大写、效率是最高的、
	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}
}
