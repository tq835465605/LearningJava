package com.foxhis.gc;


/**
 * vm参数:
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 * @author Administrator
 *
 */
public class Allocation {

	private static final int _1MB = 1024*1024;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] allocation1,allocation2,allocation3,allocation4;
		allocation1 = new byte[2*_1MB];
		allocation2 = new byte[2*_1MB];
		allocation3 = new byte[2*_1MB];
		allocation4 = new byte[4*_1MB];//发生Minor gc

		//Minor gc，一般发生在新生代。
		//Major GC/FullGC 老年代，比minorgc慢10倍以上
	}

}

/**
 * 运行结果如下
 * [GC [DefNew: 6496K->194K(9216K), 0.0214295 secs] 6496K->6339K(19456K), 0.0214528 secs] [Times: user=0.00 sys=0.03, real=0.02 secs] 
Heap
 def new generation   total 9216K, used 4454K [0x324b0000, 0x32eb0000, 0x32eb0000)
  eden space 8192K,  52% used [0x324b0000, 0x328d8fe0, 0x32cb0000)
  from space 1024K,  19% used [0x32db0000, 0x32de0bd0, 0x32eb0000)
  to   space 1024K,   0% used [0x32cb0000, 0x32cb0000, 0x32db0000)
 tenured generation   total 10240K, used 6144K [0x32eb0000, 0x338b0000, 0x338b0000)
   the space 10240K,  60% used [0x32eb0000, 0x334b0030, 0x334b0200, 0x338b0000)
 compacting perm gen  total 12288K, used 380K [0x338b0000, 0x344b0000, 0x378b0000)
   the space 12288K,   3% used [0x338b0000, 0x3390f3f8, 0x3390f400, 0x344b0000)
    ro space 10240K,  55% used [0x378b0000, 0x37e33320, 0x37e33400, 0x382b0000)
    rw space 12288K,  55% used [0x382b0000, 0x38956128, 0x38956200, 0x38eb0000)
    *
    *分析：堆最大最小内存20M，不可扩展，年轻代10M，老年代10M。SurvivorRatio=8，标识eden:from=8:1
    *则eden:from:to=8:1:1,根据复制法，to区域不可分配，则新生代可用内存为9M=9216K
    *分析gc回收日志：第一行，6496K->194K(9216K=年轻代内存大小)，代表这次GC后，新生代内存6496K变成194K,而总内存6496K->6339K(19456K)没有变少
    *还有6339K，因为前三个对象allocation都还存活，前三个allocation已经占用了6M，第四个allocation需要4M已经没有足够
    *内存存放(总9M),则发送GC，将前面6M移到到to(1M)区域，to区域太小，则移动到老年代，所以老年代变成total 10240K, used 6144K
    *此时分配allocation4就又放在新生代了，则此时新生代内存为total 9216K, used 4454K
    */
