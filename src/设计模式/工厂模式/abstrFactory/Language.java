package 设计模式.工厂模式.abstrFactory;

/**
 * @program: PhoesLeeCode
 * @className: Language
 * @description:
 * 用接口也行  主要看语义
 * 名词用抽象类，形容词用接口：例如衣服、语言是一种抽象类的东西所以用抽象类   而移动方式等等更像一种属性所以用接口
 * @author: lov.moran
 * @date 2020-09-10 11:00
 */
public abstract class Language {
    public abstract void speak();
}
