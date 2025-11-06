package singleton;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class TestSingletonInClass {

    @Test
    @DisplayName("基础静态内部类单例测试")
    public void testBasicSingleton() {
        SingletonInClass instance1 = SingletonInClass.getInstance();
        SingletonInClass instance2 = SingletonInClass.getInstance();
        
        // 验证是否为同一实例
        assertSame(instance1, instance2, "两次获取的实例应相同");
        assertEquals(instance1.hashCode(), instance2.hashCode(), "哈希码应该相同");
    }
    
    @Test
    @DisplayName("多线程静态内部类单例测试")
    public void testConcurrentSingleton() throws InterruptedException {
        int threadCount = 100;
        Thread[] threads = new Thread[threadCount];
        SingletonInClass[] instances = new SingletonInClass[threadCount];
        
        // 创建多个线程同时获取单例实例
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                instances[index] = SingletonInClass.getInstance();
            });
        }
        
        // 启动所有线程
        for (Thread thread : threads) {
            thread.start();
        }
        
        // 等待所有线程完成
        for (Thread thread : threads) {
            thread.join();
        }
        
        // 验证所有线程获取到的是否为同一实例
        SingletonInClass firstInstance = instances[0];
        assertNotNull(firstInstance, "第一个实例不应为null");
        
        for (int i = 1; i < threadCount; i++) {
            assertSame(firstInstance, instances[i], 
                "所有线程获取的实例应该相同，但第" + i + "个实例不同");
        }
    }
    
    @Test
    @DisplayName("延迟加载测试")
    public void testLazyLoading() {
        // 在调用getInstance之前，SingletonHolder不应该被初始化
        // 这个测试主要验证类加载机制，实际效果需要通过JVM监控工具观察
        assertFalse(isSingletonHolderInitialized(), 
            "SingletonHolder在调用getInstance前不应被初始化");
            
        // 调用getInstance后，实例应该被创建
        SingletonInClass instance = SingletonInClass.getInstance();
        assertNotNull(instance, "实例不应为null");
    }
    
    // 辅助方法：检查SingletonHolder是否已被初始化
    private boolean isSingletonHolderInitialized() {
        try {
            // 获取应用程序类加载器
            ClassLoader classLoader = SingletonInClass.class.getClassLoader();
            if (classLoader == null) {
                classLoader = ClassLoader.getSystemClassLoader();
            }

            // 使用反射获取findLoadedClass方法
            java.lang.reflect.Method method = ClassLoader.class.getDeclaredMethod(
                    "findLoadedClass", String.class);
            method.setAccessible(true);

            // 检查类是否已加载
            Class<?> loadedClass = (Class<?>) method.invoke(classLoader,
                    "singleton.SingletonInClass$SingletonHolder");

            return loadedClass != null;
        } catch (Exception e) {
            return false;
        }
    }
}
