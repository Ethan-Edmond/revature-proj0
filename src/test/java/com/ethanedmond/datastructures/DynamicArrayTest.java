package com.ethanedmond.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {
    public DynamicArray<Integer> subject001;
    public DynamicArray<String> subject002;

    @BeforeEach
    void setup() {
        subject001 = new DynamicArray<>(Integer.class);
        subject002 = new DynamicArray<>(String.class);

        for (int i = 0; i < 5; i++) {
            subject001.append(i);
        };
    }

    @Test
    void getLength() {
        assertEquals(5, subject001.getLength());
    }

    @Test
    void getAt() {
        assertEquals(3, subject001.getAt(3));
    }

    @Test
    void setAt() {
        subject001.setAt(3, 5);
        assertEquals(5, subject001.getAt(3));
    }

    @Test
    void append() {
        subject001.append(5);
        assertEquals(6, subject001.getLength());
    }

}