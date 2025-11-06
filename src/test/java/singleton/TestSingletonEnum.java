package singleton;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestSingletonEnum {

    @Test
    @DisplayName("基础枚举单例测试")
    public void testBasicSingleton() {
        SingletonEnum instance1 = SingletonEnum.instance;
        SingletonEnum instance2 = SingletonEnum.instance;
        
        // 验证是否为同一实例
        assertSame(instance1, instance2, "两次获取的枚举实例应相同");
        assertEquals(instance1, instance2, "枚举实例应该相等");
        assertEquals(instance1.hashCode(), instance2.hashCode(), "哈希码应该相同");
    }
    
    @Test
    @DisplayName("多线程枚举单例测试")
    public void testConcurrentSingleton() throws InterruptedException {
        int threadCount = 100;
        Thread[] threads = new Thread[threadCount];
        SingletonEnum[] instances = new SingletonEnum[threadCount];
        
        // 创建多个线程同时获取枚举单例实例
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                instances[index] = SingletonEnum.instance;
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
        SingletonEnum firstInstance = instances[0];
        assertNotNull(firstInstance, "第一个实例不应为null");
        
        for (int i = 1; i < threadCount; i++) {
            assertSame(firstInstance, instances[i], 
                "所有线程获取的枚举实例应该相同，但第" + i + "个实例不同");
        }
    }
    
    @Test
    @DisplayName("枚举单例防反射测试")
    public void testReflectionResistance() {
        // 尝试通过反射获取枚举实例
        try {
            SingletonEnum reflectedInstance = SingletonEnum.valueOf("instance");
            assertSame(SingletonEnum.instance, reflectedInstance,
                "通过反射获取的实例应该与直接访问的实例相同");
        } catch (Exception e) {
            fail("枚举单例应该能够抵抗反射攻击", e);
        }
    }
}
