package 设计模式.工厂模式.简单工厂和特定工厂;

import 设计模式.工厂模式.接口.Moveable;
import 设计模式.工厂模式.接口.Plane;

/**
 * @program: PhoesLeeCode
 * @className: PlaneFactory
 * @description: 生产 Plane 的工厂
 * @author: lov.moran
 * @date 2020-09-10 10:33
 */
public class PlaneFactory {

    public Moveable create(){
        System.out.println("create a plane");
        return new Plane();
    }
}
