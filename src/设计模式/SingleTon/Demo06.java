package 设计模式.SingleTon;

/**
 * @program: PhoesLeeCode
 * @className: Demo06
 * @description: 单例模式示例6：双重检查单例 -- 较完美写法
 * 线程安全，因为上锁之后还判断一次INSTANCE是否为空
 * @author: lov.moran
 * @date 2020-08-19 11:05
 */
public class Demo06 {
    //JAVA汇编语言优化时，
    //如果没有 volatile JIT 语句重排的时候可能会返回空的INSTANCE
    private static volatile Demo06 INSTANCE;

    /**
     * 构造方法是private 只能Mgr01自己创建
     */
    private Demo06() {
    }

    /**
     * 给别人一个调用INSTANCE的入口
     * 线程安全：因为加锁了
     * 但是效率降低了
     * @return INSTANCE
     */
    public static synchronized Demo06 getInstance() {
        //第一次判断是为了跳过获取锁，节省时间
        if (INSTANCE == null) {
            //妄图通过减少同分布代码块的方式提高效率
            synchronized(Demo06.class) {
                //线程安全测试（强行让new操作多1毫秒的休息时间，事实上不需要这个try
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Demo06();
                }
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
                    System.out.println(Demo06.getInstance().hashCode())
            ).start();
        }
    }
}
