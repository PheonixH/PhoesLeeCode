package 设计模式.工厂模式.接口;

/**
 * @program: PhoesLeeCode
 * @className: Plane
 * @description:
 * @author: lov.moran
 * @date 2020-09-10 10:25
 */
public class Plane implements Moveable {

    @Override
    public void go() {
        System.out.println("Plane go!");
    }
}
