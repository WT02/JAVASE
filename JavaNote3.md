# 2022/3/22

- 异常处理

  - 异常概述、体系

  - 常见运行时异常

  - 常见编译时异常

  - 异常的默认处理流程

  - 编译时异常的处理机制

  - 运行时异常的处理机制

  - 异常处理使代码更稳健的案例

  - 自定义异常
  
- 日志框架

  

  ## 异常处理

- 异常是代码在编译或者执行的过程中可能出现的错误。
### 异常分为两类：编译时异常、运行时异常
- 编译时异常：没有继承RuntimeExcpetion的异常，编译阶段就会出错
- 运行时异常：继承自RuntimeException的异常或其子类，编译阶段不报错，运行可能报错
### 常见运行时异常
- 数组索引越界异常：ArrayIndexOutOfBoundsException
- 空指针异常：NullPointerException，直接输出没有问题，但是调用空指针的变量的功能就会报错
- 数字操作异常:ArithmeticException
- 类型转换异常：ClassCastException
- 数字转换异常：NumberFormatException
### 常见编译时异常
- 实例
  ```java
  String date="2015-01-12 10:12:21";
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  Date d=sdf.parse(date);
  System.out.println(d);
  ```

  目的在于提醒不要出错
### 默认处理流程
1. 默认会在出现异常的代码那里自动的创建一个异常对象：ArithmeticException
2. 异常会从方法中出现的点这里抛出给调用者，调用者最终抛出给JVM虚拟机
3. 虚拟机接收到异常对象后，先在控制台直接输出异常栈信息数据
4. 直接从当前执行的异常点干掉当前程序
5. 后续代码没有机会执行了，因为程序已经死亡

### 编译时异常的处理机制
编译时宜昌市编译阶段就出错，所以必须处理，否则代码根本无法通过
- 编译时异常的处理形式有三种：
1. 出现异常直接抛也继续抛出去
2. 出现异常自己捕获处理，不麻烦别人
3. 前两者结合，出现异常直接抛出去给调用者，调用者捕获处理

#### 异常处理方式1---throws
- throws:用在方法上，可以将方法内部出现的异常抛出去给本方法的调用者处理
- 这种方式并不好，发生异常的方法自己不处理异常，如果异常最终跑出去给虚拟机引起程序死亡
抛出异常格式：
`方法throws 异常1，异常2，异常3..{
}`
规范做法：
`方法 throws Exception{
}`

#### 异常处理方式2---try...catch...
- 监视捕获异常，用在方法内怒，可以将方法内部出现的异常直接捕获处理
- 这种方式还可以发生异常的方法自己独立完成异常处理，程序可以继续往下执行。
格式：                                                  
`try{
}catch（异常类型1 变量）{
}catch（异常类型2 变量）{
}...`
建议格式：
`try{
}catch(Exception e){
	e.printStackTrace();
}`

#### 异常处理方式3---前两则会结合

- 方法直接将异常通过throws跑出去给调用者
- 调用者受到异常后直接捕获处理

### 运行时异常的处理形式
- 运行时异常编译阶段不会出错，运行时才可能出错的，所以编译阶段不处理也可以
- 按照规范建议还是处理：建议在最外层条用集中捕获处理

### 异常处理使代码更稳健的案例
需求
- 键盘录入一个合理的价格为止（必须时数值且大于0）
分析
- 定义一个死循环，让用户不断地输入价格
```java
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (true) {
            try {
                String str=sc.nextLine();
                double price=Double.valueOf(str);
                if(price>0){
                    System.out.println("价格：" + price);
                    break;
                }else{
                    System.out.println("输入价格不是正数");
                }
            } catch (NumberFormatException e) {
                System.out.println("用户输入的数据有问题");
            }
        }
    }
}
```

### 自定义异常
1. 自定义编译时异常
- 定义一个异常类继承Exception
- 重写构造器
- 再出现异常的地方用 throw new自定义对象抛出

```java
public class IlleagalException extends Exception{
    public IlleagalException() {
    }

    public IlleagalException(String message) {
        super(message);
    }
}
```

```java
public class definr {
    public static void main(String[] args) {
        try {
            check(-34);
        } catch (IlleagalException e) {
            e.printStackTrace();
        }
    }
    public static void check(int age) throws IlleagalException {
        if(age<0||age>200){
            //抛出一个异常对象给调用者
            //throw：在方法内直接创建一个异常对象，并从给点抛出
            //throws：用在方法神明上，抛出方法内部异常
            throw new IlleagalException(age+"输入不合法");
        }else{
            System.out.println("年龄合法");
        }
    }
}
```

作用：编译时异常时编译阶段就报错，提醒更加强烈，一定要处理！！

2. 自定义运行时异常

- 定义一个异常类继承RuntimeException
- 重写构造器
- 在出现异常的地方用throw new自定义对象抛出！！

```java
public class IlleagalRunTimeException extends RunTimeException{
    public IlleagalException() {
    }

    public IlleagalException(String message) {
        super(message);
    }
}
```

```java
public class definr {
    public static void main(String[] args) {
        try {
            check(-34);
        } catch (IlleagalException e) {
            e.printStackTrace();
        }
    }
    public static void check(int age) {
        if(age<0||age>200){
            //抛出一个异常对象给调用者
            //throw：在方法内直接创建一个异常对象，并从给点抛出
            //throws：用在方法神明上，抛出方法内部异常
            throw new IlleagalRunTimeException(age+"输入不合法");
        }else{
            System.out.println("年龄合法");
        }
    }
}
```
作用：提醒不强烈，编译阶段不报错！！运行时才可能出现！！
