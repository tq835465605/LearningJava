package com.foxhis.gc;

public class PretenureSizeThreshold {

	private static final int _1MB = 1024*1024;
	

/**
 * vm参数:
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 * -XX:PretenureSizeThreshold=3145728 //大对象的阈值，超过3M的对象或者数组，直接分配在老年代
 * 
 *
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] allocation1;
		allocation1 = new byte[4*_1MB];

	}
	
	/**
	 * Heap
 def new generation   total 9216K, used 516K [0x324b0000, 0x32eb0000, 0x32eb0000)
  eden space 8192K,   6% used [0x324b0000, 0x325312d8, 0x32cb0000)
  from space 1024K,   0% used [0x32cb0000, 0x32cb0000, 0x32db0000)
  to   space 1024K,   0% used [0x32db0000, 0x32db0000, 0x32eb0000)
 tenured generation   total 10240K, used 4096K [0x32eb0000, 0x338b0000, 0x338b0000)
   the space 10240K,  40% used [0x32eb0000, 0x332b0010, 0x332b0200, 0x338b0000)
 compacting perm gen  total 12288K, used 380K [0x338b0000, 0x344b0000, 0x378b0000)
   the space 12288K,   3% used [0x338b0000, 0x3390f378, 0x3390f400, 0x344b0000)
    ro space 10240K,  55% used [0x378b0000, 0x37e33320, 0x37e33400, 0x382b0000)
    rw space 12288K,  55% used [0x382b0000, 0x38956128, 0x38956200, 0x38eb0000)
	 */

}
