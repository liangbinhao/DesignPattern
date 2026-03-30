package factory;

public class GameLevel {

    private EnemyThemeFactory factory;

    public GameLevel(EnemyThemeFactory factory) {
        this.factory = factory;
    }

    public void startWave() {
        MeleeEnemy melee = factory.createMeleeEnemy();
        RangedEnemy ranged = factory.createRangedEnemy();
        BossEnemy boss = factory.createBossEnemy();

        melee.meleeAttack();
        ranged.rangedAttack();
        boss.ultimate();
    }
}
