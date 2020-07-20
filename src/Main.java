/**
 * @program: PhoesLeeCode
 * @description:
 * @author: pheonix
 * @create: 2019-12-20 20:53
 */
// 本题为考试单行多行输入输出规范示例，无需提交，不计分。

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public void test() {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        String[] ss = str.split("/");
        int n = ss.length;
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < n; i++) {
            if ("..".equals(ss[i])) {
                if (!stack.empty()) {
                    stack.pop();
                }
            } else if (".".equals(ss[i]) || "".equals(ss[i])) {
                continue;
            } else {
                stack.push(ss[i]);
            }
        }
        String res = "";
        while (!stack.empty()) {
            res = "/" + stack.pop() + res;
        }
        if ("".equals(res)) {
            res = "/";
        }
        System.out.println(res);
    }

    public void test2() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int a = in.nextInt();
            int b = in.nextInt();
            for (int i = a; i <= b; i++) {
                if (i % 15 == 0) {
                    System.out.println("foobar");
                } else if (i % 3 == 0) {
                    System.out.println("foo");
                } else if (i % 5 == 0) {
                    System.out.println("bar");
                } else {
                    System.out.println(i);
                }
            }
        }
        return;
    }

    public void test3(){
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int fr = in.nextInt();
            String b = in.next();
            int to = in.nextInt();
            char[] value = b.toCharArray();
            int key = 0;
            boolean flag = false;
            for (int i = 0; i < value.length; i++) {
                if (value[i] >= 'a' && value[i] <= 'z') {
                    key = key * fr + (int) (value[i] - 'a') + 10;
                } else if (value[i] >= '0' && value[i] <= '9') {
                    key = key * fr + (int) (value[i] - '0');
                } else if (value[i] >= 'A' && value[i] <= 'Z') {
                    key = key * fr + (int) (value[i] - 'A') + 10;
                } else if ("-".equals(value[i])) {
                    flag = true;
                }
            }
            String res = "";
            while (key > 0) {
                int p = key % to;
                key = key / to;
                res = String.valueOf(p) + res;
            }
            if ("".equals(res)) {
                res = "0";
            }
            if (flag) {
                res = "-" + res;
            }
            System.out.println(res);
        }
    }

    public static void main(String[] args) {


        return;
    }
}
