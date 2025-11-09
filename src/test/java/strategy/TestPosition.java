package strategy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Position类测试")
public class TestPosition {

    @Test
    @DisplayName("测试Position构造函数和Getter方法")
    public void testPositionConstructorAndGetters() {
        // Given
        double x = 3.5;
        double y = 4.2;

        // When
        Position position = new Position(x, y);

        // Then
        assertEquals(x, position.getX(), "X坐标应该相同");
        assertEquals(y, position.getY(), "Y坐标应该相同");
    }

    @Test
    @DisplayName("测试Position的toString方法")
    public void testToString() {
        // Given
        Position position1 = new Position(3.5, 4.2);
        Position position2 = new Position(-1.0, 2.7);
        Position position3 = new Position(0, 0);

        // When
        String result1 = position1.toString();
        String result2 = position2.toString();
        String result3 = position3.toString();

        // Then
        assertEquals("(3.5,4.2)", result1);
        assertEquals("(-1.0,2.7)", result2);
        assertEquals("(0.0,0.0)", result3);
    }

    @Test
    @DisplayName("测试负坐标值")
    public void testNegativeCoordinates() {
        // Given
        Position position = new Position(-5.5, -3.2);

        // When
        String result = position.toString();

        // Then
        assertEquals("(-5.5,-3.2)", result);
        assertEquals(-5.5, position.getX(), "X坐标应该相同");
        assertEquals(-3.2, position.getY(), "Y坐标应该相同");
    }

    @Test
    @DisplayName("测试整数坐标值")
    public void testIntegerCoordinates() {
        // Given
        Position position = new Position(10, 20);

        // When
        String result = position.toString();

        // Then
        assertEquals("(10.0,20.0)", result);
        assertEquals(10, position.getX(), "X坐标应该相同");
        assertEquals(20, position.getY(), "Y坐标应该相同");
    }
}
