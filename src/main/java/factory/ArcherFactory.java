package factory;

public class ArcherFactory extends EnemyFactory{
    @Override
    public Enemy createEnemy() {
        return new Archer();
    }
}
