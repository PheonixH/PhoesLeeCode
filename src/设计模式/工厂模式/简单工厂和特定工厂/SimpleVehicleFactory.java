package 设计模式.工厂模式.简单工厂和特定工厂;

import 设计模式.工厂模式.接口.Car;
import 设计模式.工厂模式.接口.Plane;

/**
 * @program: PhoesLeeCode
 * @className: VehicleFactory
 * @description: Vehicle Factory 简单工厂 可扩展性不好
 * @author: lov.moran
 * @date 2020-09-10 10:30
 */
public class SimpleVehicleFactory {

    public Car createCar() {
        //...
        return new Car();
    }

    public Plane createPlane() {
        //...
        return new Plane();
    }
}
