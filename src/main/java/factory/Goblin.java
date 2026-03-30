package factory;

public class Goblin extends Enemy{

    public Goblin(){
        super("哥布林", 50);
    }

    @Override
    public void attack() {
        System.out.println("哥布林挥舞小刀！");
    }
}
