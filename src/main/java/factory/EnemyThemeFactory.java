package factory;

public interface EnemyThemeFactory {

	MeleeEnemy createMeleeEnemy();

	RangedEnemy createRangedEnemy();

	BossEnemy createBossEnemy();
}
