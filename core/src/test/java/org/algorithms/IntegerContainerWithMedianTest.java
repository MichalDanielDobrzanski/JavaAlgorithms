package org.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertFalse;

class IntegerContainerWithMedianTest {

    @Test
    @DisplayName("Median on empty container is empty Optional")
    void median_empty() {
        var c = new IntegerContainerWithMedian();
        assertTrue(c.getMedian().isEmpty());
    }

    @Test
    @DisplayName("Median with one element equals that element")
    void median_single() {
        var c = new IntegerContainerWithMedian();
        assertEquals(1, c.add(7));
        assertEquals(Optional.of(7), c.getMedian());
    }

    @Test
    @DisplayName("Median with two elements returns lower median (n/2 - 1)")
    void median_twoElements_lowerMedian() {
        var c = new IntegerContainerWithMedian();
        c.add(10);
        c.add(20);
        // Sorted = [10, 20], n=2 -> median = index 0 -> 10
        assertEquals(Optional.of(10), c.getMedian());
    }

    @Test
    @DisplayName("Median with odd number of elements")
    void median_oddCount() {
        var c = new IntegerContainerWithMedian();
        c.add(3);
        c.add(1);
        c.add(2); // sorted -> [1,2,3], n=3 -> idx 1 -> 2
        assertEquals(Optional.of(2), c.getMedian());
    }

    @Test
    @DisplayName("Median with even number of elements uses lower middle")
    void median_evenCount_lowerMiddle() {
        var c = new IntegerContainerWithMedian();
        c.add(4);
        c.add(1);
        c.add(3);
        c.add(2); // sorted -> [1,2,3,4], n=4 -> idx 1 -> 2
        assertEquals(Optional.of(2), c.getMedian());
    }

    @Test
    @DisplayName("Add returns current size and keeps list sorted for median")
    void add_returnsSize_and_medianProgression() {
        var c = new IntegerContainerWithMedian();

        assertEquals(1, c.add(5));   // [5]              median 5
        assertEquals(Optional.of(5), c.getMedian());

        assertEquals(2, c.add(1));   // [1,5]            median 1 (lower middle)
        assertEquals(Optional.of(1), c.getMedian());

        assertEquals(3, c.add(10));  // [1,5,10]         median 5
        assertEquals(Optional.of(5), c.getMedian());

        assertEquals(4, c.add(7));   // [1,5,7,10]       median 5 (lower middle)
        assertEquals(Optional.of(5), c.getMedian());

        assertEquals(5, c.add(6));   // [1,5,6,7,10]     median 6
        assertEquals(Optional.of(6), c.getMedian());
    }

    @Test
    @DisplayName("Delete returns true only when element existed")
    void delete_existing_vs_missing() {
        var c = new IntegerContainerWithMedian();
        c.add(2);
        c.add(4);
        c.add(6);

        assertTrue(c.delete(4));   // remove existing
        assertFalse(c.delete(4));  // already removed
        assertFalse(c.delete(100)); // never existed
    }

    @Test
    @DisplayName("Median updates correctly after deletions")
    void median_afterDeletion() {
        var c = new IntegerContainerWithMedian();
        c.add(1);
        c.add(3);
        c.add(5);
        c.add(7); // [1,3,5,7], median lower middle -> 3
        assertEquals(Optional.of(3), c.getMedian());

        assertTrue(c.delete(3)); // [1,5,7], median -> 5
        assertEquals(Optional.of(5), c.getMedian());

        assertTrue(c.delete(5)); // [1,7], median lower middle -> 1
        assertEquals(Optional.of(1), c.getMedian());

        assertTrue(c.delete(1)); // [7], median -> 7
        assertEquals(Optional.of(7), c.getMedian());

        assertTrue(c.delete(7)); // []
        assertTrue(c.getMedian().isEmpty());
    }

    @Test
    @DisplayName("Handles duplicates: insert, median, delete one occurrence")
    void duplicates_behavior() {
        var c = new IntegerContainerWithMedian();
        c.add(5);
        c.add(5);
        c.add(5);
        // [5,5,5] -> median 5
        assertEquals(Optional.of(5), c.getMedian());

        assertTrue(c.delete(5)); // [5,5] -> median lower middle -> 5
        assertEquals(Optional.of(5), c.getMedian());

        assertTrue(c.delete(5)); // [5]
        assertEquals(Optional.of(5), c.getMedian());

        assertTrue(c.delete(5)); // []
        assertTrue(c.getMedian().isEmpty());
    }

    @Test
    @DisplayName("Works with negative numbers and mixed values")
    void negatives_and_mixed() {
        var c = new IntegerContainerWithMedian();
        c.add(-10);
        c.add(0);
        c.add(-5);
        c.add(5);
        // Sorted -> [-10, -5, 0, 5], n=4 -> idx 1 -> -5
        assertEquals(Optional.of(-5), c.getMedian());

        c.add(-7); // [-10, -7, -5, 0, 5], n=5 -> idx 2 -> -5
        assertEquals(Optional.of(-5), c.getMedian());
    }

}