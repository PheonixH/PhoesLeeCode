package 设计模式.SingleTon;

/**
 * @program: PhoesLeeCode
 * @className: Demo03
 * @description: 单例模式示例3：懒汉式
 * 达到了按需初始化的目的，
 * 缺点：线程不安全
 * @author: lov.moran
 * @date 2020-08-19 10:44
 */
public class Demo03 {
    private static Demo03 INSTANCE;

    /**
     * 构造方法是private 只能Mgr01自己创建
     */
    private Demo03() {
    }

    /**
     * 给别人一个调用INSTANCE的入口
     * (线程不安全)
     *
     * @return INSTANCE
     */
    public static Demo03 getInstance() {
        if (INSTANCE == null) {
            //线程不安全测试（强行让new操作多1毫秒的休息时间，事实上不需要这个try
            try{
                Thread.sleep(1);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            INSTANCE = new Demo03();
        }
        return INSTANCE;
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
                    System.out.println(Demo03.getInstance().hashCode())
            ).start();
        }
    }
}
