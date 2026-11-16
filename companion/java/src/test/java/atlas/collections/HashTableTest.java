package atlas.collections;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HashTableTest {
    @Test
    void putsGetsUpdatesAndRemoves() {
        HashTable<String, Integer> table = new HashTable<>();
        assertNull(table.put("java", 1995));
        assertEquals(Integer.valueOf(1995), table.get("java"));
        assertEquals(Integer.valueOf(1995), table.put("java", 1996));
        assertEquals(Integer.valueOf(1996), table.get("java"));

        assertEquals(Integer.valueOf(1996), table.remove("java"));
        assertNull(table.get("java"));
        assertEquals(0, table.size());
    }

    @Test
    void resizesAndPreservesEveryEntry() {
        HashTable<Integer, Integer> table = new HashTable<>();
        Map<Integer, Integer> expected = new HashMap<>();
        Random random = new Random(11);

        for (int i = 0; i < 5_000; i++) {
            int key = random.nextInt(1_000);
            table.put(key, i);
            expected.put(key, i);
        }

        assertEquals(expected.size(), table.size());
        expected.forEach((key, value) -> assertEquals(value, table.get(key)));
        assertTrue(table.capacity() > 16);
    }

    @Test
    void handlesHeavyCollisions() {
        HashTable<Integer, Integer> table = new HashTable<>();
        for (int i = 0; i < 100; i++) table.put(i * 16, i);

        assertEquals(100, table.size());
        for (int i = 0; i < 100; i++) assertEquals(i, table.get(i * 16));
    }
}
