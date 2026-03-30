package Factory;

import factory.Archer;
import factory.Enemy;
import factory.Goblin;
import factory.Ogre;
import factory.SimpleEmemyFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSimpleEnemyFactory {
    
    @Test
    public void testCreateGoblin() {
        Enemy goblin = SimpleEmemyFactory.createEnemy("goblin");
        assertNotNull(goblin, "创建的哥布林对象不应为空");
        assertTrue(goblin instanceof Goblin, "创建的应该是哥布林实例");
        goblin.attack();
    }
    
    @Test
    public void testCreateArcher() {
        Enemy archer = SimpleEmemyFactory.createEnemy("archer");
        assertNotNull(archer, "创建的弓箭手对象不应为空");
        assertTrue(archer instanceof Archer, "创建的应该是弓箭手实例");
        archer.attack();
    }
    
    @Test
    public void testCreateOgre() {
        Enemy ogre = SimpleEmemyFactory.createEnemy("ogre");
        assertNotNull(ogre, "创建的食人魔对象不应为空");
        assertTrue(ogre instanceof Ogre, "创建的应该是食人魔实例");
        ogre.attack();
    }
    
    @Test
    public void testCreateEnemyWithUpperCase() {
        Enemy goblin = SimpleEmemyFactory.createEnemy("GOBLIN");
        assertNotNull(goblin, "大写字母创建的哥布林对象不应为空");
        assertTrue(goblin instanceof Goblin, "大写字母创建的应该是哥布林实例");
    }
    
    @Test
    public void testCreateEnemyWithMixedCase() {
        Enemy archer = SimpleEmemyFactory.createEnemy("ArChEr");
        assertNotNull(archer, "混合大小写创建的弓箭手对象不应为空");
        assertTrue(archer instanceof Archer, "混合大小写创建的应该是弓箭手实例");
    }
    
    @Test
    public void testCreateUnknownEnemy() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> SimpleEmemyFactory.createEnemy("dragon"),
            "创建未知敌人类型应该抛出异常"
        );
        assertTrue(exception.getMessage().contains("未知敌人类型"), "异常消息应包含'未知敌人类型'");
    }
    
    @Test
    public void testCreateEnemyWithNull() {
        assertThrows(
            NullPointerException.class,
            () -> SimpleEmemyFactory.createEnemy(null),
            "传入 null 应该抛出空指针异常"
        );
    }
}
