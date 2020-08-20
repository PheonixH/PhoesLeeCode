package 设计模式.SingleTon;

/**
 * @program: PhoesLeeCode
 * @className: Demo07
 * @description: 单例模式示例7：静态内部类方式 -- 完美写法1
 * 线程安全:JVM加载一个class的时候只会加载一次，所以Demo07Holder只会被加载一次，即只会生成一个INSTANCE
 * 类装载的时候不会初始化的Demo07Holder，当调用getInstance才会加载Demo07Holder
 * @author: lov.moran
 * @date 2020-08-19 11:08
 */
public class Demo07 {
    /**
     * 构造方法是private 只能Mgr01自己创建
     */
    private Demo07() {
    }

    private static class Demo07Holder{
        private final static Demo07 INSTANCE = new Demo07();
    }


    /**
     * 给别人一个调用INSTANCE的入口
     * 线程安全：因为加锁了
     * 但是效率降低了
     * @return INSTANCE
     */
    public static Demo07 getInstance() {
        return Demo07Holder.INSTANCE;
    }

    /**
     * 业务方法示例
     */
    public void fun() {
        System.out.println("Test");
    }

    /**
     * 调用示例
     */
    public static void main(String[] args) {
        //线程不安全测试
        //同一个类的不同对象的hashCode是不同的
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(Demo06.getInstance().hashCode())
            ).start();
        }
    }
}
