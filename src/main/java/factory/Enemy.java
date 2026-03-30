package factory;

public abstract class Enemy {
    protected String name;
    protected int health;

    public Enemy(String name, int health){
        this.name = name;
        this.health = health;
    }

    public abstract void attack();
}
