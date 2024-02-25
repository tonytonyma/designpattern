package singleton;

public class SingleLazyLock {
//    private static SingleLazyLock instance = null;
//    private SingleLazyLock(){};
//    public static SingleLazyLock getSingleLazy(){
//        if(instance==null){
//            synchronized (SingleLazyLock.class){
//                //如果多个线程因线程锁停在锁外 线程执行完 还是会进入并创建实例对象 可以在锁里再
//                //进行一次判断 这样就避免了问题 这种方式也被称为DCL 双重校验锁
//                instance = new SingleLazyLock();
//            }
//        }
//        return instance;
//    }
    /**
     * 主要在于instance = new SingleLazyLock();这句，
     * 这并非是一个原子操作，事实上在 JVM 中这句话大概做了下面 3 件事情。
     * 1. 给 instance分配内存
     * 2. 调用 instance的构造函数来初始化成员变量，形成实例
     * 3. 将instance对象指向分配的内存空间（执行完这步 singleton才是非 null 了）
     * 但是在 JVM 的即时编译器中存在指令重排序的优化。
     * 也就是说上面的第二步和第三步的顺序是不能保证的，
     * 最终的执行顺序可能是 1-2-3 也可能是 1-3-2。
     * 如果是后者，则在 3 执行完毕、2 未执行之前，被线程二抢占了，
     * 这时 instance 已经是非 null 了（但却没有初始化），
     * 所以线程二会直接返回 instance，然后使用，然后顺理成章地报错。
     * volatile关键字的一个作用是禁止指令重排，把instance声明为volatile之后，
     * 对它的写操作就会有一个内存屏障，这样，在它的赋值完成之前，就不用会调用读操作。
     * volatile阻止的不是instance = new Singleton()这句话内部[1-2-3]的指令重排，而是保证了在一个写操作（[1-2-3]）完成之前，
     * 不会调用读操作（if (instance == null)）。
     */
    private static volatile SingleLazyLock instance = null;
    private SingleLazyLock(){};
    public static SingleLazyLock getSingleLazy(){
        /**
         * 第一个if (instance == null)，解决效率问题只有instance为null的时候，
         * 才进入synchronized的代码段大大减少了几率。
         * 第二个if (instance == null)，是为了防止可能出现多个实例的情况。
         */
        if(instance==null){
            synchronized (SingleLazyLock.class){
                if(instance==null){
                    instance = new SingleLazyLock();
                }
            }
        }
        return instance;
    }
}
