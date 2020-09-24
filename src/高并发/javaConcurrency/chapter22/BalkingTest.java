package 高并发.javaConcurrency.chapter22;

/**
 * Balking 设计模式测试
 */
public class BalkingTest {

    public static void main(String[] args){
        new DocumentEditThread("E:\\Code\\PhoesLeeCode\\src\\高并发\\javaConcurrency\\chapter22\\","document.txt").start();
    }
}
