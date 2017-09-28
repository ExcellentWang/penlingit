/*package com.ontheroad.mysql.web.context;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue<T> {

	private static int defaultCount = 20;

	private final BlockingQueue<T> blockingQueue = new LinkedBlockingQueue<T>();

	*//**
	 * 往队列放置元素
	 * 
	 * @param treeNode
	 * @throws InterruptedException
	 *//*
	public void put(T treeNode) throws InterruptedException {
		blockingQueue.put(treeNode);
	}
	
	public boolean isEmpty(){
		return blockingQueue.isEmpty();
	}
	
	public T take(){
		return blockingQueue.poll();
	}
	*//**
	 * 获取即将批量发送的treeNodes
	 * 
	 * @return
	 *//*
	public List<T> getTasks() {
		List<T> result = new ArrayList<T>();
		long start = System.nanoTime();
		blockingQueue.drainTo(result, defaultCount);
		System.out.println("获取" + result + "条，耗费时间为（毫秒）："+ (System.nanoTime() - start) / 10000000);
		return result;
	}

	public BlockingQueue<T> getBlockingQueue() {
		return blockingQueue;
	}
	
	
}
*/