package factory;

public class ForestEnemyFactory implements EnemyThemeFactory{
    @Override
    public MeleeEnemy createMeleeEnemy() {
        return new ForestGoblin();
    }

    @Override
    public RangedEnemy createRangedEnemy() {
        return new ForestArcher();
    }

    @Override
    public BossEnemy createBossEnemy() {
        return new Treant();
    }
}
