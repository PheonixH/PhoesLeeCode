package leetcode.specialDataStruct;

import java.util.*;

/**
 * @program: PhoesLeeCode
 * @className: ThroneInheritance
 * @description: 1600. 皇位继承顺序
 * 一个王国里住着国王、他的孩子们、他的孙子们等等。每一个时间点，这个家庭里有人出生也有人死亡。
 * 这个王国有一个明确规定的皇位继承顺序，第一继承人总是国王自己。
 *
 * 执行用时：329 ms, 在所有 Java 提交中击败了100.00% 的用户
 * 内存消耗：100.9 MB, 在所有 Java 提交中击败了100.00% 的用户
 * @author: lov.moran
 * @date 2020-09-27 11:26
 */
class ThroneInheritance {

    private String king;

    private Set<String> deadMan;

    private Map<String, List<String>> consanguinity;

    private List<String> inheritanceOrder;

    /**
     * ThroneInheritance(string kingName) 初始化一个 ThroneInheritance 类的对象。
     * @param kingName 国王的名字
     */
    public ThroneInheritance(String kingName) {
        this.king = kingName;
        this.deadMan = new HashSet<>();
        this.consanguinity = new HashMap<>();
    }

    /**
     * void birth(string parentName, string childName)
     * 表示 parentName 新拥有了一个名为 childName 的孩子。
     *
     * @param parentName 父亲名字
     * @param childName 孩子名字
     */
    public void birth(String parentName, String childName) {
        this.consanguinity.putIfAbsent(parentName, new LinkedList<>());
        List<String> list = this.consanguinity.get(parentName);
        list.add(childName);
    }

    /**
     * void death(string name) 表示名为 name 的人死亡。
     * 一个人的死亡不会影响 Successor 函数，也不会影响当前的继承顺序。你可以只将这个人标记为死亡状态。
     * @param name 死者名字
     */
    public void death(String name) {
        this.deadMan.add(name);
    }

    /**
     * string[] getInheritanceOrder() 返回 除去 死亡人员的当前继承顺序列表。
     * @return 除去 死亡人员的当前继承顺序列表
     */
    public List<String> getInheritanceOrder() {
        this.inheritanceOrder = new LinkedList<>();
        updateDFS(king);
        return this.inheritanceOrder;
    }


    private void updateDFS(String nowKing) {
        if (!this.deadMan.contains(nowKing)) {
            inheritanceOrder.add(nowKing);
        }
        if (this.consanguinity.containsKey(nowKing)) {
            List<String> list = this.consanguinity.get(nowKing);
            int len = list.size();
            for (int i = 0; i < len; i++) {
                updateDFS(list.get(i));
            }
        }
    }
}
