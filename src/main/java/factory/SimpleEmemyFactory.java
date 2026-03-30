package factory;

public class SimpleEmemyFactory {
    public static Enemy createEnemy(String type){
        return switch (type.toLowerCase()){
            case "goblin"->new Goblin();
            case "archer"->new Archer();
            case "ogre"->new Ogre();
            default -> throw new IllegalArgumentException("未知敌人类型："+type);
        };
    }
}
