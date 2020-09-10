package 设计模式.工厂模式.abstrFactory;

/**
 * @program: PhoesLeeCode
 * @className: AbstractFactory
 * @description:
 * @author: lov.moran
 * @date 2020-09-10 11:02
 */
public abstract class AbstractFactory {
    public abstract Language createLanguage();
    public abstract Clothes createClothes();
}
