package prototype;

import java.util.Arrays;
import java.util.Map;

// 哥布林原型
public class Goblin extends Monster {
    public Goblin() {
        super(
                "goblin_01",
                "绿皮哥布林",
                80,
                15,
                Arrays.asList("撕咬", "投掷石头"),
                Map.of("speed", 1.2f, "resistance", "physical:10%")
        );
    }
}
