package strategy;

import lombok.Getter;

/**
 * 实现敌人类，采用策略模式切换行为
 */
@Getter
public class Enemy {
    private String name;
    private double health;
    private double maxHealth;
    private double speed;
    private double baseSpeed = 2.0;
    private Position position;
    private AiBehavior behavior;

    public Enemy(String name, Position startPos) {
        this.name = name;
        this.position = startPos;
        this.maxHealth = 100;
        this.health = maxHealth;
        this.speed = baseSpeed;
        this.behavior = new NormalBehavior(); //默认行为
    }

    /**
     * 委托给具体策略执行行为
     */
    public void update(Player player) {
        behavior.act(this, player);
    }

    /**
     * 动态切换行为
     *
     * @param behavior 新行为
     */
    public void setBehavior(AiBehavior behavior) {
        this.behavior = behavior;
        System.out.println(this.name + " 的AI行为已切换");
    }

    /**
     * 模拟方法（实际游戏会有更复杂的实现）
     *
     * @param target 目标位置
     */
    public void moveTowards(Position target) {
        System.out.println(this.name + " 正在移动到" + target);
    }

    public void moveAwayFrom(Position target) {
        System.out.println(this.name + " 正在远离" + target);
    }

    public void attack(Player player) {
        System.out.println(this.name + " 对玩家造成伤害\uD83D\uDCA5！");
    }

    public void heal(int amount) {
        health = Math.min(health + amount, maxHealth);
        System.out.println(this.name + " 回复 " + amount + " 点生命值，当前生命：" + health);
    }

    // Getters and Setters
    public void setSpeed(double speed) {
        this.speed = speed;
        System.out.println(this.name + " 的移动速度已调整为 " + speed);
    }

    public void setHealth(double health) {
        this.health = health;
        System.out.println(this.name + " 的生命已调整为 " + health);
    }

    public double getDistanceTo(Player player) {
        Position playerPosition = player.getPosition();
        return Math.sqrt(Math.pow(playerPosition.getX() - position.getX(), 2) +
                Math.pow(playerPosition.getY() - position.getY(), 2));
    }

}
