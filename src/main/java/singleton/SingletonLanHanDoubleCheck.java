package singleton;

public class SingletonLanHanDoubleCheck {
    private static volatile SingletonLanHanDoubleCheck instance;

    private SingletonLanHanDoubleCheck() {
    }

    public static SingletonLanHanDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (SingletonLanHanDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingletonLanHanDoubleCheck();
                }
            }
        }

        return instance;
    }
}
