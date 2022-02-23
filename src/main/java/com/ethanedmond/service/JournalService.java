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

    public DynamicArray<Entry> getAllEntries() {
        try {
            return this.dao.getAllEntries();
        } catch (SQLException e) {
            System.out.println("Failed to retrieve entries from journal.");
            return null;
        }
    }

    public void addEntry(Entry added, Logger log) {
        try {
            this.dao.addEntry(added);
        } catch (SQLException e) {
            log.error("Failed to add entry", e);
        }
    }
}
