package com.ethanedmond.service;

import com.ethanedmond.dao.JournalDAO;
import com.ethanedmond.datastructures.DynamicArray;
import com.ethanedmond.model.Entry;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class JournalService {
    JournalDAO dao;
    public JournalService() {
        this.dao = new JournalDAO();
    }

    public JournalService(JournalDAO dao) {
        this.dao = dao;
    }

    public DynamicArray<Entry> getAllEntries() {
        try {
            return this.dao.getAllEntries();
        } catch (SQLException e) {
            System.out.println("Failed to retrieve entries from journal.");
            return null;
        }
    }

    public boolean addEntry(Entry added, Logger log) {
        try {
            this.dao.addEntry(added);
            return true;
        } catch (SQLException e) {
            log.error("Failed to add entry", e);
            return false;
        }
    }
}
