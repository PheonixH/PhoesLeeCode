package 高并发.Java高并发编程详解多线程架构设计.Chapter16_SingleThreadExecetion模式;

/**
 * @program: PhoesLeeCode
 * @className: TablewarePair
 * @description: 餐具类优化--封装--避免交叉锁
 * @author: lov.moran
 * @date 2020-09-22 16:41
 */
public class TablewarePair {

    private final String leftTool;

    private final String righttTool;

    public TablewarePair(String leftTool, String righttTool) {
        this.leftTool = leftTool;
        this.righttTool = righttTool;
    }

    @Override
    public String toString() {
        return "TablewarePair{" +
                "leftTool='" + leftTool +
                ", righttTool='" + righttTool +
                '}';
    }

    public String getLeftTool() {
        return leftTool;
    }

    public String getRighttTool() {
        return righttTool;
    }
}
