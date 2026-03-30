package Factory;

import factory.Archer;
import factory.ArcherFactory;
import factory.Enemy;
import factory.EnemyFactory;
import factory.Goblin;
import factory.GoblinFactory;
import factory.Ogre;
import factory.OgreFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEnemyFactoryMethod {

    @Test
    public void testGoblinFactoryCreateEnemy() {
        EnemyFactory factory = new GoblinFactory();
        Enemy enemy = factory.createEnemy();

        assertNotNull(enemy, "GoblinFactory 创建结果不应为空");
        assertInstanceOf(Goblin.class, enemy, "GoblinFactory 应创建 Goblin 实例");
    }

    @Test
    public void testArcherFactoryCreateEnemy() {
        EnemyFactory factory = new ArcherFactory();
        Enemy enemy = factory.createEnemy();

        assertNotNull(enemy, "ArcherFactory 创建结果不应为空");
        assertInstanceOf(Archer.class, enemy, "ArcherFactory 应创建 Archer 实例");
    }

    @Test
    public void testOgreFactoryCreateEnemy() {
        EnemyFactory factory = new OgreFactory();
        Enemy enemy = factory.createEnemy();

        assertNotNull(enemy, "OgreFactory 创建结果不应为空");
        assertInstanceOf(Ogre.class, enemy, "OgreFactory 应创建 Ogre 实例");
    }

    @Test
    public void testSpawnEnemyForAllFactories() {
        EnemyFactory[] factories = {new GoblinFactory(), new ArcherFactory(), new OgreFactory()};

        for (EnemyFactory factory : factories) {
            Enemy enemy = assertDoesNotThrow(factory::spawnEnemy, "spawnEnemy 不应抛出异常");
            assertNotNull(enemy, "spawnEnemy 创建结果不应为空");
        }
    }
}

