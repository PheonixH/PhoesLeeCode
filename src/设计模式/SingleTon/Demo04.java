package 设计模式.SingleTon;

/**
 * @program: PhoesLeeCode
 * @className: Demo04
 * @description: 单例模式示例4：加锁
 * 线程安全
 * 缺点：加锁之后效率降低
 * @author: lov.moran
 * @date 2020-08-19 10:58
 */
public class Demo04 {

    private static Demo04 INSTANCE;

    /**
     * 构造方法是private 只能Mgr01自己创建
     */
    private Demo04() {
    }

    /**
     * 给别人一个调用INSTANCE的入口
     * 线程安全：因为加锁了
     * 但是效率降低了
     * @return INSTANCE
     */
    public static synchronized Demo04 getInstance() {
        if (INSTANCE == null) {
            //线程不安全测试（强行让new操作多1毫秒的休息时间，事实上不需要这个try
            try{
                Thread.sleep(1);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            INSTANCE = new Demo04();
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
                    System.out.println(Demo04.getInstance().hashCode())
            ).start();
        }
    }
}
