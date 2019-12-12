package helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoonTest {

    @Test
    void calculateGravity() {
    }

    @Test
    void getVelocityChange() {
        int ganymede = 3;
        int callisto = 5;
        int ganymedeChange = Moon.getVelocityChange(ganymede, callisto);
        int callistorChange = Moon.getVelocityChange(callisto, ganymede);

        assertEquals(1, ganymedeChange);
        assertEquals(-1, callistorChange);
    }
}