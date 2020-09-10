package 设计模式.工厂模式.abstrFactory.Chinese;

import 设计模式.工厂模式.abstrFactory.AbstractFactory;
import 设计模式.工厂模式.abstrFactory.Clothes;
import 设计模式.工厂模式.abstrFactory.Language;

/**
 * @program: PhoesLeeCode
 * @className: Chinese
 * @description: create a Chinese
 * @author: lov.moran
 * @date 2020-09-10 11:07
 */
public class ChineseFactory extends AbstractFactory {
    @Override
    public Language createLanguage() {
        return new LanguageChinese();
    }

    @Override
    public Clothes createClothes() {
        return new ClothesHanFu();
    }
}
