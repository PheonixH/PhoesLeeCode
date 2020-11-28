package leetcode.specialDataStruct;

/**
 * @program: PhoesLeeCode
 * @className: LeetCode.data.SubrectangleQueries
 * @description:
 * @author: lov.moran
 * @date 2020/6/13 下午10:47
 */
public class SubrectangleQueries {
    private int[][] data;
    private int rows;
    private int cols;

    public SubrectangleQueries(int[][] rectangle) {
        rows = rectangle.length;
        cols = rectangle[0].length;
        data = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = rectangle[i][j];
            }
        }
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                data[i][j] = newValue;
            }
        }
    }

    public int getValue(int row, int col) {
        return data[row][col];
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 1}, {4, 3, 4}, {3, 2, 1}, {1, 1, 1}};
        SubrectangleQueries subrectangleQueries = new SubrectangleQueries(arr);
        System.out.println(subrectangleQueries.getValue(0, 2));
        subrectangleQueries.updateSubrectangle(0, 0, 3, 2, 5);
        System.out.println(subrectangleQueries.getValue(0, 2));
        System.out.println(subrectangleQueries.getValue(3, 1));

        subrectangleQueries.updateSubrectangle(3, 0, 3, 2, 10);
        System.out.println(subrectangleQueries.getValue(3, 1));

        System.out.println(subrectangleQueries.getValue(0, 2));


    }
}
