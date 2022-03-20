#  2020/3/20



- Set系列集合
  - Set系列集系概述
  - HashSet元素无序的底层原理：哈希表
  - HashSet元素去重复的底层原理
  - 实现类：LinkedHashSet
  - 实现类：TreeSet
- Collection体系的特点、使用场景总结
- 补充知识：可变参数
- 补充知识：集合工具类Collections
- Collections体系的综合案例：斗地主
- Map集合体系
  - Map集合的概述
  - Map集合体系特点
  - Map集合常用API
  - Map集合的遍历方式一：键找值
  - Map集合的遍历方式二：键值对
  - Map集合的遍历方式三：lambda表达式
  - Map集合的实现类HashMap
  - Map集合的实现类LinkedHashMap
  - Map集合的实现类TreeMap


- 补充知识：集合的嵌套

----------



## Set系列集合



### Set系列集合的特点



- 无序：存取顺序不一致。
- 不重复：可以去除重复。
- 无索引：没有带索引的方法，所以不能使用普通的for循环遍历，也不能通过索引来获取元素。

### Set集合实现类的特点

- HashSet：无序、不重复、无索引。
- LinkedHashSet：**有序**、不重复、无索引。
- TreeSet：**排序**、不重复、无索引。



## HashSet元素无序的底层原理：哈希表

### HashSet底层原理

- HashSet集合底层采取哈希表存储的数据。
- 哈希表是一种对于增删改查数据性能都较好的结构。

### 哈希表的组成

- JDK8之前，底层使用**数组+链表。**
- JDK8之后，底层采用**数组+链表+红黑树**。

### 哈希值

- 是JDK根据对象的**地址**，按照某种规则算出来的int类型的**数值**。

### Object类的API

- `public int hasCode(): 返回对象的哈希值`

### 对象的哈希值特点

- 同一对象多次调用hashCode()方法返回的哈希值是相同的。
- 默认情况下，不同对象的哈希值是不同的。

![image-20220320115219298](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320115219298.png)



### JDK1.8开始HashSet原理解析

- 底层结构：哈希表（数组、链表、红黑树的结合体）。
- 当挂在下面的数据过多时，查询性能降低；当链表长度超过8时，自动转换为红黑树。

![image-20220320132932295](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320132932295.png)

![image-20220320133008676](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320133008676.png)

哈希表的详细流程

1. 创建一个默认长度16，默认jiazaiyinwei0.75的数组，数组名为table。
2. 根据元素的哈希值跟数组长度计算出英寸入的位置。
3. 判断当前位置是否为null，如果是则直接存入，若不是，表示有元素，则调用equals方法比较属性值，如果一样，则不存，如果不一样，则存入数组。
4. 当数组存满到16*0.75=12时，就自动扩容，每次扩容为原先的两倍。



## HashSet元素去重复的底层原理

![image-20220320133929134](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320133929134.png)

- 在类中用快捷键重写hashCode()和equals()方法



## 实现类：LinkedHashSet

![image-20220320135012555](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320135012555.png)





## 实现类：TreeSet

###  TreeSet集合的概述和特点



- 不重复、无索引、可排序。
- 可排序：按照元素的大小默认升序。
- TreeSet集合底层是基于红黑数的数据结构后实现排序的，增删改查性能都很好。
- **$\textcolor{red}{TreeSet集合是一定要排序的，可以将元素按照指定规则进行排序}$**

### TreeSet集合的默认规则

- 对于数值类型：Integer，Double，官方默认按照大小进行升序排序。
- 对于字符串类型：默认按照首字符的标号升序排序。
- 对于自定义类型如Student对象，TreeSet无法直接排序。

**$\textcolor{red}{想要使用TreeSet存储自定义类型，需要制定排序规则}$**

### 自定义排序规则

- **方式一**:让自定义类实现Comparable接口重写里面的compareTo方法来制定比较规则。
- **方式二**:TreeSet集合有参数构造器，可以设置Comparator接口对应的比较器对象，来制定规则。

#### 两种方式中，关于返回值的规则：

- 如果认为第一个元素大于第二个元素返回正整数即可。
- 如果认为第一个元素小于第二个元素返回负整数即可。
- 如果认为第一个元素等于第二个元素返回0即可，此时TreeSet集合会只保留一个元素，认为两者重复。

**$\textcolor{red}{注意：如果TreeSet集合存储对象有实现比较规则，集合也自带比较器，默认使用集合自带的比较器排序}$**



## Collection体系的特点、使用场景总结

1. 如果希望元素可以重复，又有索引，索引查询要快

- 用ArrayList集合，基于数组的。（用的最多）

2. 如果希望元素可以重复，又有索引，增删首尾操作快

- 用LinkedList集合，基于链表的。

3. 如果希望增删改查都快，但是元素不重复、无序、无索引

- 用HashSet集合，基于哈希表。

4. 如果希望增删改查都快，但是元素不重复、有序、无索引。

- 用LinkedHashSet集合，基于哈希表和双链表。

5. 如果要对对象进行排序

- 用TreeSet集合，基于红黑树。后续也可以用List集合实现排序。



## 补充知识：可变参数

### 可变参数

- 可变参数用在形参中可以接受多个数据
- 可变参数的格式：$\textcolor{red}{参数类型...参数名称}$

### 可变参数的作用

- 传输参数非常灵活，方便。可以不传，传一个或多个参数
- $\texecolor{red}{可变参数在方法内部本质上就是一个数组}$

### 可变参数的注意事项：

- 一个形参列表中可变参数只能有一个
- 可变参数必循放在形参列表的最后面



## 补充知识：集合工具类Collections

- java.utils.Collections:是集合工具类
- 作用:Collections并不属于集合，是用来操作集合的工具类

### Collections常用的API

`public static <T> boolean addAll(Collection<? super T> c, T...element) 给集合对象批量添加元素`

`public static void shuffle(List<?> list) 打乱list集合的顺序`

### Collections排序相关的API

- 使用范围：只能对于List集合的排序

#### 排序方法1：

`public static <T> void sort（List<T> list） 将集合中的元素按照默认规则排序`

注意：本方式不可以直接对自定义类型的List集合排序，除非自定义类型实现了比较规则Comparable的接口。

#### 排序方式2：

`public static <T> void sort(List<T>  list, Comparator<? super T>  c) 将集合中的元素按照指定规则排序`

## Collections体系的综合案例：斗地主

```java
package com.ITKing.date320collections;

public class Cards {
    private String size;
    private String color;
    private int index;//牌的真正大小

    public Cards() {
    }

    public Cards(String size, String color, int index) {
        this.size = size;
        this.color = color;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return size + color;
    }
}
```



```java
package com.ITKing.date320collections;

import java.util.*;

public class GameDemo {
    public static List<Cards> allCards = new ArrayList<>();

    static {
        String[] sizes = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        String[] colors = {"♠", "♣", "♥", "♦"};
        int index = 0;
        for (String size : sizes) {
            index++;
            for (String color : colors) {
                Cards card = new Cards(size, color, index);
                allCards.add(card);
            }
        }
        Cards c1 = new Cards("", "☺", ++index);
        Cards c2 = new Cards("", "☻", ++index);
        Collections.addAll(allCards, c1, c2);
        System.out.println(allCards);
    }

    public static void main(String[] args) {
        Collections.shuffle(allCards);
        System.out.println(allCards);
        //开始发牌
        List<Cards> player1 = new ArrayList<>();
        List<Cards> player2 = new ArrayList<>();
        List<Cards> player3 = new ArrayList<>();
        for (int i = 0; i < allCards.size() - 3; i++) {
            Cards c = allCards.get(i);
            if (i % 3 == 0) {
                player1.add(c);
            } else if (i % 3 == 1) {
                player2.add(c);
            } else if (i % 3 == 2) {
                player3.add(c);
            }
        }
        //截取最后三张牌
        List<Cards> lastThreeCards = allCards.subList(allCards.size() - 3, allCards.size());
        //排序牌
        sortCards(player1);
        sortCards(player2);
        sortCards(player3);
        //输出玩家的牌
        System.out.println("player1:" + player1);
        System.out.println("player2:" + player2);
        System.out.println("player3:" + player3);
        System.out.println("底牌：" + lastThreeCards);
    }

    private static void sortCards(List<Cards> cards) {
        Collections.sort(cards, new Comparator<Cards>() {
            @Override
            public int compare(Cards o1, Cards o2) {
                return o1.getIndex() - o2.getIndex();
            }
        });
    }
}
```

## Map集合体系

### Map集合概述和使用

- Map集合是一种双列集合，每个元素包含两个数据。
- Map集合的每个元素格式：key=value（键值对元素）。
- Map集合也被称为“$\textcolor{red}{键值对集合}$”。

#### Map集合整体格式

- Collection集合的格式：[元素1，元素2，元素3...]
- Map集合的完整格式：{key1=value1,key2=value2,key3=value3,...}

![image-20220320164338513](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320164338513.png)

### Map集合体系特点

![image-20220320164548311](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320164548311.png)

![image-20220320164644752](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320164644752.png)

- Map集合的特点都是由键决定的
- Map集合的键是无序，不重复的，无索引的，值不做要求（可重复）。
- Map集合后面重复的键对应的值会覆盖前面重复键的值。
- Map集合的键值对都可以null。

#### Map集合实现类特点

- HashMap：元素按照键是无序，不重复，无索引，值不做要求。
- LinkedHashMap：元素按照键是$\textcolor{red}{有序}$，不重复，无索引，值不做要求。
- TreeMap：元素按照键是$\textcolor{red}{排序}$，不重复，无索引，值不做要求。

### Map集合常用API

#### Map集合

- Map是双列集合的祖宗接口，它的功能是全部双列集合都可以继承使用的。

#### Map API 如下：

`V put(K key, V value) 添加元素`

`V get (Object key)  根据键得到键值对元素`

`V remove (Object key)  根据键删除键值对元素`

`void clear() 移除所有的键值对元素`

`boolean containsKey(Object key) 判断集合是否包含指定的键`

`boolean containsValue(Object value) 判断集合是否包含指定的值`

`boolean is Empty() 判断集合是否为空`

`int  size() 集合的长度，即集合中键值对的个数`

`V1. putAll(V2) 把集合map2中的元素拷贝一份到map1中，会覆盖，map2不变`



### Map集合的遍历方式一：键找值

- 先获取Map集合的全部键的Set集合。
- 遍历键的Set集合，然后通过键提取对应的值。

 涉及到的API

`Set<K> keySet() 获取所有间的集合`

`V get(Object key) 根据键获取值`



### Map集合的遍历方式二：键值对

- 先把Map集合转换为Set集合，Set集合中每个元素都是键值对的实体类型。
- 遍历Set集合，然后提取键及提取值

涉及到的API

`Set<Map.Entry<K,V>> entrySet() 获取所有键值对对象的集合`

`K getKey() 获取键`

`V getValue() 获取值`

 

### Map集合的遍历方式三：lambda表达式

![image-20220320200414182](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320200414182.png)



```java
package com.ITKing.date320map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapTest {
    public static void main(String[] args) {
        String[] ch = {"A", "B", "C", "D"};
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 80; i++) {
            stringBuilder.append(ch[random.nextInt(ch.length)]);
        }
        System.out.println(stringBuilder);
        Map<Character, Integer> maps = new HashMap<>();
        for (int i = 0; i < stringBuilder.length(); i++) {
            char ch3 = stringBuilder.charAt(i);
            if (maps.containsKey(ch3)) {
                maps.put(ch3, maps.get(ch3) + 1);
            } else {
                maps.put(ch3, 1);
            }
        }
        System.out.println(maps);
    }
}
```



### Map集合的实现类HashMap

![image-20220320203038604](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320203038604.png)

![image-20220320204439673](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320204439673.png)



### Map集合的实现类LinkedHashMap

![image-20220320204545674](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320204545674.png)



### Map集合的实现类TreeMap

![image-20220320204826312](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320204826312.png)

![image-20220320205301243](C:\Users\Bently\AppData\Roaming\Typora\typora-user-images\image-20220320205301243.png)

## 补充知识：集合的嵌套

```java
package com.ITKing.date320map;

import java.util.*;

public class MapTestExtends {
    public static void main(String[] args) {
        Map<String, List<String>> maps = new HashMap<>();
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "A", "B");
        maps.put("张三", list);
        List<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "C", "B");
        maps.put("李四", list1);
        List<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "A", "D");
        maps.put("王五", list2);
        System.out.println(maps);
        Map<String, Integer> infos = new HashMap<>();
        Collection<List<String>> values = maps.values();
        for (List<String> value : values) {
            for (String s : value) {
                if (infos.containsKey(s)) {
                    infos.put(s, infos.get(s) + 1);
                } else {
                    infos.put(s, 1);
                }
            }
        }
        System.out.println(infos);
    }
}
```







