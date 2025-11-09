package strategy;

public class BerserkBehavior implements AiBehavior{
    @Override
    public void act(Enemy enemy, Player player) {
        System.out.println(enemy.getName() + " 进入狂暴状态！全速冲锋！");
        enemy.setSpeed(enemy.getBaseSpeed() * 2);
        enemy.moveTowards(player.getPosition());
        // 可能附加特效或音效
    }
}
