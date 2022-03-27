# 2022/3/27
- 日志框架
  - 日志技术的概述
  - 日志技术体系结构
  - Logback概述
  - Logback快速入门
  - Logback配置详解-输出位置、格式设置
  - Logback配置详解-日志级别设置


## 日志技术的概述

- 日志技术可以将系统执行的信息选择性的记录到指定位置
- 可以随时以开关的形式控制是否记录日志、无序修改源代码



## 日志技术体系

![image-20220327215115266](https://cdn.jsdelivr.net/gh/WT02/clouding/img/202203272151369.png)



## Logback概述

- Logback是基于slf4j的日志规范实现的框架。
- logback-core： logback-core 模块为其他两个模块奠定了基础，必须有。
- logback-classic：它是log4j的一个改良版本，同时它完整实现了slf4j API。
- logback-access 模块与 Tomcat 和 Jetty 等 Servlet 容器集成，以提供 HTTP 访问日志功能。



## Logback快速入门

1. 在项目下新建文件夹lib，导入Logback的相关jar包到该文件夹下，并添加到项目库中去。
2. 必须将Logback的核心配置文件logback.xml直接拷贝到src目录下。
3. 在代码中获取日志的对象。
4.   使用日志对象输出日志信息。



## Logback配置详解-输出位置、格式设置

Logback日志系统的特性都是通过核心配置文件logback.xml控制的。

**Logback日志输出位置、格式设置：**

- 通过logback.xml 中的<append>标签可以设置输出位置和日志信息的详细格式。
-  通常可以设置2个日志输出位置：一个是控制台、一个是系统文件中。

**输出到控制台的配置标志**

`<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">`

**输出到系统文件的配置标志**

`<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">`



## Logback配置详解-日志级别设置

l**用于控制系统中哪些日志级别是可以输出的。**

- 级别程度依次是：TRACE< DEBUG< INFO<WARN<ERROR ; 默认级别是debug（忽略大小写），对应其方法。

- 作用：用于控制系统中哪些日志级别是可以输出的，只输出级别不低于设定级别的日志信息。

- ALL  和 OFF分别是打开全部日志信息，及关闭全部日志信息。

**具体在<root level="INFO">标签的level属性中设置日志级别。**

`<root level=“INFO">
   <appender-ref ref="CONSOLE"/>
   <appender-ref ref="FILE" />
 </root>`

  

  
