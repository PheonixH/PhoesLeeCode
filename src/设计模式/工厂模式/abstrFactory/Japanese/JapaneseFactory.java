package 设计模式.工厂模式.abstrFactory.Japanese;

import 设计模式.工厂模式.abstrFactory.AbstractFactory;
import 设计模式.工厂模式.abstrFactory.Clothes;
import 设计模式.工厂模式.abstrFactory.Language;

/**
 * @program: PhoesLeeCode
 * @className: JavpaneseFactory
 * @description: create a Japanese
 * @author: lov.moran
 * @date 2020-09-10 11:09
 */
public class JapaneseFactory extends AbstractFactory {

    @Override
    public Language createLanguage() {
        return new LanguageJapanese();
    }

    @Override
    public Clothes createClothes() {
        return new ClothesKimono();
    }
}
