package 设计模式.工厂模式.接口;

/**
 * @program: PhoesLeeCode
 * @className: Car
 * @description: Car Factory
 * @author: lov.moran
 * @date 2020-09-10 10:23
 */
public class Car implements Moveable {

    @Override
    public void go() {
        System.out.println("Car go!");
    }
}
