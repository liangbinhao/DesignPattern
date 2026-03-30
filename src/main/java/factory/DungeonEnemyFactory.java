package factory;

public class DungeonEnemyFactory implements EnemyThemeFactory{
    @Override
    public MeleeEnemy createMeleeEnemy() {
        return new DungeonOgre();
    }

    @Override
    public RangedEnemy createRangedEnemy() {
        return new Skeleton();
    }

    @Override
    public BossEnemy createBossEnemy() {
        return new ShadowMage();
    }
}
