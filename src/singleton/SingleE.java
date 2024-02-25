package singleton;
/**
 *   因为是类构造器直接初始化 没有线程安全问题
 *   可能存在的问题：
 *   1.由于在类加载的时候就实例化了，造成资源的浪费(但是这个浪费可以忽略，所以这种方式也是推荐使用的)。
 *   2.如果初始化本身依赖于一些其他数据，那么也就很难保证其他数据会在它初始化之前准备好。
 *
 */
public class SingleE {
    private static final SingleE instance = new SingleE();
    private SingleE(){};
    public static SingleE getSingleE(){
        return instance;
    }
}
