package 设计模式.SingleTon;

/**
 * @program: PhoesLeeCode
 * @className: Demo02
 * @description: 单例模式示例2：饿汉式
 * 本质上和Demo01一样,只是用静态语句块初始化
 * @author: lov.moran
 * @date 2020-08-19 10:39
 */
public class Demo02 {
    private static final Demo02 INSTANCE;

    static {
        INSTANCE = new Demo02();
    }

    /**
     * 构造方法是private 只能Mgr01自己创建
     */
    private Demo02() {
    }

    /**
     * 给别人一个调用INSTANCE的入口
     *
     * @return INSTANCE
     */
    private static Demo02 getInstance() {
        return INSTANCE;
    }

    /**
     * 业务方法示例
     *
     * @param s 业务方法示例传参
     */
    public void fun(String s) {
        System.out.println(s);
    }

    /**
     * 调用示例
     */
    public static void main(String[] args) {
        Demo02 m1 = Demo02.getInstance();
        m1.fun("Test");
    }
}
