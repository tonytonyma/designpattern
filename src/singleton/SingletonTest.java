package singleton;

public class SingletonTest {
    public static void main(String[] args) {
        //饿汉
        SingleE singleton = SingleE.getSingleE();
        //懒汉
        SingleLazy singleLazy = SingleLazy.getSingleLazy();
        //懒汉锁
        SingleLazyLock singleLazyLock = SingleLazyLock.getSingleLazy();
    }
}
