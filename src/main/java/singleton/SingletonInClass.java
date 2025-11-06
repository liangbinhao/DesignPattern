package singleton;

public class SingletonInClass {
    private SingletonInClass(){}

    private static class SingletonHolder{
        private static final SingletonInClass instance = new SingletonInClass();
    }

    public static SingletonInClass getInstance(){
        return SingletonHolder.instance;
    }
}
