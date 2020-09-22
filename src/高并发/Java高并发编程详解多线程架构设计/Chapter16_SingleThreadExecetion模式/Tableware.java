package 高并发.Java高并发编程详解多线程架构设计.Chapter16_SingleThreadExecetion模式;

/**
 * @program: PhoesLeeCode
 * @className: Tableware
 * @description: 餐具类
 * @author: lov.moran
 * @date 2020-09-22 16:29
 */
public class Tableware {

    private final String toolName;


    public Tableware(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return "toolName :" + toolName;
    }
}
