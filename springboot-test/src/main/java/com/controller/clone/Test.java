package com.controller.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {
	/**
	 * 浅拷贝（Shallow Copy）：①对于数据类型是基本数据类型的成员变量，浅拷贝会直接进行值传递，
	 * 也就是将该属性值复制一份给新的对象。因为是两份不同的数据，
	 * 所以对其中一个对象的该成员变量值进行修改，不会影响另一个对象拷贝得到的数据。
	 * ②对于数据类型是引用数据类型的成员变量，比如说成员变量是某个数组、某个类的对象等，那么浅拷贝会进行引用传递，
	 * 也就是只是将该成员变量的引用值（内存地址）复制一份给新的对象。因为实际上两个对象的该成员变量都指向同一个实例。
	 * 在这种情况下，在一个对象中修改该成员变量会影响到另一个对象的该成员变量值。
	 * @throws CloneNotSupportedException
	 */
	public static void qianclone() throws CloneNotSupportedException {
		A a=new A();
		a.setId("aaa");
		a.setName("啊啊啊");
		
		B b=new B();
		b.setAge(30);
		b.setName("bbb");
		a.setB(b);
		System.out.println("克隆前a:"+a.toString());
		
		A a1=(A)a.clone();
		System.out.println("克隆后a1:"+a1.toString());
		a1.setId("bbb");
        a1.setName("版本包");
        //浅克隆 修改被复制的对象的引用属性时，原对象的引用属性也会跟着变
		B b1=a1.getB();
		b1.setAge(40);
		b1.setName("ccc");
		a1.setB(b1);
		System.out.println("修改克隆的a之后,a="+a.toString());
		System.out.println("修改克隆的a1之后,a1="+a1.toString());
	}
	
	/**
	 * 对于深拷贝来说，不仅要复制对象的所有基本数据类型的成员变量值，还要为所有引用数据类型的成员变量申请存储空间，并复制每个引用数据类型成员变量所引用的对象，
	 * 直到该对象可达的所有对象。也就是说，对象进行深拷贝要对整个对象图进行拷贝！
	 * 深拷贝对引用数据类型的成员变量的对象图中所有的对象都开辟了内存空间；而浅拷贝只是传递地址指向，
	 * 新的对象并没有对引用数据类型创建内存空间
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void shenclone() throws IOException, ClassNotFoundException {
		A a=new A();
		a.setId("aaa");
		a.setName("啊啊啊");
		
		B b=new B();
		b.setAge(30);
		b.setName("bbb");
		a.setB(b);
		
		 //通过序列化方法实现深拷贝
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(bos);
        oos.writeObject(a);
        oos.flush();
		ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
		A a1=(A)ois.readObject();
		System.out.println("克隆后a:"+a.toString());
        System.out.println("克隆后a1:"+a1.toString());
		
	
	
	}
	public static void main(String[] args){
		try {
			qianclone();
			shenclone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
