package 设计模式.工厂模式.abstrFactory.Chinese;

import 设计模式.工厂模式.abstrFactory.Clothes;

/**
 * @program: PhoesLeeCode
 * @className: Clothes
 * @description: 衣服
 * @author: lov.moran
 * @date 2020-09-10 10:48
 */
public class ClothesHanFu extends Clothes {

    @Override
    public void dress() {
        System.out.println("I wear Han-Fu!");
    }
}
