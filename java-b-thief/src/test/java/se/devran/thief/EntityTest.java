package se.devran.thief;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class EntityTest {

    Resident resident = new Resident("Devran", 100, 25);
    Burglar burglar = new Burglar("Thief", 100, 25);

    @Test
    void punch() {
        resident.punch(burglar);
        assertEquals(75, burglar.getHealth());
    }

    @Test
    void takeHit() {
        resident.takeHit(25);
        assertEquals(75, resident.getHealth());
    }

    @Test
    void isConsciousTrue() {
        burglar.takeHit(20);
        assertTrue(burglar.isConscious());
    }

    @Test
    void isConsciousFalse() {
        burglar.takeHit(100);
        assertFalse(burglar.isConscious());
    }
}