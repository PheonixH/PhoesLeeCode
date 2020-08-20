package 设计模式.SingleTon;

/**
 * @program: PhoesLeeCode
 * @className: Demo05
 * @description: 单例模式示例5：加锁失败
 * 线程不安全，因为这个锁并没有办法阻止判断，相当于没有加锁
 * @author: lov.moran
 * @date 2020-08-19 11:01
 */
public class Demo05 {
    private static Demo05 INSTANCE;

    /**
     * 构造方法是private 只能Mgr01自己创建
     */
    private Demo05() {
    }

    /**
     * 给别人一个调用INSTANCE的入口
     * 线程安全：因为加锁了
     * 但是效率降低了
     * @return INSTANCE
     */
    public static synchronized Demo05 getInstance() {
        if (INSTANCE == null) {
            //妄图通过减少同分布代码块的方式提高效率
            synchronized(Demo05.class) {
                //线程安全测试（强行让new操作多1毫秒的休息时间，事实上不需要这个try
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Demo05();
            }
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
                    System.out.println(Demo05.getInstance().hashCode())
            ).start();
        }
    }
}
