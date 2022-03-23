package com.it.run;

import com.it.bean.Business;
import com.it.bean.Customer;
import com.it.bean.Movie;
import com.it.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class MovieSystem {
    public static final List<User> allUsers = new ArrayList<>();
    public static final Map<Business, List<Movie>> allMovies = new HashMap<>();
    public static final Scanner sc = new Scanner(System.in);
    //定义一个静态变量记录当前登陆的用户
    public static User loginUser;
    public static final Logger LOGGER = LoggerFactory.getLogger("MovieSystem.class");
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    static {
        Customer c = new Customer();
        c.setLoginName("zyf888");
        c.setPassword("123456");
        c.setUserName("黑马刘德华");
        c.setSex('男');
        c.setMoney(10000);
        c.setPhone("110110");
        allUsers.add(c);

        Customer c1 = new Customer();
        c1.setLoginName("gzl888");
        c1.setPassword("123456");
        c1.setUserName("黑马关之琳");
        c1.setSex('女');
        c1.setMoney(2000);
        c1.setPhone("111111");
        allUsers.add(c1);

        Business b = new Business();
        b.setLoginName("baozugong888");
        b.setPassword("123456");
        b.setUserName("黑马包租公");
        b.setMoney(0);
        b.setSex('男');
        b.setPhone("110110");
        b.setAddress("火星6号2B二层");
        b.setShopName("甜甜圈国际影城");
        allUsers.add(b);
        List<Movie> movies = new ArrayList<>();
        allMovies.put(b, movies);


        Business b2 = new Business();
        b2.setLoginName("baozupo888");
        b2.setPassword("123456");
        b2.setUserName("黑马包租婆");
        b2.setMoney(0);
        b2.setSex('女');
        b2.setPhone("110110");
        b2.setAddress("火星8号8B八层");
        b2.setShopName("巧克力国际影城");
        allUsers.add(b2);
        List<Movie> movies2 = new ArrayList<>();
        allMovies.put(b2, movies2);
    }

    public static void main(String[] args) {
        showMain();
    }

    private static void showMain() {
        System.out.println("1.登录");
        System.out.println("2.用户注册");
        System.out.println("3.商家注册");
        System.out.println("请输入命令");
        String command = sc.nextLine();
        switch (command) {
            case "1":
                login();
                break;
            case "2":
                break;
            case "3":
                break;
            default:
                System.out.println("命令有误请重新输入");
        }
    }

    private static void login() {
        while (true) {
            System.out.println("请输入登录名称");
            String loginName = sc.nextLine();
            System.out.println("请输入密码");
            String password = sc.nextLine();
            User u = getUserByLoginName(loginName);
            if (u != null) {
                if (u.getPassword().equals(password)) {
                    loginUser = u;
                    if (u instanceof Customer) {
                        showCustomerMain();
                        return;
                    } else {
                        showBusinessMain();
                        return;
                    }
                } else {
                    System.out.println("用户密码错误请重新输入");
                }
            } else {
                System.out.println("登录名称错误请重新输入");
            }
        }
    }

    private static User getUserByLoginName(String loginName) {
        for (User user : allUsers) {
            if (user.getLoginName().equals(loginName)) {
                return user;
            }

        }
        return null;
    }

    private static void showBusinessMain() {
        while (true) {
            System.out.println("============黑马电影商家界面===================");
            System.out.println(loginUser.getUserName() + (loginUser.getSex() == '男' ? "先生" : "女士" + "欢迎您进入系统"));
            System.out.println("1、展示详情:");
            System.out.println("2、上架电影:");
            System.out.println("3、下架电影:");
            System.out.println("4、修改电影:");
            System.out.println("5、退出:");

            System.out.println("请输入您要操作的命令：");
            String command = sc.nextLine();
            switch (command) {
                case "1":
                    // 展示全部排片信息
                    showBusinessInfos();
                    break;
                case "2":
                    // 上架电影信息
                    addMovie();
                    break;
                case "3":
                    // 下架电影信息
                    deleteMovie();
                    break;
                case "4":
                    // 修改电影信息
                    updateMovie();
                    break;
                case "5":
                    System.out.println(loginUser.getUserName() + "请您下次再来啊~~~");
                    return; // 干掉方法
                default:
                    System.out.println("不存在该命令！！");
                    break;
            }
        }
    }

    private static void updateMovie() {
        System.out.println("================修改电影====================");
        Business business = (Business) loginUser;
        List<Movie> movies = allMovies.get(business);
        if (movies.size() == 0) {
            System.out.println("当期无片可以修改~~");
            return;
        }

        // 2、让用户选择需要下架的电影名称
        while (true) {
            System.out.println("请您输入需要修改的电影名称：");
            String movieName = sc.nextLine();

            // 3、去查询有没有这个影片对象。
            Movie movie = getMovieByMovieName(movieName);
            if (movie != null) {
                // 修改它
                System.out.println("请您输入修改后的片名：");
                String name = sc.nextLine();
                System.out.println("请您输入修改后主演：");
                String actor = sc.nextLine();
                System.out.println("请您输入修改后时长：");
                String time = sc.nextLine();
                System.out.println("请您输入修改后票价：");
                String price = sc.nextLine();
                System.out.println("请您输入修改后票数：");
                String totalNumber = sc.nextLine(); // 200\n
                while (true) {
                    try {
                        System.out.println("请您输入修改后的影片放映时间：");
                        String stime = sc.nextLine();

                        movie.setName(name);
                        movie.setActor(actor);
                        movie.setPrice(Double.valueOf(price));
                        movie.setTime(Double.valueOf(time));
                        movie.setNumber(Integer.valueOf(totalNumber));
                        movie.setStartTime(sdf.parse(stime));

                        System.out.println("恭喜您，您成功修改了该影片了！！！");
                        showBusinessInfos();
                        return; // 直接退出去
                    } catch (Exception e) {
                        e.printStackTrace();
                        LOGGER.error("时间解析出了毛病");
                    }
                }
            } else {
                System.out.println("您的店铺没有上架该影片！");
                System.out.println("请问继续修改吗？y/n");
                String command = sc.nextLine();
                switch (command) {
                    case "y":
                        break;
                    default:
                        System.out.println("好的！");
                        return;
                }
            }
        }
    }

    private static void deleteMovie() {
        Business business = (Business) loginUser;
        List<Movie> movies = allMovies.get(business);
        while (true) {
            System.out.println("请输入要下架的影片");
            String movieName = sc.nextLine();
            Movie movie = getMovieByMovieName(movieName);
            if (movie != null) {
                movies.remove(movie);
                System.out.println(movie.getName() + "已下架");
                showBusinessInfos();
                return;
            } else {
                System.out.println("该影片不存在");
                System.out.println("是否要继续y/n");
                String command = sc.nextLine();
                switch (command) {
                    case "y":
                        break;
                    case "n":
                        return;
                }

            }
        }
    }

    private static Movie getMovieByMovieName(String movieName) {
        Business business = (Business) loginUser;
        List<Movie> movies = allMovies.get(business);
        for (Movie movie : movies) {
            if (movie.getName().contains(movieName)) {
                return movie;
            }
        }
        return null;
    }

    private static void addMovie() {
        System.out.println("================上架电影====================");
        Business business = (Business) loginUser;
        List<Movie> movies = allMovies.get(business);
        System.out.println("请您输入新片名：");
        String name = sc.nextLine();
        System.out.println("请您输入主演：");
        String actor = sc.nextLine();
        System.out.println("请您输入时长：");
        String time = sc.nextLine();
        System.out.println("请您输入票价：");
        String price = sc.nextLine();
        System.out.println("请您输入票数：");
        String totalNumber = sc.nextLine(); // 200\n
        while (true) {
            try {
                System.out.println("请您输入影片放映时间：");
                String stime = sc.nextLine();
                // public Movie(String name, String actor, double time, double price, int number, Date startTime)        // 封装成电影对象 ，加入集合movices中去
                Movie movie = new Movie(name, actor, Double.valueOf(time), Double.valueOf(price)
                        , Integer.valueOf(totalNumber), sdf.parse(stime));
                movies.add(movie);
                System.out.println("您已经成功上架了：《" + movie.getName() + "》");
                return; // 直接退出去
            } catch (ParseException e) {
                e.printStackTrace();
                LOGGER.error("时间解析出了毛病");
            }

        }
    }

    private static void showBusinessInfos() {
        System.out.println("================商家详情界面=================");
        Business business = (Business) loginUser;
        List<Movie> movies = allMovies.get(business);
        LOGGER.info(loginUser.getUserName() + "商家，正在看自己的详情~~~");
        // 根据商家对象(就是登录的用户loginUser)，作为Map集合的键 提取对应的值就是其排片信息 ：Map<Business , List<Movie>> ALL_MOVIES
        System.out.println(business.getShopName() + "\t\t电话：" + business.getPhone()
                + "\t\t地址:" + business.getAddress() + "\t\t余额：" + business.getMoney());
        if (movies.size() > 0) {
            System.out.println("片名\t\t\t主演\t\t时长\t\t评分\t\t票价\t\t余票数量\t\t放映时间");
            for (Movie movie : movies) {
                System.out.println(movie.getName() + "\t\t\t" + movie.getActor() + "\t\t" + movie.getTime()
                        + "\t\t" + movie.getScore() + "\t\t" + movie.getPrice() + "\t\t" + movie.getNumber() + "\t\t"
                        + sdf.format(movie.getStartTime()));
            }
        } else {
            System.out.println("您的店铺当前无片在放映~~~~");
        }
    }

    private static void showCustomerMain() {
        while (true) {
            System.out.println("============黑马电影客户界面===================");
            System.out.println(loginUser.getUserName() + (loginUser.getSex() == '男' ? "先生" : "女士" + "欢迎您进入系统" +
                    "\t余额：" + loginUser.getMoney()));
            System.out.println("请您选择要操作的功能：");
            System.out.println("1、展示全部影片信息功能:");
            System.out.println("2、根据电影名称查询电影信息:");
            System.out.println("3、评分功能:");
            System.out.println("4、购票功能:");
            System.out.println("5、退出系统:");
            System.out.println("请输入您要操作的命令：");
            String command = sc.nextLine();
            switch (command) {
                case "1":
                    // 展示全部排片信息
                    showAllMovies();
                    break;
                case "2":
                    break;
                case "3":
                    // 评分功能
                    scoreMovie();
                    showAllMovies();
                    break;
                case "4":
                    // 购票功能
                    buyMovie();
                    break;
                case "5":
                    return; // 干掉方法
                default:
                    System.out.println("不存在该命令！！");
                    break;
            }
        }
    }

    public static final Map<String, List<Double>> MOVIES_SCORE = new HashMap<>();

    private static void scoreMovie() {
        // 1、查询当前登录成功的用户历史购买记录，看哪些电影是它可以评分的。
        Customer c = (Customer) loginUser;
        Map<String, Boolean> movies = c.getBuyMovies();
        if (movies.size() == 0) {
            System.out.println("当前您没有看过电影，不能评价！");
            return;
        }

        // 买过了 ，看哪些电影是它可以评分的。
        movies.forEach((name, flag) -> {
            if (flag) {
                System.out.println(name + "此电影已评价");
            } else {
                System.out.println("请您对：" + name + "进行打分（0-10）：");
                double score = Double.valueOf(sc.nextLine());

                // 先根据电影名称拿到评分数据
                List<Double> scores = MOVIES_SCORE.get(name); // MOVIES_SCORE = [ 名称=[10] , ... ]
                if (scores == null) {
                    // 说明此电影是第一次评价
                    scores = new ArrayList<>();
                    scores.add(score);
                    MOVIES_SCORE.put(name, scores);
                } else {
                    scores.add(score);
                }

                movies.put(name, true);
            }
        });
    }

    private static void buyMovie() {
        showAllMovies();
        System.out.println("=============用户购票功能=================");
        while (true) {
            System.out.println("请您输入需要买票的门店：");
            String shopName = sc.nextLine();
            // 1、查询是否存在该商家。
            Business business = getBusinessByShopName(shopName);
            if (business == null) {
                System.out.println("对不起，没有该店铺！请确认");
            } else {
                // 2、此商家全部的排片
                List<Movie> movies = allMovies.get(business);
                // 3、判断是否存在上映的电影
                if (movies.size() > 0) {
                    // 4、开始进行选片购买
                    while (true) {
                        System.out.println("请您输入需要购买电影名称：");
                        String movieName = sc.nextLine();
                        // 去当前商家下，查询该电影对象。
                        Movie movie = getMovieByShopAndName(business, movieName);
                        if (movie != null) {
                            // 开始购买
                            while (true) {
                                System.out.println("请您输入要购买的电影票数：");
                                String number = sc.nextLine();
                                int buyNumber = Integer.valueOf(number);
                                // 判断电影是否购票
                                if (movie.getNumber() >= buyNumber) {
                                    // 可以购买了
                                    // 当前需要花费的金额
                                    double money = BigDecimal.valueOf(movie.getPrice()).multiply(BigDecimal.valueOf(buyNumber))
                                            .doubleValue();
                                    if (loginUser.getMoney() >= money) {
                                        // 终于可以买票了
                                        System.out.println("您成功购买了" + movie.getName() + buyNumber +
                                                "张票！总金额是：" + money);
                                        // 更新自己的金额 更新商家的金额
                                        loginUser.setMoney(loginUser.getMoney() - money);
                                        business.setMoney(business.getMoney() + money);
                                        movie.setNumber(movie.getNumber() - buyNumber);

                                        Customer c = (Customer) loginUser;
                                        // 记录购买电影的信息
                                        // 第一个参数是购买的电影，第二个参数是没有评价的标记！
                                        c.getBuyMovies().put(movie.getName(), false);

                                        return;// 结束方法
                                    } else {
                                        // 钱不够！
                                        System.out.println("是否继续~~");
                                        System.out.println("是否继续买票？y/n");
                                        String command = sc.nextLine();
                                        switch (command) {
                                            case "y":
                                                break;
                                            default:
                                                System.out.println("好的！");
                                                return;
                                        }
                                    }
                                } else {
                                    // 票数不够
                                    System.out.println("您当前最多可以购买：" + movie.getNumber());
                                    System.out.println("是否继续买票？y/n");
                                    String command = sc.nextLine();
                                    switch (command) {
                                        case "y":
                                            break;
                                        default:
                                            System.out.println("好的！");
                                            return;
                                    }
                                }
                            }

                        } else {
                            System.out.println("电影名称有毛病~~");
                        }
                    }

                } else {
                    System.out.println("该电影院关门了~~~");
                    System.out.println("是否继续买票？y/n");
                    String command = sc.nextLine();
                    switch (command) {
                        case "y":
                            break;
                        default:
                            System.out.println("好的！");
                            return;
                    }
                }
            }
        }
    }

    private static Movie getMovieByShopAndName(Business business, String movieName) {
        List<Movie> movies = allMovies.get(business);
        for (Movie movie : movies) {
            if (movie.getName().contains(movieName)) {
                return movie;
            }
        }
        return null;
    }

    private static Business getBusinessByShopName(String shopName) {
        Set<Business> businesses = allMovies.keySet();
        for (Business business : businesses) {
            if (business.getShopName().equals(shopName)) {
                return business;
            }
        }
        return null;
    }

    private static void showAllMovies() {
        System.out.println("=============展示全部商家排片信息=================");
        allMovies.forEach((business, movies) -> {
            System.out.println(business.getShopName() + "\t\t电话：" + business.getPhone() + "\t\t地址:" + business.getAddress());
            System.out.println("\t\t\t片名\t\t\t主演\t\t时长\t\t评分\t\t票价\t\t余票数量\t\t放映时间");
            for (Movie movie : movies) {
                System.out.println("\t\t\t" + movie.getName() + "\t\t\t" + movie.getActor() + "\t\t" + movie.getTime()
                        + "\t\t" + movie.getScore() + "\t\t" + movie.getPrice() + "\t\t" + movie.getNumber() + "\t\t"
                        + sdf.format(movie.getStartTime()));
            }
        });
    }

}
