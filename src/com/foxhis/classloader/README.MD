## 类加载 

类的加载指的是将类的.class文件中的二进制数据读入到内存中，将其放在运行时数据区的方法区内，然后在堆区创建一个java.lang.Class对象，用来封装类在方法区内的数据结构。类的加载的最终产品是位于堆区中的Class对象，Class对象封装了类在方法区内的数据结构，并且向Java程序员提供了访问方法区内的数据结构的接口。

## 类生命周期

其中类加载的过程包括了**加载**、**验证**、**准备**、**解析**、**初始化**、**使用**、**卸载**7个阶段。在这7个阶段中，加载、验证、准备和初始化这四个阶段发生的顺序是确定的，而解析阶段则不一定，它在某些情况下可以在初始化阶段之后开始，这是为了支持Java语言的运行时绑定（也成为动态绑定或晚期绑定）。另外注意这里的几个阶段是按顺序开始，而不是按顺序进行或完成，因为这些阶段通常都是互相交叉地混合进行的，通常在一个阶段执行的过程中调用或激活另一个阶段。

* 加载：查找并加载类的二进制数据
* 连接
  * 验证：确保被加载的类的正确性
  * 准备：为类的静态变量分配内存，并将其初始化为默认值
  * 解析：把类中的符号引用转换为直接引用
* 初始化 ：为类的静态变量赋予正确的初始值，JVM负责对类进行初始化，主要对类变量进行初始化
* 结束生命周期
  * 执行了System.exit()方法
  * 程序正常执行结束
  * 程序在执行过程中遇到了异常或错误而异常终止
  * 由于操作系统出现错误而导致Java虚拟机进程终止

## 类加载器

寻找类加载器，先来一个小例子

```java
  package com.neo.classloader;
   public class ClassLoaderTest {
     public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());
    }
  }
```
运行后，输出结果：
```
sun.misc.Launcher$AppClassLoader@64fef26a
sun.misc.Launcher$ExtClassLoader@1ddd40f3
null
```
从上面的结果可以看出，并没有获取到ExtClassLoader的父Loader，原因是Bootstrap Loader（引导类加载器）是用C语言实现的，找不到一个确定的返回父Loader的方式，于是就返回null。




  
