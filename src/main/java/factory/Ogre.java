package factory;

public class Ogre extends Enemy{

    public Ogre(){
        super("食人魔", 150);
    }

    @Override
    public void attack() {
        System.out.println("食人魔重拳出击！");
    }
}
