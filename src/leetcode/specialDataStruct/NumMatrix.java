package leetcode.specialDataStruct;

/**
 * @program: PhoesLeeCode
 * @description: 304. 二维区域和检索 - 矩阵不可变
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 * Range Sum Query 2D
 * <p>
 * 执行用时：14 ms, 在所有 Java 提交中击败了98.81% 的用户
 * 内存消耗：44 MB, 在所有 Java 提交中击败了76.07% 的用户
 * @author: Huang Feng
 * @create: 2021-03-02 11:38
 **/
public class NumMatrix {

    private int[][] data;

    public NumMatrix(int[][] matrix) {
        int n = matrix.length;
        if (n <= 0) {
            return;
        }
        int m = matrix[0].length;
        data = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            data[i + 1][1] = matrix[i][0];
            for (int j = 1; j < m; j++) {
                data[i + 1][j + 1] = matrix[i][j] + data[i + 1][j];
            }
        }
        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) {
                data[i + 1][j + 1] = data[i + 1][j + 1] + data[i][j + 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return data[row2 + 1][col2 + 1] - data[row1][col2 + 1] - data[row2 + 1][col1] + data[row1][col1];
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(arr);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }
}
