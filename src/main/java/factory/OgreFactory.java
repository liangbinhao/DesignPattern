package factory;

public class OgreFactory extends EnemyFactory{
    @Override
    public Enemy createEnemy() {
        return new Ogre();
    }
}
