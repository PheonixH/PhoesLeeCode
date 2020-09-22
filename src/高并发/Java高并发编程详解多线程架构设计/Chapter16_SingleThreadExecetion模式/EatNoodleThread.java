package 高并发.Java高并发编程详解多线程架构设计.Chapter16_SingleThreadExecetion模式;

/**
 * @program: PhoesLeeCode
 * @className: EatNoodleThread
 * @description: 吃面条线程
 * @author: lov.moran
 * @date 2020-09-22 16:30
 */
public class EatNoodleThread extends Thread {

    // 顾客
    private final String name;

    // 顾客左手边的餐具
    private final Tableware leftTool;

    // 顾客右手边的餐具
    private final Tableware rightTool;


    public EatNoodleThread(String name, Tableware leftTool, Tableware rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {
        // 顾客一有机会就开始吃面
        while (true) {
            this.eat();
        }
    }

    // 顾客吃面过程
    private void eat() {
        // 先拿起左手边的餐具
        synchronized (leftTool) {
            System.out.println(name + " take up " + leftTool + "(left).");
            // 再拿起右手边的餐具
            synchronized (rightTool) {
                System.out.println(name + " take up " + rightTool + "(right).");
                System.out.println(name + " is eating now!");
                System.out.println(name + " will put down " + rightTool + "(right).");
            }
            System.out.println(name + " will put down " + leftTool + "(left).");
        }
    }

    // 测试
    public static void main(String[] args) {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
        new EatNoodleThread("Jonh", fork, knife).start();
        new EatNoodleThread("Tom", knife, fork).start();
    }
}
