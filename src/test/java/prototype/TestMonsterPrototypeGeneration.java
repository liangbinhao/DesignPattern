package prototype;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMonsterPrototypeGeneration {

    @Test
    public void testCreateMonsterReturnsIndependentClones() {
        MonsterPrototypeRegistry registry = MonsterPrototypeRegistry.getInstance();

        Monster goblin1 = registry.createMonster("goblin");
        Monster goblin2 = registry.createMonster("goblin");

        assertNotSame(goblin1, goblin2, "每次创建都应返回新的怪物实例");
        assertEquals("绿皮哥布林", goblin1.getName(), "应基于哥布林原型创建");
        assertEquals("绿皮哥布林", goblin2.getName(), "应基于哥布林原型创建");
    }

    @Test
    public void testCreateUnknownMonsterThrowsException() {
        MonsterPrototypeRegistry registry = MonsterPrototypeRegistry.getInstance();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> registry.createMonster("dragon"),
                "创建未知怪物应抛出异常"
        );
        assertTrue(exception.getMessage().contains("未知的怪物类型"), "异常信息应包含未知类型提示");
    }

    @Test
    public void testEliteSkeletonLevelAndBuffConfiguration() {
        MonsterPrototypeRegistry registry = MonsterPrototypeRegistry.getInstance();

        Monster eliteSkeleton = registry.createMonster("skeleton");
        eliteSkeleton.setLevel(3);
        eliteSkeleton.addBuff("狂暴");

        assertEquals(172, eliteSkeleton.getBaseHp(), "3级骷髅战士 HP 计算应正确");
        assertEquals(33, eliteSkeleton.getBaseAttack(), "3级骷髅战士攻击计算应正确");
        assertEquals("狂暴", eliteSkeleton.getAttributes().get("buff"), "应正确添加 buff");
    }

    @Test
    public void testMutatingOneCloneDoesNotAffectAnotherClone() {
        MonsterPrototypeRegistry registry = MonsterPrototypeRegistry.getInstance();

        Monster mage1 = registry.createMonster("fire_mage");
        Monster mage2 = registry.createMonster("fire_mage");

        mage1.getSkills().add("陨石天降");
        mage1.getAttributes().put("mana", 999);

        assertTrue(mage1.getSkills().contains("陨石天降"), "修改后的实例应包含新增技能");
        assertFalse(mage2.getSkills().contains("陨石天降"), "另一个克隆实例不应被污染");
        assertEquals(999, mage1.getAttributes().get("mana"), "当前实例属性应被修改");
        assertEquals(100, mage2.getAttributes().get("mana"), "另一个克隆实例属性应保持原值");
    }
}


