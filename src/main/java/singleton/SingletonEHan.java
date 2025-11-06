package singleton;

public class SingletonEHan {
    private static SingletonEHan instance = new SingletonEHan();
    private SingletonEHan(){}
    public static SingletonEHan getInstance(){
        return instance;
    }
}
