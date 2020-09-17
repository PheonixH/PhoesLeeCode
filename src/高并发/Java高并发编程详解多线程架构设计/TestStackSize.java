package 高并发.Java高并发编程详解多线程架构设计;

/**
 * @program: PhoesLeeCode
 * @className: TestStackSize
 * @description: 探寻stacksize不同值对线程的影响
 * @author: lov.moran
 * @date 2020-09-16 16:10
 */
public class TestStackSize {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter the stack size");
        }

        ThreadGroup threadGroup = new ThreadGroup("Test-Group");

        Runnable runnable = new Runnable() {
            final int max = Integer.MAX_VALUE;

            @Override
            public void run() {
                int i = 0;
                recurse(i);
            }

            private void recurse(int i) {
                System.out.println(i);
                if (i < max) {
                    recurse(i + 1);
                }
            }
        };
        Thread thread = new Thread(threadGroup, runnable, "Test", Integer.parseInt(args[0]));
        thread.start();
    }
}
