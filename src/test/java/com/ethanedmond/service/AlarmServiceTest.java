package com.ethanedmond.service;

import com.ethanedmond.dao.AlarmDAO;
import com.ethanedmond.datastructures.DynamicArray;
import com.ethanedmond.model.Alarm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AlarmServiceTest {
    static AlarmDAO mockDao;
    static AlarmService alarmService;
    static DynamicArray<Alarm> alarms;

    @BeforeAll
    static void sEtUpMoCkS() throws SQLException {
        mockDao = Mockito.mock(AlarmDAO.class);
        alarmService = new AlarmService(mockDao);
        Mockito.when(mockDao.getAllAlarms()).thenReturn(alarms);
    }

    @Test
    void getAllAlarms() {
        Assertions.assertEquals(alarms, alarmService.getAllAlarms());
    }

    @Test
    void addAlarm() {
        // TODO reorganize the user input so you can actually test this
    }

    @Test
    void deleteAlarm() {
        // TODO reorganize the user input so you can actually test this
    }
}