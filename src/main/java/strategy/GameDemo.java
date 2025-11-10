package strategy;

public class GameDemo {
    public static void main(String[] args) {
        Player player = new Player(new Position(100, 100));
        Enemy enemy = new Enemy("兽人战士", new Position(0, 0));

        System.out.println("==========游戏开始==========");

        // 第1帧 默认行为（普通）
        enemy.update(player);

        // 第2帧 切换为远程行为
        enemy.setBehavior(new RangeBehavior());
        enemy.update(player);

        // 第3帧 受伤后切换为防御行为
        enemy.setBehavior(new DefensiveBehavior());
        enemy.update(player);

        // 第4帧 血量极低，触发狂暴
        enemy.setBehavior(new BerserkBehavior());
        enemy.update(player);

    }
}
