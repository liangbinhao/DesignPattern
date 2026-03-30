package factory;

public class GoblinFactory extends EnemyFactory{

    @Override
    public Enemy createEnemy() {
        return new Goblin();
    }
}
