package prototype;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 原型注册中心 -- 单例模式
public class MonsterPrototypeRegistry {
    private static final MonsterPrototypeRegistry INSTANCE = new MonsterPrototypeRegistry();
    private final Map<String, Monster> prototypes = new ConcurrentHashMap<>();

    private MonsterPrototypeRegistry() {
        // 初始化所有原型(模拟从配置文件加载)
        registerPrototype("goblin", new Goblin());
        registerPrototype("skeleton", new SkeletonWarrior());
        registerPrototype("fire_mage", new FireMage());
    }

    public static MonsterPrototypeRegistry getInstance() {
        return INSTANCE;
    }

    private void registerPrototype(String key, Monster prototype) {
        prototypes.put(key, prototype);
    }

    // 核心：通过克隆获取新实例
    public Monster createMonster(String key) {
        Monster prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("未知的怪物类型：" + key);
        }
        return prototype.clone();
    }
}
