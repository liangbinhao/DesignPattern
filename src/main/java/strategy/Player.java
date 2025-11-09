package strategy;

import lombok.Getter;

/**
 * 实现玩家类
 */
@Getter
public class Player {
    private Position position;

    Player(Position position) {
        if (position == null) {
            position = new Position(50, 50);
        }
        this.position = position;
    }
}
