package com.ethanedmond.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryTest {
    public Entry subject001;

    @Test
    public void toStringTest() {
        subject001 = new Entry(1, "I dreamt that I saw a bear");
        assertEquals("Entry1:\n    I dreamt that I saw a bear", subject001.toString());
    }

}