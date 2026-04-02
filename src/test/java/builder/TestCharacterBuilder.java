package builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCharacterBuilder {

    @Test
    public void testBuildWithRequiredFieldsUsesDefaultValues() {
        Character character = Character.builder("亚瑟", "战士").build();
        String text = character.toString();

        assertTrue(text.contains("角色【亚瑟】"), "应包含角色姓名");
        assertTrue(text.contains("职业：战士"), "应包含职业");
        assertTrue(text.contains("性别：男 ｜ 发型：短发"), "应使用默认性别和发型");
        assertTrue(text.contains("装备：无 + 无"), "应使用默认装备");
        assertTrue(text.contains("技能：无"), "应使用默认技能");
        assertTrue(text.contains("出生点：(0, 0)"), "应使用默认出生点");
    }

    @Test
    public void testChainMethodsReturnSameBuilder() {
        Character.Builder builder = Character.builder("莉娜", "法师");

        assertSame(builder, builder.gender("女"), "gender 应返回同一个 Builder");
        assertSame(builder, builder.hairstyle("长发"), "hairstyle 应返回同一个 Builder");
        assertSame(builder, builder.weapon("法杖"), "weapon 应返回同一个 Builder");
        assertSame(builder, builder.armor("法袍"), "armor 应返回同一个 Builder");
        assertSame(builder, builder.skills("火球术", "冰盾"), "skills 应返回同一个 Builder");
        assertSame(builder, builder.spawnAt(10, 20), "spawnAt 应返回同一个 Builder");
    }

    @Test
    public void testBuildWithCustomValues() {
        Character character = Character.builder("艾拉", "游侠")
                .gender("女")
                .hairstyle("马尾")
                .weapon("长弓")
                .armor("轻甲")
                .skills("连射", "翻滚", "陷阱")
                .spawnAt(12, 34)
                .build();

        String text = character.toString();
        assertTrue(text.contains("角色【艾拉】"), "应包含自定义姓名");
        assertTrue(text.contains("职业：游侠"), "应包含自定义职业");
        assertTrue(text.contains("性别：女 ｜ 发型：马尾"), "应包含自定义外观");
        assertTrue(text.contains("装备：长弓 + 轻甲"), "应包含自定义装备");
        assertTrue(text.contains("技能：连射,翻滚,陷阱"), "应包含自定义技能");
        assertTrue(text.contains("出生点：(12, 34)"), "应包含自定义出生点");
    }

    @Test
    public void testBuildWithNullNameThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Character.builder(null, "战士"),
                "姓名为 null 应抛出非法参数异常"
        );
        assertTrue(exception.getMessage().contains("姓名不能为空"), "异常信息应提示姓名不能为空");
    }

    @Test
    public void testBuildWithBlankNameThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Character.builder("   ", "法师"),
                "姓名为空白应抛出非法参数异常"
        );
        assertTrue(exception.getMessage().contains("姓名不能为空"), "异常信息应提示姓名不能为空");
    }

    @Test
    public void testBuildWithInvalidProfessionThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Character.builder("罗恩", "刺客"),
                "非法职业应抛出非法参数异常"
        );
        assertTrue(exception.getMessage().contains("无效职业"), "异常信息应提示无效职业");
    }

    @Test
    public void testMageWithHeavySwordThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Character.builder("梅林", "法师").weapon("重剑").build(),
                "法师使用重剑应抛出非法参数异常"
        );
        assertTrue(exception.getMessage().contains("法师不能使用重剑"), "异常信息应提示法师不能使用重剑");
    }

    @Test
    public void testSkillsMoreThanThreeThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Character.builder("索林", "战士").skills("冲锋", "格挡", "怒吼", "旋风斩").build(),
                "技能超过三个应抛出非法参数异常"
        );
        assertTrue(exception.getMessage().contains("技能数量不能超过三个"), "异常信息应提示技能数量上限");
    }

    @Test
    public void testBuildWithThreeSkillsDoesNotThrow() {
        assertDoesNotThrow(
                () -> Character.builder("凯恩", "战士").skills("冲锋", "格挡", "怒吼").build(),
                "技能数量等于三个时应允许构建"
        );
    }

    @Test
    public void testCharacterToString(){
        Character character = Character.builder("艾拉", "游侠")
                .gender("女")
                .hairstyle("马尾")
                .weapon("长弓")
                .armor("轻甲")
                .skills("连射", "翻滚", "陷阱")
                .spawnAt(12, 34)
                .build();
        System.out.println(character);
    }
}

