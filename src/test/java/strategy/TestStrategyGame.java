package strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestStrategyGame {

    private Player player;
    private Enemy enemy;

    @BeforeEach
    void setUp() {
        player = new Player(new Position(100, 100));
        enemy = new Enemy("测试敌人", new Position(0, 0));
    }

    @Test
    void testEnemyInitialization() {
        assertEquals("测试敌人", enemy.getName());
        assertEquals(100.0, enemy.getHealth());
        assertEquals(100.0, enemy.getMaxHealth());
        assertEquals(2.0, enemy.getBaseSpeed());
        assertEquals(2.0, enemy.getSpeed());
        assertNotNull(enemy.getPosition());
        assertTrue(enemy.getBehavior() instanceof NormalBehavior);
    }

    @Test
    void testNormalBehavior() {
        // 默认行为应该是NormalBehavior
        assertTrue(enemy.getBehavior() instanceof NormalBehavior);

        // 执行行为更新
        assertDoesNotThrow(() -> enemy.update(player));
    }

    @Test
    void testBerserkBehavior() {
        // 切换到狂暴行为
        enemy.setBehavior(new BerserkBehavior());
        assertTrue(enemy.getBehavior() instanceof BerserkBehavior);

        // 保存原始速度
        double originalSpeed = enemy.getSpeed();

        // 执行行为更新
        assertDoesNotThrow(() -> enemy.update(player));

        // 验证速度是否加倍
        assertEquals(originalSpeed * 2, enemy.getSpeed());
    }

    @Test
    void testDefensiveBehaviorWithHighHealth() {
        enemy.setBehavior(new DefensiveBehavior());
        assertTrue(enemy.getBehavior() instanceof DefensiveBehavior);

        // 高生命值情况下执行行为
        assertDoesNotThrow(() -> enemy.update(player));
    }

    @Test
    void testDefensiveBehaviorWithLowHealth() {
        enemy.setBehavior(new DefensiveBehavior());
        enemy.setHealth(20); // 设置低生命值(低于30%)

        // 执行行为更新
        assertDoesNotThrow(() -> enemy.update(player));
    }

    @Test
    void testRangeBehavior() {
        enemy.setBehavior(new RangeBehavior());
        assertTrue(enemy.getBehavior() instanceof RangeBehavior);

        // 执行行为更新
        assertDoesNotThrow(() -> enemy.update(player));
    }

    @Test
    void testBehaviorSwitching() {
        // 初始为NormalBehavior
        assertTrue(enemy.getBehavior() instanceof NormalBehavior);

        // 切换到BerserkBehavior
        enemy.setBehavior(new BerserkBehavior());
        assertTrue(enemy.getBehavior() instanceof BerserkBehavior);

        // 再切换到DefensiveBehavior
        enemy.setBehavior(new DefensiveBehavior());
        assertTrue(enemy.getBehavior() instanceof DefensiveBehavior);
    }

    @Test
    void testDistanceCalculation() {
        // 设置特定位置来验证距离计算
        player = new Player(new Position(3, 4));
        enemy = new Enemy("距离测试敌人", new Position(0, 0));

        // 计算距离(0,0)到(3,4)应该是5
        assertEquals(5.0, enemy.getDistanceTo(player), 0.01);
    }

    @Test
    void testHealMethod() {
        double initialHealth = enemy.getHealth();
        enemy.heal(10);
        assertEquals(Math.min(initialHealth + 10, enemy.getMaxHealth()), enemy.getHealth());
    }
}
