package strategy;

public class DefensiveBehavior implements AiBehavior{
    @Override
    public void act(Enemy enemy, Player player) {
        if(enemy.getHealth() < enemy.getMaxHealth()*0.3){
            System.out.println(enemy.getName() + " 生命值低，正在撤退并治疗！");
            enemy.moveAwayFrom(player.getPosition());
            enemy.heal(5);
        } else {
            System.out.println(enemy.getName() + " 谨慎观察，暂不进攻。");
        }
    }
}
