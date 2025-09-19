package org.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LRUCacheTest {

    @Test
    @DisplayName("get on empty cache returns -1")
    void getOnEmpty() {
        LRUCache cache = new LRUCache(2);
        assertEquals(-1, cache.get(42));
    }

    @Test
    @DisplayName("basic put/get works")
    void basicPutGet() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        cache.put(2, 20);
        assertAll(
                () -> assertEquals(10, cache.get(1)),
                () -> assertEquals(20, cache.get(2))
        );
    }

    @Test
    @DisplayName("updating an existing key changes value and moves it to MRU")
    void updateExistingMovesToMRU() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);   // LRU: [1]
        cache.put(2, 2);   // LRU: [1,2]
        cache.put(1, 100); // update + move to MRU: order becomes [2,1]

        // Now inserting a third should evict key 2 (the LRU)
        cache.put(3, 3);
        assertAll(
                () -> assertEquals(-1, cache.get(2), "key 2 should be evicted"),
                () -> assertEquals(100, cache.get(1), "key 1 value should be updated"),
                () -> assertEquals(3, cache.get(3))
        );
    }

    @Test
    @DisplayName("get refreshes recency (classic 3-capacity scenario)")
    void getRefreshesRecency() {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 1);   // order: [1]
        cache.put(2, 2);   // order: [1,2]
        cache.put(3, 3);   // order: [1,2,3]  (3 is MRU)

        // Access 1 so it becomes MRU: order -> [2,3,1]
        assertEquals(1, cache.get(1));

        // Insert 4; should evict 2 (the LRU)
        cache.put(4, 4);

        assertAll(
                () -> assertEquals(-1, cache.get(2), "key 2 should be evicted"),
                () -> assertEquals(1, cache.get(1)),
                () -> assertEquals(3, cache.get(3)),
                () -> assertEquals(4, cache.get(4))
        );
    }

    @Test
    @DisplayName("LRU eviction with capacity 2 and intervening get")
    void evictionAfterGet() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1); // [1]
        cache.put(2, 2); // [1,2]
        assertEquals(1, cache.get(1)); // refresh -> [2,1]
        cache.put(3, 3); // evict 2 -> [1,3]
        assertAll(
                () -> assertEquals(-1, cache.get(2)),
                () -> assertEquals(1, cache.get(1)),
                () -> assertEquals(3, cache.get(3))
        );
    }

    @Test
    @DisplayName("capacity 1 keeps only the most recent")
    void capacityOne() {
        LRUCache cache = new LRUCache(1);
        cache.put(1, 10);
        assertEquals(10, cache.get(1));
        cache.put(2, 20); // evicts 1
        assertAll(
                () -> assertEquals(-1, cache.get(1)),
                () -> assertEquals(20, cache.get(2))
        );

        // updating existing should still work
        cache.put(2, 200);
        assertEquals(200, cache.get(2));
        cache.put(3, 30); // evicts 2
        assertAll(
                () -> assertEquals(-1, cache.get(2)),
                () -> assertEquals(30, cache.get(3))
        );
    }

    @Test
    @DisplayName("capacity 0 stores nothing")
    void capacityZero() {
        LRUCache cache = new LRUCache(0);
        cache.put(1, 1);
        cache.put(2, 2);
        assertAll(
                () -> assertEquals(-1, cache.get(1)),
                () -> assertEquals(-1, cache.get(2))
        );
    }

    @Test
    @DisplayName("get miss does not break subsequent operations")
    void getMissThenContinue() {
        LRUCache cache = new LRUCache(2);
        assertEquals(-1, cache.get(999)); // miss
        cache.put(1, 1);
        cache.put(2, 2);
        // Now insert third; should evict LRU=1
        cache.put(3, 3);
        assertAll(
                () -> assertEquals(-1, cache.get(1)),
                () -> assertEquals(2, cache.get(2)),
                () -> assertEquals(3, cache.get(3))
        );
    }

    @Test
    @DisplayName("repeated gets keep key fresh and protect it from eviction")
    void repeatedGetsKeepFresh() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        // keep 1 fresh via gets
        assertEquals(1, cache.get(1));
        assertEquals(1, cache.get(1));
        // insert 3 should evict 2 (older)
        cache.put(3, 3);

        assertAll(
                () -> assertEquals(1, cache.get(1)),
                () -> assertEquals(-1, cache.get(2)),
                () -> assertEquals(3, cache.get(3))
        );
    }

    @Test
    @DisplayName("updating value keeps same key, moves it to MRU; eviction hits the other key")
    void updateValueOnly() {
        LRUCache cache = new LRUCache(2);

        cache.put(7, 70);   // [7]
        cache.put(8, 80);   // [7,8] (no eviction at cap)

        // Prove no eviction at fill step and refresh 7 to MRU
        assertEquals(70, cache.get(7)); // order -> [8,7]

        // Update existing key; should keep key 7 and keep it MRU
        cache.put(7, 700);             // order -> [8,7]

        // Next insert must evict LRU = 8
        cache.put(9, 90);              // evicts 8 -> [7,9]

        assertAll(
                () -> assertEquals(700, cache.get(7)),
                () -> assertEquals(-1, cache.get(8)),
                () -> assertEquals(90, cache.get(9))
        );
    }
}
