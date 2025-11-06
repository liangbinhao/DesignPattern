package singleton;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class TestSingletonLanHan {
    @Test
    @DisplayName("基础懒汉式单例测试")
    public void testBasicSingleton() {
        SingletonLanHan instance1 = SingletonLanHan.getInstance();
        SingletonLanHan instance2 = SingletonLanHan.getInstance();

        // 验证是否为同一实例
        assertSame(instance1, instance2, "两次获取的实例应相同");
        assertEquals(instance1.hashCode(), instance2.hashCode(), "哈希码应该相同");
    }

    @Test
    @DisplayName("多线程懒汉式单例测试")
    public void testConcurrentSingleton() throws InterruptedException {
        int threadCount = 100;
        Thread[] threads = new Thread[threadCount];
        SingletonLanHan[] instances = new SingletonLanHan[threadCount];

        // 创建多个线程同时获取单例实例
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                instances[index] = SingletonLanHan.getInstance();
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
        SingletonLanHan firstInstance = instances[0];
        assertNotNull(firstInstance, "第一个实例不应为null");

        for (int i = 1; i < threadCount; i++) {
            assertSame(firstInstance, instances[i],
                    "所有线程获取的实例应该相同，但第" + i + "个实例不同");
        }
    }
}
