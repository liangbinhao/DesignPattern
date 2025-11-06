package singleton;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class TestSingletonEHan {

    @Test
    @DisplayName("基础单例测试")
    public void testBasicSingleton() {
        SingletonEHan instance1 = SingletonEHan.getInstance();
        SingletonEHan instance2 = SingletonEHan.getInstance();

        // 验证是否为同一实例
        assertSame(instance1, instance2, "两次获取的实例应相同");
        assertEquals(instance1.hashCode(), instance2.hashCode(), "哈希码应该相同");
    }

    @RepeatedTest(10)
    @DisplayName("重复单例测试")
    public void testRepeatedSingleton() {
        SingletonEHan instance1 = SingletonEHan.getInstance();
        SingletonEHan instance2 = SingletonEHan.getInstance();

        assertSame(instance1, instance2, "重复测试中实例应该相同");
    }

    @Test
    @DisplayName("多线程单例测试")
    public void testConcurrentSingleton() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        SingletonEHan[] instances = new SingletonEHan[threadCount];

        // 创建多个线程同时获取单例实例
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            executor.submit(() -> {
                instances[index] = SingletonEHan.getInstance();
            });
        }

        // 关闭线程池并等待所有任务完成
        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS), "线程池应在5秒内完成所有任务");

        // 验证所有线程获取到的是否为同一实例
        SingletonEHan firstInstance = instances[0];
        assertNotNull(firstInstance, "第一个实例不应为null");
        for (int i = 1; i < threadCount; i++) {
            assertSame(firstInstance, instances[i], "所有线程获取的实例应该相同，但第" + i + "个实例不同");
        }
    }
}
