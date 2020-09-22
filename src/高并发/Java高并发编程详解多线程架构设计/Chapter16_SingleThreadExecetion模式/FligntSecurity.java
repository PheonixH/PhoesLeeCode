package 高并发.Java高并发编程详解多线程架构设计.Chapter16_SingleThreadExecetion模式;

/**
 * @program: PhoesLeeCode
 * @className: FligntSecurity_UnSafe
 * @description: 非线程安全的安检口类
 * @author: lov.moran
 * @date 2020-09-22 16:05
 */
public class FligntSecurity {

    private int count = 0;

    //登机牌
    private String boardingPass = "null";

    //身份证
    private String idCard = "null";

    // 线程安全方法改进： 对这个赋值的方法加一个锁， 例如改为：
//    public synchronized void pass(String boardingPass, String idCard) {...}
    public void pass(String boardingPass, String idCard) {
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    private void check() {
        // 简单的测试， 当登机牌和身份证首字母不相同时候表示不通过
        if (boardingPass.charAt(0) != idCard.charAt(0)) {
            throw new RuntimeException("=====================Exception============================" + toString());
        }
    }

    @Override
    public String toString() {
        return "The " + count + " passengers, boardingPass [" + boardingPass + "], idCard [" + idCard + " ]";
    }

}
