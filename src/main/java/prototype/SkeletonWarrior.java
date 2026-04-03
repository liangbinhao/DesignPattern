package prototype;

import java.util.List;
import java.util.Map;

// 骷髅战士原型
public class SkeletonWarrior extends Monster {
    public SkeletonWarrior() {
        super(
                "skeleton_01",
                "骷髅战士",
                120,
                25,
                List.of("骨剑斩", "盾击"),
                Map.of("resistance", "physical:20%", "weakness", "holy")
        );
    }
}
