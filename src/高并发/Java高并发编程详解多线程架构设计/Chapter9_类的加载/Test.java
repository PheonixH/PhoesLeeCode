package 高并发.Java高并发编程详解多线程架构设计.Chapter9_类的加载;

/**
 * @program: PhoesLeeCode
 * @description: 类的加载和测试
 * 测试1输出结果是1， 1
 * 当Flag 1和Flag 2交换顺序之后会输出 0, 1
 * 原因是类加载时候准备阶段和初始化阶段赋值导致的！具体解析见书本9.4章
 * @author: Feng.H
 * @create: 2020-09-20 16:59
 **/
public class Test {

    //Flag 1
    private static int x = 0;
    private static int y;

    //Flag 2
    private static Test test = new Test();

    private Test() {
        x++;
        y++;
    }

    public static Test getInstance() {
        return test;
    }

    public static void main(String[] args) {
        //测试1
        Test test = Test.getInstance();
        System.out.println(test.x);
        System.out.println(test.y);

        //测试2
        System.out.println("Bootstrap: " + String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
