package builder;

public class Character {
    private final String name;
    private final String profession;
    private final String gender;
    private final String hairstyle;
    private final String weapon;
    private final String armor;
    private final String[] skills;
    private final int spawnX;
    private final int spawnY;

    private Character(Builder builder) {
        this.name = builder.name;
        this.profession = builder.profession;
        this.gender = builder.gender;
        this.hairstyle = builder.hairstyle;
        this.weapon = builder.weapon;
        this.armor = builder.armor;
        this.skills = builder.skills;
        this.spawnX = builder.spawnX;
        this.spawnY = builder.spawnY;
    }

    public static Builder builder(String name, String profession) {
        return new Builder(name, profession);
    }

    @Override
    public String toString() {
        return String.format(
                "角色【%s】\n" +
                        "  职业：%s\n" +
                        "  性别：%s ｜ 发型：%s\n" +
                        "  装备：%s + %s\n" +
                        "  技能：%s\n" +
                        "  出生点：(%d, %d)",
                name, profession, gender, hairstyle,
                weapon != null ? weapon : "无", armor != null ? armor : "无",
                skills != null ? String.join(",", skills) : "无",
                spawnX, spawnY
        );
    }

    public static class Builder {
        // 必填参数
        private final String name;
        private final String profession;

        // 可选参数(带默认值)
        private String gender = "男";
        private String hairstyle = "短发";
        private String weapon;
        private String armor;
        private String[] skills;
        private int spawnX = 0;
        private int spawnY = 0;

        private Builder(String name, String profession) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("姓名不能为空");
            }
            if (!isValidProfession(profession)) {
                throw new IllegalArgumentException("无效职业：" + profession);
            }

            this.name = name;
            this.profession = profession;
        }

        private boolean isValidProfession(String profession) {
            return "战士".equals(profession) || "法师".equals(profession) || "游侠".equals(profession);
        }

        // 链式调用方法(返回this)
        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder hairstyle(String hairstyle) {
            this.hairstyle = hairstyle;
            return this;
        }

        public Builder weapon(String weapon) {
            this.weapon = weapon;
            return this;
        }

        public Builder armor(String armor) {
            this.armor = armor;
            return this;
        }

        public Builder skills(String... skills) {
            this.skills = skills;
            return this;
        }

        public Builder spawnAt(int x, int y) {
            spawnX = x;
            spawnY = y;
            return this;
        }

        // 构建最终对象
        public Character build() {
            // 可在此处添加复杂校验逻辑(如法师不能拿重剑)
            validate();
            return new Character(this);
        }

        private void validate() {
            if ("法师".equals(profession) && "重剑".equals(weapon)) {
                throw new IllegalArgumentException("法师不能使用重剑");
            }
            if (skills != null && skills.length > 3) {
                throw new IllegalArgumentException("技能数量不能超过三个");
            }
        }
    }
}
