package strategy;

import lombok.Data;

/**
 * 实现位置类，存放enemy和player的二维坐标
 */
@Data
public class Position {

    private double x, y;

    Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
