package factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestAbstractEnemyFactory {

    @Test
    public void testForestEnemyFactoryCreatesCorrectProducts() {
        EnemyThemeFactory factory = new ForestEnemyFactory();

        MeleeEnemy meleeEnemy = factory.createMeleeEnemy();
        RangedEnemy rangedEnemy = factory.createRangedEnemy();
        BossEnemy bossEnemy = factory.createBossEnemy();

        assertNotNull(meleeEnemy, "森林近战敌人不应为空");
        assertNotNull(rangedEnemy, "森林远程敌人不应为空");
        assertNotNull(bossEnemy, "森林Boss敌人不应为空");
        assertInstanceOf(ForestGoblin.class, meleeEnemy, "森林近战应为 ForestGoblin");
        assertInstanceOf(ForestArcher.class, rangedEnemy, "森林远程应为 ForestArcher");
        assertInstanceOf(Treant.class, bossEnemy, "森林Boss应为 Treant");
    }

    @Test
    public void testDungeonEnemyFactoryCreatesCorrectProducts() {
        EnemyThemeFactory factory = new DungeonEnemyFactory();

        MeleeEnemy meleeEnemy = factory.createMeleeEnemy();
        RangedEnemy rangedEnemy = factory.createRangedEnemy();
        BossEnemy bossEnemy = factory.createBossEnemy();

        assertNotNull(meleeEnemy, "地牢近战敌人不应为空");
        assertNotNull(rangedEnemy, "地牢远程敌人不应为空");
        assertNotNull(bossEnemy, "地牢Boss敌人不应为空");
        assertInstanceOf(DungeonOgre.class, meleeEnemy, "地牢近战应为 DungeonOgre");
        assertInstanceOf(Skeleton.class, rangedEnemy, "地牢远程应为 Skeleton");
        assertInstanceOf(ShadowMage.class, bossEnemy, "地牢Boss应为 ShadowMage");
    }

    @Test
    public void testGameLevelStartWaveWithForestFactory() {
        GameLevel gameLevel = new GameLevel(new ForestEnemyFactory());
        assertDoesNotThrow(gameLevel::startWave, "森林主题 startWave 不应抛出异常");
    }

    @Test
    public void testGameLevelStartWaveWithDungeonFactory() {
        GameLevel gameLevel = new GameLevel(new DungeonEnemyFactory());
        assertDoesNotThrow(gameLevel::startWave, "地牢主题 startWave 不应抛出异常");
    }
}


