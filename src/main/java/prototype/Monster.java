package prototype;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 抽象原型 -- 声明克隆接口
@Setter
@Getter
public abstract class Monster implements Cloneable {
    protected String id;
    protected String name;
    protected int baseHp;
    protected int baseAttack;
    protected List<String> skills;
    protected Map<String, Object> attributes;

    public Monster(String id, String name, int hp, int attack,
                   List<String> skills, Map<String, Object> attributes) {
        this.id = id;
        this.name = name;
        this.baseHp = hp;
        this.baseAttack = attack;
        this.skills = new ArrayList<>(skills);  // 防止外部修改
        this.attributes = new HashMap<>(attributes);
    }

    // 深拷贝方法
    @Override
    public Monster clone() {
        try {
            Monster cloned = (Monster) super.clone();
            // 深拷贝引用类型字段
            cloned.skills = new ArrayList<>(this.skills);
            cloned.attributes = new HashMap<>(this.attributes);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("克隆失败", e);
        }
    }

    // 差异化配置方法
    public void setLevel(int level) {
        this.baseHp = (int) (baseHp * Math.pow(1.2, level - 1));
        this.baseAttack = (int) (baseAttack * Math.pow(1.15, level - 1));
    }

    public void addBuff(String buffName) {
        attributes.put("buff", buffName);
    }
}
