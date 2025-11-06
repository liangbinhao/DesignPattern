package singleton;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestSingletonLanHanDoubleCheck {

    @Test
    @DisplayName("基础双检锁单例测试")
    public void testBasicSingleton() {
        SingletonLanHanDoubleCheck instance1 = SingletonLanHanDoubleCheck.getInstance();
        SingletonLanHanDoubleCheck instance2 = SingletonLanHanDoubleCheck.getInstance();
        
        // 验证是否为同一实例
        assertSame(instance1, instance2, "两次获取的实例应相同");
        assertEquals(instance1.hashCode(), instance2.hashCode(), "哈希码应该相同");
    }
    
    @Test
    @DisplayName("多线程双检锁单例测试")
    public void testConcurrentSingleton() throws InterruptedException {
        int threadCount = 5000;
        Thread[] threads = new Thread[threadCount];
        SingletonLanHanDoubleCheck[] instances = new SingletonLanHanDoubleCheck[threadCount];
        
        // 创建多个线程同时获取单例实例
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                try {
                    Thread.sleep(new Random().nextInt(2));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                instances[index] = SingletonLanHanDoubleCheck.getInstance();
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
        SingletonLanHanDoubleCheck firstInstance = instances[0];
        assertNotNull(firstInstance, "第一个实例不应为null");

        int nullCnt=0;
        for(SingletonLanHanDoubleCheck instance : instances){
            if(instance==null){
                nullCnt++;
            }
        }
        System.out.println("获取到null的次数："+nullCnt);
        
        for (int i = 1; i < threadCount; i++) {
            assertSame(firstInstance, instances[i], 
                "所有线程获取的实例应该相同，但第" + i + "个实例不同");
        }
    }
}
