# 2022/3/21

- 创建不可变集合
- Stream流
  - Stream流的概述
  - Stream流的获取
  - Stream流的常用API
  - Stream流的综合应用
  - 收集Stream流

## 创建不可变集合

### 什么是不可变集合

- 不可变集合，就是不可被修改的集合
- 集合的数据项在创建的时候提供，并且在整个生命周期中都不可改变，否则报错

### 为什么要创建不可变集合

- 如果某个数据不能被修改，把它防御性地拷贝到不可变集合中是个很好的实践。
- 或者当集合对象被不可信的库调用时，不可变形式是安全的。

### 如何创建不可变集合

- 在List、Set、Map接口中，都存在of方法，可以创建一个不可变的集合。

`List<E> list= List.of (E...element)`

`Set<E> set= Set.of (E...element)`

`Map<K,V> map= Map.of (K,v...element)`



## Stream流

### 什么是Stream流

- 基于Lambda地函数式编程
- 目的：简化集合和数组操作的API

### 体验Stream流的作用

```java
public class StreamTest1 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        Collections.addAll(names, "张三丰", "张无忌", "赵敏", "周芷若", "张强");
        不使用Stream流
//        List<String> zhangList=new ArrayList<>();
//        List<String> zhang3List=new ArrayList<>();
//        for (String name : names) {
//            if(name.startsWith("张")){
//                zhangList.add(name);
//                if(name.length()==3){
//                    zhang3List.add(name);
//                }
//            }
//        }
//        System.out.println(zhangList);
//        System.out.println(zhang3List);
		使用Stream流
        names.stream().filter(s -> s.startsWith("张")).filter(s -> s.length() == 3).forEach(s -> System.out.print(s));
    }
}
```





### Stream流的核心思想

1. 先得到集合或者数组的Stream流（一根传送带）
2. 把元素放上去
3. 然后使用Stream流简化操作

### Stream流的三类方法

- 获取Stream流
- 中间方法
- 终结方法



## Stream流的获取

### 集合获取Stream流的方式

- 可以使用Collection接口中的默认方法stream()生成流

```java
/**Collection集合获取流*/
Collection<String> list=new ArrayList<>();
Stream<String> s=list.stream();
/**Map集合获取流*/
Map<String,Integer> maps=new HashMap<>();
//键流
Stream<String> keys=maps.keySet().stream();
//值流
Stream<Integer> values=maps.values().stream();
//键值对流
Stream<Map.Entry<String,Integer>> keyAndValueStream=maps.entrySet().stream();
```

- 数组获取Stream流的方式

```java
/**数组获取流*/
String[] names={"a","b","c"};
Stream<String> nameStream=Arrays.stream(names);
Stream<String> nameStream2=Stream.of(names);
```



## Stream流的常用API（中间操作方法）

```java
public class StreamDemo3 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        Collections.addAll(names, "张三丰", "张无忌", "赵敏", "周芷若", "张强");
        names.stream().filter(s -> s.startsWith("张")).limit(2).forEach(System.out::println);
        names.stream().filter(s -> s.startsWith("张")).skip(2).forEach(System.out::println);

        //Map加工方法   原材料参数->加工后的参数
        names.stream().map(s -> "加工后" + s).forEach(System.out::print);

        //把所有名字加工成学生类
        //names.stream().map(s -> new Student(s)).forEach(System.out::println);
        names.stream().map(Student::new).forEach(System.out::println);
		//去除重复元素
		names.stream().distinct();
        //合并流
        Stream<String> s1 = names.stream().filter(s -> s.startsWith("张"));
        Stream<String> s2 = Stream.of("java1", "java2");
        Stream<String> s3 = Stream.concat(s1, s2);
        s3.forEach(s -> System.out.println(s));
    }
}
```



**注意**

- 中间方法也称为非终结方法，调用完成后返回新的Stream流可以继续使用，支持链式编程。
- 在Stream流中无法直接修改集合、数组中的数据。



## Stream流的综合应用

需求：某个公司的开发部门，分为开发一部和而不，现在需要进行年终数据结算分析：

1. 员工信息至少包含了（名称、性别、工资、奖金、出发记录）
2. 开发一部有4个员工、开发二部有5名员工
3. 分别筛选出2个部门的最高工资的员工信息，封装成优秀员工对象Topperformer
4. 分别统计出2个部门的平均月收入，要求去掉最好和最低工资
5. 统计2个开发部门整体的平均工资，去掉最低和最高工资的平均值

```java
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class StreamCase {
    public static double allMoney = 0;
    public static double allMoney2 = 0;

    public static void main(String[] args) {
        List<Employee> one = new ArrayList<>();
        one.add(new Employee("zhubajie", '男', 30000, 25000, null));
        one.add(new Employee("shaseng", '男', 25000, 1000, "迟到"));
        one.add(new Employee("sunwukong", '男', 20000, 20000, null));
        one.add(new Employee("tangseng", '男', 20000, 25000, null));
        List<Employee> two = new ArrayList<>();
        two.add(new Employee("张三", '男', 15000, 9000, null));
        two.add(new Employee("李四", '男', 20000, 10000, null));
        two.add(new Employee("王五", '男', 50000, 100000, null));
        two.add(new Employee("六六", '女', 3500, 1000, null));
        two.add(new Employee("七七", '女', 20000, 0, null));
        
        Topperformer t = one.stream().max((o1, o2) -> Double.compare(o1.getBonus() + o1.getSalary(), o2.getBonus() + o2.getSalary()))
                .map(e -> new Topperformer(e.getName(), e.getBonus() + e.getSalary())).get();

        one.stream().sorted((o1, o2) -> Double.compare(o1.getBonus() + o1.getSalary(), o2.getBonus() + o2.getSalary()))
                .skip(1).limit(one.size() - 2).forEach(employee -> allMoney += (employee.getBonus() + employee.getSalary()));
        System.out.println("开发一部平均工资为" + allMoney / (one.size() - 2));

        Stream<Employee> s1 = one.stream();
        Stream<Employee> s2 = two.stream();
        Stream<Employee> s3 = Stream.concat(s1, s2);
        s3.sorted((o1, o2) -> Double.compare(o1.getBonus() + o1.getSalary(), o2.getBonus() + o2.getSalary()))
                .skip(1).limit(one.size() + two.size() - 2).forEach(e -> {
                    allMoney2 += (e.getBonus() + e.getSalary());
                });
        BigDecimal a = BigDecimal.valueOf(allMoney2);
        BigDecimal b = BigDecimal.valueOf(one.size() + two.size() - 2);
        System.out.println("两个部门的平均工资为" + a.divide(b, 2, RoundingMode.HALF_UP));
    }
}
```



## 收集Stream流

- Stream流：方便操作集合/数组的手段。

- 集合/数组：才是开发中的目的。

  ```java
  import java.util.ArrayList;
  import java.util.Collections;
  import java.util.List;
  import java.util.Set;
  import java.util.function.IntFunction;
  import java.util.stream.Collectors;
  import java.util.stream.Stream;
  
  public class StreamDemo4 {
      public static void main(String[] args) {
          List<String> names = new ArrayList<>();
          Collections.addAll(names, "张三丰", "张无忌", "赵敏", "周芷若", "张强");
  
          Stream<String> stream = names.stream().filter(s -> s.startsWith("张"));
          List<String> list = stream.collect(Collectors.toList());
  
          //流只能收集一次
          Stream<String> stream2 = names.stream().filter(s -> s.startsWith("张"));
          Set<String> set = stream2.collect(Collectors.toSet());
  
          Stream<String> stream3 = names.stream().filter(s -> s.startsWith("张"));
          //Object[] arry= stream3.toArray(); 泛型不能约束流的类型
  
  //        String[] arry=stream3.toArray(new IntFunction<String[]>() {
  //            @Override
  //            public String[] apply(int value) {
  //                return new String[value];
  //            }
  //        })
          // String [] arry=stream3.toArray(s->new String[s]); 简化
  
          String[] arry = stream3.toArray(String[]::new);
      }
  }
  ```