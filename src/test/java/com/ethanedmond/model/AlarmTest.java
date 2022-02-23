package com.ethanedmond.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlarmTest {
    public Alarm subject001;

    @Test
    void testToString() {
        subject001 = new Alarm(1, "sleep_end_time", "sleep_end_date", false);
        System.out.println(subject001.toString());
        assertEquals("Alarm1: sleep_end=sleep_end_time sleep_end_date, has_fired=false", subject001.toString());
    }
}