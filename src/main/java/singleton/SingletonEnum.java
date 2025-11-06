package singleton;

public enum SingletonEnum {
    instance;

    public void doSomething(){
        System.out.println("执行业务逻辑");
    }

    // 可以添加字段和构造函数
    private String data;

    // 枚举的构造函数必须是private的
    private SingletonEnum() {
        this.data = "单例数据";
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
