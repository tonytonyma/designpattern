package singleton;
/**
 *  通过判断instance是否为空 达成懒加载 但是如果有多个线程同时进入
 *  就可能导致创建了多个实例对象
 */
public class SingleLazy {
    private static SingleLazy instance = null;
    private SingleLazy(){}
    public static SingleLazy getSingleLazy(){
        if(instance == null){
            instance = new SingleLazy();
        }
        return instance;
    }
}
