package 设计模式.SingleTon;

/**
 * @program: PhoesLeeCode
 * @className: Demo01
 * @description: 单例模式示例1：饿汉式 -- 最实用
 * 简单实用，推荐使用
 * 缺点：不管用到与否，类装载时候完成时候就完成实例化
 * 懒加载：Class.forName("Demo01") -- 不用就装它干嘛
 * @author: lov.moran
 * @date 2020-08-19 10:32
 */
public class Demo01 {
    private static final Demo01 INSTANCE = new Demo01();

    /**
     * 构造方法是private 只能Mgr01自己创建
     */
    private Demo01(){

    }

    /**
     * 给别人一个调用INSTANCE的入口
     * @return INSTANCE
     */
    private static Demo01 getInstance(){
        return INSTANCE;
    }

    /**
     * 业务方法示例
     * @param s 业务方法示例传参
     */
    public void fun(String s){
        System.out.println(s);
    }

    /**
     * 调用示例
     */
    public static void main(String[] args) {
        Demo01 m1 = Demo01.getInstance();
        m1.fun("Test");
    }
}
