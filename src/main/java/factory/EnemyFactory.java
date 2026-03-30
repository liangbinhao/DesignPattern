package factory;

public abstract class EnemyFactory {

    public abstract Enemy createEnemy();

    // 通用逻辑：生成敌人后播放音频、记录日子等
    public Enemy spawnEnemy() {
        Enemy enemy = createEnemy();
        System.out.println("spawned:" + enemy.name);
        playSpawnSound();
        return enemy;
    }

    private void playSpawnSound() {
        System.out.println("🔔敌人生成音效");
    }
}
