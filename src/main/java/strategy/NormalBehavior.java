package strategy;

public class NormalBehavior implements AiBehavior{
    @Override
    public void act(Enemy enemy, Player player) {
        System.out.println(enemy.getName() + " 正在向玩家直线移动...");
        // 实际游戏中可能是更新坐标、播放动画等
        enemy.moveTowards(player.getPosition());
    }
}
