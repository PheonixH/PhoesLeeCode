package 设计模式.SingleTon;

/**
 * @program: PhoesLeeCode
 * @className: Demo08
 * @description: 单例模式示例8：Enum 枚举单例 -- 完美中的完美
 * 不止可以解决线程同步问题，还可以防止反序列化
 * 序列化：
 * 反序列化：
 * JAVA 可以通过class绕到内存  通过反射的方式new一个INSTANCE
 * 而枚举类的构造方法是构造类而不是方法的
 * @author: lov.moran
 * @date 2020-08-19 11:16
 */
public enum Demo08 {
    INSTANCE;

    public void fun() {
        System.out.println("Test");
    }

    public static void main(String[] args) {
        //线程不安全测试
        //同一个类的不同对象的hashCode是不同的
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Demo08 m1 = Demo08.INSTANCE;
                m1.fun();
                System.out.println(Demo06.getInstance().hashCode());
            }
            ).start();
        }
    }
}
