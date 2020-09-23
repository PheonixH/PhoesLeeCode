package 高并发.Java高并发编程详解多线程架构设计.Chapter16_SingleThreadExecetion模式;

/**
 * @program: PhoesLeeCode
 * @className: EatNoodleThreadWithPair
 * @description: 改用一组餐具吃饭避免交叉锁
 * @author: lov.moran
 * @date 2020-09-22 16:45
 */
public class EatNoodleThreadWithPair extends Thread {


    // 顾客
    private final String name;

    // 顾客的餐具
    private final TablewarePair tablewarePair;

    public EatNoodleThreadWithPair(String name, TablewarePair tablewarePair) {
        this.name = name;
        this.tablewarePair = tablewarePair;
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
        synchronized (tablewarePair) {
            System.out.println(name + " take up " + tablewarePair.getLeftTool() + "(left).");
            // 再拿起右手边的餐具
            System.out.println(name + " take up " + tablewarePair.getRighttTool() + "(right).");
            System.out.println(name + " is eating now!");
            System.out.println(name + " will put down " + tablewarePair.getRighttTool() + "(right).");
            System.out.println(name + " will put down " + tablewarePair.getLeftTool() + "(left).");
        }
    }

    // 测试
    public static void main(String[] args) {
        TablewarePair tablewarePair = new TablewarePair("fork", "knife");
        new EatNoodleThreadWithPair("Jonh", tablewarePair).start();
        new EatNoodleThreadWithPair("Tom", tablewarePair).start();
    }
}
