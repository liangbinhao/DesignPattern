package factory;

public class Archer extends Enemy{

    public Archer(){
        super("弓箭手", 70);
    }

    @Override
    public void attack() {
        System.out.println("弓箭手射出箭矢！");
    }
}
