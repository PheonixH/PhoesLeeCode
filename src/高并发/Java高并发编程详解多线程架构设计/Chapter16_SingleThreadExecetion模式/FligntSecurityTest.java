package 高并发.Java高并发编程详解多线程架构设计.Chapter16_SingleThreadExecetion模式;

/**
 * @program: PhoesLeeCode
 * @className: FligntSecurityTest
 * @description: 机场安检口类安全测试
 * @author: lov.moran
 * @date 2020-09-22 16:12
 */
public class FligntSecurityTest {

    static class Passengers extends Thread {
        private final FligntSecurity fligntSecurity_;

        private final String idCard;

        private final String boardingPass;

        public Passengers(FligntSecurity fligntSecurity_, String idCard, String boardingPass) {
            this.fligntSecurity_ = fligntSecurity_;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {
            // 游客不断经过安检口
            while (true) {
                fligntSecurity_.pass(boardingPass, idCard);
            }
        }
    }

    // 会报错： 即使是首字母相同合法的游客
    // 原因：

    public static void main(String[] args) {
        final FligntSecurity fligntSecurity_ = new FligntSecurity();
        new Passengers(fligntSecurity_, "A123564", "AF213").start();
        new Passengers(fligntSecurity_, "B125465", "BF456").start();
        new Passengers(fligntSecurity_, "C456456", "CF789").start();
    }

}
