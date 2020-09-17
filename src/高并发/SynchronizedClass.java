package 高并发;

/**
 * @program: PhoesLeeCode
 * @className: SynchronizedClass
 * @description: synchronized关键字
 * @author: lov.moran
 * @date 2020-09-16 11:52
 */
public class SynchronizedClass implements Runnable {
    //有synchronized 就不用添加 volatile 了
    private /*volatile*/ int val = 1;
    private static int v = 0;

    @Override
    public synchronized void run() {
        val += 1;
        System.out.println(Thread.currentThread().getName() + ":val = " + val);
    }


    /**
     * 对static加synchronized等同于synchronized(T.class)
     */
    public static void fun() {
        for (int i = 0; i < 100; i++) {
            v += 1;
            System.out.println(":val = " + v);
        }
    }


    public static void main(String[] args) {
        SynchronizedClass thread = new SynchronizedClass();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(thread, String.valueOf(i));
            t.start();
        }
    }
}
