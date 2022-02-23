package com.ethanedmond.dao;

import com.ethanedmond.datastructures.DynamicArray;
import com.ethanedmond.model.Entry;
import com.ethanedmond.util.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JournalDAO {
    public DynamicArray<Entry> getAllEntries() throws SQLException {
        DynamicArray<Entry> allEntries = new DynamicArray<>(Entry.class);
        PreparedStatement ps = ConnectionSingleton.getConn().prepareStatement(
                "SELECT j.alarm_id, a.sleep_end_date, j.content FROM journal j JOIN alarms a ON j.alarm_id = a.id ORDER BY j.written_on DESC"
        );
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("alarm_id");
            String sleep_end_date = rs.getString("sleep_end_date");
            String content = rs.getString("content");
            Entry nextEntry = new Entry(id, content, sleep_end_date);
            allEntries.append(nextEntry);
        }
        rs.close();
        ps.close();
        return allEntries;
    }

    public void addEntry(Entry added) throws SQLException {
        PreparedStatement ps = ConnectionSingleton.getConn().prepareStatement(
                "INSERT INTO journal (alarm_id, written_on, content) VALUES (?, SYSDATETIME(), ?)");
        ps.setInt(1, added.alarm_id);
        ps.setString(2, added.content);
        ps.execute();
        ps.close();
    }
}
