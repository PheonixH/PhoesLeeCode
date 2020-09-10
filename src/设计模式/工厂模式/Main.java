package 设计模式.工厂模式;

import 设计模式.工厂模式.abstrFactory.AbstractFactory;
import 设计模式.工厂模式.abstrFactory.Chinese.ChineseFactory;
import 设计模式.工厂模式.abstrFactory.Clothes;
import 设计模式.工厂模式.abstrFactory.Language;
import 设计模式.工厂模式.接口.Car;
import 设计模式.工厂模式.接口.Moveable;
import 设计模式.工厂模式.接口.Plane;
import 设计模式.工厂模式.简单工厂和特定工厂.CarFactory;
import 设计模式.工厂模式.简单工厂和特定工厂.PlaneFactory;

/**
 * @program: PhoesLeeCode
 * @className: Main
 * @description: 抽象类和接口：
 * 区分主要看语义 名词用抽象类，形容词用接口：例如衣服、语言是一种抽象类的东西所以用抽象类   而移动方式等等更像一种属性所以用接口
 * @author: lov.moran
 * @date 2020-09-10 10:27
 */
public class Main {
    public static void main(String[] args) {
        //任意定制交通工具
        //使用接口
        Moveable m1 = new Car();
        m1.go();
        Moveable m2 = new Plane();
        m2.go();

        //任意定制生产过程
        //使用工厂
        Moveable m3 = new CarFactory().create();
        m3.go();
        Moveable m4 = new PlaneFactory().create();
        m4.go();

        //任意定制产品一族
        //例如在以下场景中把语言和服饰绑定作为一族
        //如果要改为日本人就修改ChineseFactory为JapaneseFactory就可以
        AbstractFactory person = new ChineseFactory();
        Clothes clothes = person.createClothes();
        Language language = person.createLanguage();
        clothes.dress();
        language.speak();
    }
}
