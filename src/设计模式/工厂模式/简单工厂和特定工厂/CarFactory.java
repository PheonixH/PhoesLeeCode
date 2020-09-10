package 设计模式.工厂模式.简单工厂和特定工厂;

import 设计模式.工厂模式.接口.Car;
import 设计模式.工厂模式.接口.Moveable;

/**
 * @program: PhoesLeeCode
 * @className: CarFactory
 * @description: 生产Car 的工厂
 * @author: lov.moran
 * @date 2020-09-10 10:33
 */
public class CarFactory {

    public Moveable create(){
        System.out.println("create a car");
        return new Car();
    }
}
