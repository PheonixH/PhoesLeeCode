package 设计模式.工厂模式.abstrFactory.Japanese;

import 设计模式.工厂模式.abstrFactory.Language;

/**
 * @program: PhoesLeeCode
 * @className: LanguageChinese
 * @description: 语言
 * @author: lov.moran
 * @date 2020-09-10 10:52
 */
public class LanguageJapanese extends Language {

    @Override
    public void speak() {
        System.out.println("I speak Japanese");
    }
}
