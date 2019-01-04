package com.foxhis.generics;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 通过concurrentHashMap实现高并发缓存
 * @author tq
 *
 * @param <T>
 * @param <V>
 */
public class ExtendCompute<T,V> implements CacheCompute<T, V>{

	private Map<T, Future<V>> cacheMap = new ConcurrentHashMap<T, Future<V>>();
	private CacheCompute<T,V> compute;

	public ExtendCompute(CacheCompute<T,V> compute) {
		// TODO Auto-generated constructor stub
		this.compute = compute;
	}
	@Override
	public V comput(T t) {
		// TODO Auto-generated method stub
		Future<V> f = cacheMap.get(t);
		if(null ==f)
		{
			Callable<V> callable= new Task(compute,t);
			FutureTask<V> ft = new FutureTask<V>(callable);
			f= ft;
			cacheMap.put(t, f);	
			ft.run();			
		}
		try {
			return f.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	
	class Task implements Callable<V>
	{
		CacheCompute<T,V> cacheCompute;
		T t;
		public Task(CacheCompute<T,V> cacheCompute,T t) {
			// TODO Auto-generated constructor stub
			this.cacheCompute = cacheCompute;
			this.t = t;
		}
		@Override
		public V call() throws Exception {
			// TODO Auto-generated method stub
			return cacheCompute.comput(t);
		}
		
	}

}
