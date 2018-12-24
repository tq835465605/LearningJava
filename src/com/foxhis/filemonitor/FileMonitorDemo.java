package com.foxhis.filemonitor;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;


public class FileMonitorDemo {
	

	private static Map<String, Long> cacheMap =new  ConcurrentHashMap<String, Long>();

	private static final File logFile = new File(System.getProperty("user.dir")+"\\logs\\");
	private static final String KEY = "lastTimeFileSize";
	private static final String[] extensions = {"log"};


	
	public static void main(String[] args) throws Exception {
		
		FileFilterImpl filterImpl = new FileFilterImpl(extensions);
		cacheMap.put(KEY, 0L);
		System.out.println(filterImpl.accept(logFile));
		FileAlterationObserver logFileObserver=new FileAlterationObserver(logFile, filterImpl );
		logFileObserver.addListener(new FileListener());
		FileAlterationMonitor fileMonitor = new FileAlterationMonitor(2000, logFileObserver);
		fileMonitor.start();
	}

	/**
	 * 文件监听类
	 * @author Administrator
	 *
	 */
	static class FileListener extends FileAlterationListenerAdaptor{

		
		/**
	     * 文件创建时执行的动作
	     */
	    @Override
	    public void onFileCreate(File file) {
	        // To do something
	        System.out.println("Create file: "+file.getName());
	    }
	    
	    /**
	     * 文件删除（转移）时执行的动作
	     */
	    @Override
	    public void onFileDelete(File file) {
	        // To do something
	        System.out.println("Delete file: "+file.getName());
	    }
	    
	    /**
	     * 文件内容改变时执行的动作
	     */
	    @Override
	    public void onFileChange(File file) {
	    	// To do something
	       StringBuilder logTextArea = new StringBuilder();

	    	RandomAccessFile randomFile=null;
	    	long lastTimeFileSize = cacheMap.get(KEY).longValue();
	    	try {
	    		randomFile = new RandomAccessFile(file, "r");
	    		long nowTimeFileSize = randomFile.length();
	    		if(nowTimeFileSize!=lastTimeFileSize)
	    		{
	    			randomFile.seek(lastTimeFileSize);
	    			cacheMap.put(KEY, nowTimeFileSize);
		    		String tmp = null;
		    		while ((tmp = randomFile.readLine()) != null) {
		    			
		    			logTextArea.append(new String(tmp.getBytes("iso-8859-1"), "utf-8"));
		    			logTextArea.append("\n");
		    		}
	    		}
		
	    	} catch (Exception e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	finally {
	    		try {
	    			randomFile.close();
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    	}
	    	System.out.println("Change file: "+file.getName());
	    	System.out.println(logTextArea.toString());
	    }

	    /**
	     * 开始执行监听时执行的动作
	     */
	    @Override
	    public void onStart(FileAlterationObserver observer) {
	        // To do something
	    	super.onStart(observer);
	    }
	    
	    /**
	     * 停止监听时执行的动作
	     */
	    @Override
	    public void onStop(FileAlterationObserver observer) {
	        // To do something
	    	super.onStop(observer);
	    }
		
	}

    
	static class FileFilterImpl  implements FileFilter{

		private String[] extensions;
		
		public FileFilterImpl(String...extensions) {
			// TODO Auto-generated constructor stub
			this.extensions = extensions;
		}
		@Override
		public boolean accept(File pathname) {
			// TODO Auto-generated method stub
			return FilenameUtils.isExtension(pathname.getName(), extensions);
		}
		
		public String[] getExtensions()
		{
			return extensions;
		}
	}



}
