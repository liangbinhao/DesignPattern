package prototype;

import java.util.List;
import java.util.Map;

// 火焰法师原型
public class FireMage extends Monster {
    public FireMage() {
        super(
                "mage_01",
                "火焰法师",
                60,
                35,
                List.of("火球术", "灼烧", "烈焰风暴"),
                Map.of("resistance", "fire:50%", "mana", 100)
        );
    }
}
