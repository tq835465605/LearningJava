# WebService

Web service是一个平台独立的，低耦合的，自包含的、基于可编程的web的应用程序，因为它们基于一些常规的产业标准以及已有的一些技术，诸如标准通用标记语言下的子集XML、HTTP。

## 两种重要技术

要达到这样的目标，Web services要使用两种技术：

 * XML（标准通用标记语言下的一个子集）：
 
 XML是在web上传送结构化数据的伟大方式，Web services要以一种可靠的自动的方式操作数据，HTML（标准通用标记语言下的一个应用）不会满足要求，而XML可以使web services十分方便的处理数据，它的内容与表示的分离十分理想；XML是由万维网协会(W3C)创建。
 
 * SOAP（SOAP即简单对象访问协议(Simple Object Access Protocol)）：
 
 SOAP使用XML消息调用远程方法，这样web services可以通过HTTP协议的post和get方法与远程机器交互，而且，SOAP更加健壮和灵活易用；XML主要的优点在于它既与平台无关，又与厂商无关。它是用于交换XML（标准通用标记语言下的一个子集）编码信息的轻量级协议。
 

其他像UDDI和WSDL技术与XML和SOAP技术紧密结合用于服务发现。

## 技术支持
除了上面两个重要技术之外，还有

  * WSDL(Web Service描述语言WSDL)：
  　就是用机器能阅读的方式提供的一个正式描述文档而基于XML（标准通用标记语言下的一个子集）的语言，用于描述Web Service及其函数、参数和返回值。因为是基于XML的，所以WSDL既是机器可阅读的，又是人可阅读的。
  
## JAVA 实现服务发布

基于jdk1.6以上的javax.jws 发布webservice接口。

需要在需要发布的方法类上加上@WebService注解，代表该类为发布类。

可在需要发布的方法上加上@Method，代表该方法发布；

 具体调用方法可见当前目录下的demo。
 
**注**： 静态方法不被发布，@WebMentod(exclude=true)后，此方法不被发布，默认exclude=false

## JAVA 获取服务端方法并调用

* 方法一：
  
  在dos命令下输入 wsimport -s “src目录” -p “生成类所在包名” -keep “wsdl发布地址”  （前提配置java环境变量）
  
   示例：
   
   wsimport -s F:\workspace\src -p com.ws.jws -keep http://127.0.0.1:8080/Service/WebServiceDemo?wsdl 
   
* 方法二：

  使用apache的axis包来生成代码。
  
  格式：
  
 java -Djava.ext.dirs="axis所在路径" org.apache.axis.wsdl.WSDL2Java -o"输出代码路径" -p"定义代码所在的包名"    "wsdl发布地址"
 
  具体代码如下：
  
 ```dos
   set Axis_lib=C:\Users\Administrator\foxxms\xms.foxhis.com\pub\lib
   set Java_Cmd=C:\Users\Administrator\foxxms\xms.foxhis.com\jre\bin\java -Djava.ext.dirs=%Axis_lib% org.apache.axis.wsdl.WSDL2Java
   set OutPutPath=C:\WebServices
   set Package=com.foxhis.itf.EasWebservice

   %Java_Cmd% -o%OutPutPath% -p%Package% http://10.1.65.89:7888/web/services/WSBizInterfaceFacade?wsdl

   @echo ------------------ok------------------------

   @pause
   
 ```
 
