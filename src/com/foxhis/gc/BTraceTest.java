package com.foxhis.gc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class BTraceTest {
	public int add(int a, int b) {  
        return a + b;  
    }  
    public void run(){  
        int a = (int) (Math.random() * 1000);  
        int b = (int) (Math.random() * 1000);  
        System.out.println(add(a, b));  
    }  
    public static void main(String[] args) throws IOException {  
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));  
        BTraceTest bTraceTest=new BTraceTest();  
        bReader.readLine();  
        for (int i = 0; i < 10; i++) {  
            bTraceTest.run();  
        }  
    }  
}


/***
 * 
import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TracingScript {

  @OnMethod(
      clazz = "com.foxhis.gc.BTraceTest",
      method="add",
      location=@Location(Kind.RETURN)
  )

  public static void func(@Self com.foxhis.gc.BTraceTest instance ,int a,int b,@Return int result){
      println("调用堆栈");
      jstack();
      println(strcat("方法参数A:",str(a)));
      println(strcat("方法参数B:",str(b)));
      println(strcat("方法返回;",str(result)));
  }
}


** Compiling the BTrace script ...
*** Compiled
** Instrumenting 1 classes ...
*** Done
** BTrace up&running

*** Done
** BTrace up&running

调用堆栈
com.foxhis.gc.BTraceTest.add(BTraceTest.java:9)
com.foxhis.gc.BTraceTest.run(BTraceTest.java:14)
com.foxhis.gc.BTraceTest.main(BTraceTest.java:21)
方法参数A:731
方法参数B:52
方法返回;783
调用堆栈
com.foxhis.gc.BTraceTest.add(BTraceTest.java:9)
com.foxhis.gc.BTraceTest.run(BTraceTest.java:14)
com.foxhis.gc.BTraceTest.main(BTraceTest.java:21)
方法参数A:109
方法参数B:613
方法返回;722
调用堆栈
com.foxhis.gc.BTraceTest.add(BTraceTest.java:9)
com.foxhis.gc.BTraceTest.run(BTraceTest.java:14)
com.foxhis.gc.BTraceTest.main(BTraceTest.java:21)
方法参数A:339
方法参数B:238
方法返回;577
调用堆栈
com.foxhis.gc.BTraceTest.add(BTraceTest.java:9)
com.foxhis.gc.BTraceTest.run(BTraceTest.java:14)
com.foxhis.gc.BTraceTest.main(BTraceTest.java:21)
方法参数A:446
方法参数B:714
方法返回;1160
调用堆栈
com.foxhis.gc.BTraceTest.add(BTraceTest.java:9)
com.foxhis.gc.BTraceTest.run(BTraceTest.java:14)
com.foxhis.gc.BTraceTest.main(BTraceTest.java:21)
方法参数A:474
方法参数B:164
方法返回;638
调用堆栈
com.foxhis.gc.BTraceTest.add(BTraceTest.java:9)
com.foxhis.gc.BTraceTest.run(BTraceTest.java:14)
com.foxhis.gc.BTraceTest.main(BTraceTest.java:21)
方法参数A:767
方法参数B:583
方法返回;1350
调用堆栈
com.foxhis.gc.BTraceTest.add(BTraceTest.java:9)
com.foxhis.gc.BTraceTest.run(BTraceTest.java:14)
com.foxhis.gc.BTraceTest.main(BTraceTest.java:21)
方法参数A:941
方法参数B:986
方法返回;1927
调用堆栈
com.foxhis.gc.BTraceTest.add(BTraceTest.java:9)
com.foxhis.gc.BTraceTest.run(BTraceTest.java:14)
com.foxhis.gc.BTraceTest.main(BTraceTest.java:21)
方法参数A:331
方法参数B:483
方法返回;814
调用堆栈
com.foxhis.gc.BTraceTest.add(BTraceTest.java:9)
com.foxhis.gc.BTraceTest.run(BTraceTest.java:14)
com.foxhis.gc.BTraceTest.main(BTraceTest.java:21)
方法参数A:807
方法参数B:842
方法返回;1649
调用堆栈
** BTrace has stopped
** BTrace has stopped
 */
