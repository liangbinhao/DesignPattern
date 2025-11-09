package strategy;

public class RangeBehavior implements AiBehavior {

    private static final double SAFE_DISTANCE = 10.0;

    @Override
    public void act(Enemy enemy, Player player) {
        double distance = enemy.getDistanceTo(player);
        if (distance > SAFE_DISTANCE) {
            System.out.println(enemy.getName() + " 靠近玩家以进入射程");
            enemy.moveTowards(player.getPosition());
        } else if (distance < SAFE_DISTANCE * 0.8) {
            System.out.println(enemy.getName() + " 后退以保持安全距离！");
            enemy.moveAwayFrom(player.getPosition());
        } else {
            System.out.println(enemy.getName() + " 使用弓箭攻击玩家！");
            enemy.attack(player);
        }
    }
}
