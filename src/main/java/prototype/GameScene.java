package prototype;

public class GameScene {
    public static void main(String[] args) {
        MonsterPrototypeRegistry registry = MonsterPrototypeRegistry.getInstance();

        // 场景1: 普通地下城 - 生成10只哥布林
        System.out.println("=== 普通地下城 ===");
        for (int i = 0; i < 3; i++) {
            Monster goblin = registry.createMonster("goblin");
            goblin.setName("哥布林伺候-" + i);
            System.out.printf("生成 %s [HP:%d, 技能:%s]%n",
                    goblin.getName(), goblin.getBaseHp(), goblin.getSkills());
        }

        // 场景2: 精英关卡 - 生成强化骷髅战士
        System.out.println("\n=== 精英关卡 ===");
        Monster eliteSkeleton = registry.createMonster("skeleton");
        eliteSkeleton.setLevel(3); // 设置为3级精英
        eliteSkeleton.addBuff("狂暴");
        eliteSkeleton.setName("骸骨将军");
        System.out.printf("BOSS %s [Hp:%d, BUFF:%s]%n",
                eliteSkeleton.getName(),
                eliteSkeleton.getBaseHp(),
                eliteSkeleton.getAttributes().get("buff"));

        // 场景3: 法师塔 - 生成带特殊技能的火焰法师
        System.out.println("\n=== 法师塔 ===");
        Monster bossMage = registry.createMonster("fire_mage");
        bossMage.setLevel(5); // 设置为5级Boss
        // 动态添加新技能(深拷贝保证不影响原型)
        bossMage.getSkills().add("陨石天降");
        bossMage.setName("炎魔领主");
        System.out.printf("最终BOSS %s [技能:%s]%n",
                bossMage.getName(), bossMage.getSkills());
    }
}
