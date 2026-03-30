package factory;

public class ForestArcher implements RangedEnemy{
    @Override
    public void rangedAttack() {
        System.out.println("🌲弓箭手（森林）射击！");
    }
}
