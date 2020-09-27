package leetcode.datestruct;

import java.util.*;

/**
 * @program: PhoesLeeCode
 * @className: ThroneInheritance
 * @description:
 * @author: lov.moran
 * @date 2020-09-27 11:26
 */
public class ThroneInheritance {

    private String kingName;

    private Map<String, List<String>> map;

    private Set<String> deadMan;

    public ThroneInheritance(String kingName) {
        this.kingName = kingName;
        map = new HashMap<>();
        deadMan = new HashSet<>();
        map.put(kingName, new LinkedList<>());
    }

    public void birth(String parentName, String childName) {
//        map.putIfAbsent(parentName, new LinkedList<>());
        List<String> tmp = map.get(parentName);
        tmp.add(childName);
        map.putIfAbsent(parentName, tmp);
    }

    public void death(String name) {
        deadMan.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> res = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.add(kingName);
        while (!stack.empty()) {
            String tmp = stack.pop();
            if (!deadMan.contains(tmp)) {
                res.add(tmp);
            }
            List<String> ch = map.get(tmp);
            for (int i = ch.size() - 1; i >= 0; i--) {
                stack.add(ch.get(i));
            }
        }
        return res;
    }
}
