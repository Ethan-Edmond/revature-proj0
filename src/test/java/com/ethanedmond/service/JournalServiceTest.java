package com.ethanedmond.service;

import com.ethanedmond.dao.JournalDAO;
import com.ethanedmond.datastructures.DynamicArray;
import com.ethanedmond.model.Entry;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JournalServiceTest {
    static DynamicArray<Entry> entries;
    static JournalDAO mockDao;
    static Entry entry;
    static Connection mockConn;
    static Logger mockLog;
    static JournalService journalService;
    static boolean loggerCalled;
    static SQLException sqlException;

    @BeforeAll
    static void sEtUpMoCkS() throws SQLException {
        mockDao = Mockito.mock(JournalDAO.class);
        Mockito.when(mockDao.getAllEntries()).thenReturn(entries);
        entry = new Entry(1, "asdf");
        sqlException = new SQLException();
        Mockito.doThrow(sqlException).when(mockDao).addEntry(entry);
        mockLog = Mockito.mock(Logger.class);
        Mockito.doAnswer(invocationOnMock -> {
            loggerCalled = true;
            return null;
        }).when(mockLog).error("Failed to add entry", sqlException);
        mockConn = Mockito.mock(Connection.class);
        journalService = new JournalService(mockDao);
    }

    @BeforeEach
    void setup() {
        loggerCalled = false;
    }

    @Test
    void getAllEntries() {
        Assertions.assertEquals(entries, journalService.getAllEntries());
    }

    @Test
    void addEntryLoggerCalled() {
        journalService.addEntry(entry, mockLog);
        Assertions.assertTrue(loggerCalled);
    }
}