package com.controller;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.util.RedisUtil;

import redis.clients.jedis.Transaction;

/**
 * @Auther: lh
 * @Date: 2018/7/2
 * @Description:
 */
public class ProduceModern {
	public static void main(String[] args) throws Exception {
	        BlockingQueue<Apple> queue = new LinkedBlockingQueue<Apple>(10);
	        ExecutorService cachedThreadPool1 = Executors.newCachedThreadPool();
	        ExecutorService cachedThreadPool2 = Executors.newCachedThreadPool();
	        for(int i=0;i<5;i++) {
	        	cachedThreadPool1.execute(new Producer(queue,i));
	        } 
	        for(int j=0;j<10;j++) {
	        	cachedThreadPool2.execute(new Consumer(queue,j));
	        }         
	        cachedThreadPool1.shutdown();
	        cachedThreadPool2.shutdown();
	    }

}


class Producer implements Runnable {

    private static Random r = new Random();

    private BlockingQueue<Apple> queue;

    private static AtomicInteger count = new AtomicInteger();
    private String name;

    public Producer(BlockingQueue<Apple> queue,int num) {
        this.queue = queue;
        this.name="生产工人"+num+"号";
    }

    public void run() {
    	   try {  
	           Thread.sleep((int)(Math.random()*5000));// 随机睡眠一下  
	       } catch (InterruptedException e1) {  
	       } 
	       while (true) {  
	           try {
	        	   Apple apple = new Apple();
	        	   apple.setId(r.nextInt(100));
                   count.incrementAndGet();   
	        	   queue.put(apple);   
	        	   Thread.sleep((int)(Math.random()*1000));
                   System.out.println(name + "把苹果" + apple.getId() + "放到盘子里");
                   if(count.get()>=10) {
                    	System.out.println(name+"发现苹果已经放完了========="+count.get());
                    	break;
                   }
	           } catch (Exception e) {  
	               e.printStackTrace();  
	           } 
	       }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Apple> queue;
    private static AtomicInteger count = new AtomicInteger();
    private String name;

    public Consumer(BlockingQueue<Apple> queue,int num) {
        this.queue = queue;
        this.name="消费工人"+num+"号";
    }

    public void run() {
    	try {  
	           Thread.sleep((int)(Math.random()*5000));// 随机睡眠一下  
	    } catch (InterruptedException e1) {  
	    }
        while (true) {
            try {
                Apple apple = queue.take();
                if(apple!=null) {
                	 System.out.println(name + "把苹果" + apple.getId() + "拿出来");
                	 count.incrementAndGet();              	 
                }
                if(count.get()>=10) {
                	System.out.println(name+"发现苹果已经拿完了========="+count.get());
                	break;
                }  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Apple {
    int id;
    String name;
    public Apple() {}
    public Apple(int id) {
    	this.id=id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}