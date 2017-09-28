package com.ontheroad.core.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Order {
	private static int serial = 1;
	public static synchronized String generateOrder() {
		StringBuilder sb = new StringBuilder();
		sb.append(serial++);
		while (sb.length() < 6) {
			sb.insert(0, "0");
		}
		sb.insert(0, new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
		return sb.toString();
	}
	
   public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(15);
		for (int i = 0; i < 15; i++) {
			new Thread() {
				public void run() {
					System.out.println(Order.generateOrder());
				}
			}.start();
		}
   }
}