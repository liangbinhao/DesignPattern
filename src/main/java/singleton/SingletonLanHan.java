package singleton;

public class SingletonLanHan {
    private static SingletonLanHan instance;

    private SingletonLanHan() {
    }

    public static synchronized SingletonLanHan getInstance() {
        if (instance == null) {
            instance = new SingletonLanHan();
        }
        return instance;
    }
}
