/**
 * @ProjectName: PhoesLeeCode
 * @Package: PACKAGE_NAME
 * @ClassName: Node
 * @Description: 四叉树接口
 * @Author: Pheonix
 * @CreateDate: 2018/12/24 16:38
 * @Version: 1.0
 */
public class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
