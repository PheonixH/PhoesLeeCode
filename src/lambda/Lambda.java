package lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @program: PhoesLeeCode
 * @className: lambda.Lambda
 * @description: java lambda test
 * @author: lov.moran
 * @date 2020-07-09 16:20
 */
public class Lambda {

    /**
     * 1.使用() -> {} 替代匿名类
     * 现在Runnable线程，Swing，JavaFX的事件监听器代码等，在java 8中你可以使用Lambda表达式替代丑陋的匿名类。
     */
    public static void function0() {
        //Before Java 8:
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8 ");
            }
        }).start();

        //In Java 8:
        new Thread(()->{System.out.println("In Java8");}).start();

    }

    /**
     * 2.使用内循环替代外循环
     * 为了支持函数编程，Java 8加入了一个新的包java.util.function，其中有一个接口java.util.function.Predicate是支持Lambda函数编程：
     */
    public static void function1() {
        //Prior Java 8 :
        List<String> features = Arrays.asList("Lambdas", "Default Method",
                "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }

        System.out.println("========================");

        //In Java 8:
        features.forEach(n -> System.out.println(n));
    }


    public static void main(String[] args) {
        function0();
    }
}
