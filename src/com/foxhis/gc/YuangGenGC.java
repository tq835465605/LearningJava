package com.foxhis.gc;

import java.util.ArrayList;
import java.util.List;

public class YuangGenGC {
	
	/**
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=5 -XX:+HeapDumpOnOutOfMemoryError
	 * @author Administrator
	 *
	 */
	static class OOMObjece{
		
		public static void main(String[] args) {
			
			List<OOMObjece> list = new ArrayList<OOMObjece>();
			while(true)
			{
				list.add(new OOMObjece());
			}
		}
	}
	
	
	/**
[GC [DefNew: 6249K->1408K(8832K), 0.0147919 secs] 6249K->3682K(19072K), 0.0148144 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
[GC [DefNew: 8832K->1408K(8832K), 0.0216791 secs] 11106K->8045K(19072K), 0.0217023 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
[GC [DefNew: 8000K->8000K(8832K), 0.0000133 secs][Tenured: 6637K->10240K(10240K), 0.0479047 secs] 14638K->11972K(19072K), [Perm : 381K->381K(12288K)], 0.0479632 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
[Full GC [Tenured: 10240K->10240K(10240K), 0.0498755 secs] 11972K->11959K(19072K), [Perm : 381K->375K(12288K)], 0.0499095 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid10344.hprof ...
Heap dump file created [24842098 bytes in 0.339 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:2760)
	at java.util.Arrays.copyOf(Arrays.java:2734)
	at java.util.ArrayList.ensureCapacity(ArrayList.java:167)
	at java.util.ArrayList.add(ArrayList.java:351)
	at com.foxhis.gc.YuangGenGC$OOMObjece.main(YuangGenGC.java:15)
Heap
 def new generation   total 8832K, used 1948K [0x324b0000, 0x32eb0000, 0x32eb0000)
  eden space 7424K,  26% used [0x324b0000, 0x32697350, 0x32bf0000)
  from space 1408K,   0% used [0x32bf0000, 0x32bf0000, 0x32d50000)
  to   space 1408K,   0% used [0x32d50000, 0x32d50000, 0x32eb0000)
 tenured generation   total 10240K, used 10240K [0x32eb0000, 0x338b0000, 0x338b0000)
   the space 10240K, 100% used [0x32eb0000, 0x338b0000, 0x338b0000, 0x338b0000)
 compacting perm gen  total 12288K, used 376K [0x338b0000, 0x344b0000, 0x378b0000)
   the space 12288K,   3% used [0x338b0000, 0x3390e118, 0x3390e200, 0x344b0000)
    ro space 10240K,  55% used [0x378b0000, 0x37e33320, 0x37e33400, 0x382b0000)
    rw space 12288K,  55% used [0x382b0000, 0x38956128, 0x38956200, 0x38eb0000)
*/

}
