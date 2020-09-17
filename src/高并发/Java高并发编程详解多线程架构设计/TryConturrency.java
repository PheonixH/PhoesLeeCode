package 高并发.Java高并发编程详解多线程架构设计;

/**
 * @program: PhoesLeeCode
 * @className: TryConturrency
 * @description: 同时/交替  看新闻和听音乐
 * @author: lov.moran
 * @date 2020-09-16 15:21
 */
public class TryConturrency {
    public static void listenMusic() {
        for (int i = 0; i < 100; i++) {

            System.out.println("I am listening music!");
        }
    }

    public static void readNew() {
        for (int i = 0; i < 100; i++) {
            System.out.println("I am reading news!");
        }
    }

    public static void main(String[] args) {
        new Thread(TryConturrency::listenMusic).start();
        readNew();
    }
}
