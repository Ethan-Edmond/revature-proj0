package com.ethanedmond.dao;

import com.ethanedmond.model.Alarm;
import com.ethanedmond.datastructures.DynamicArray;
import com.ethanedmond.util.ConnectionSingleton;

import java.sql.*;

public class AlarmDAO {
    public DynamicArray<Alarm> getAllAlarms() throws SQLException {
        DynamicArray<Alarm> allAlarms = new DynamicArray<>(Alarm.class);
        PreparedStatement ps = ConnectionSingleton.getConn().prepareStatement("SELECT * FROM alarms");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String sleep_end_time = rs.getString("sleep_end_time");
            String sleep_end_date = rs.getString("sleep_end_date");
            boolean has_fired = rs.getBoolean("has_fired");
            Alarm nextAlarm = new Alarm(id, sleep_end_time, sleep_end_date, has_fired);
            allAlarms.append(nextAlarm);
        }
        ps.close();
        rs.close();
        return allAlarms;
    }

    public int addAlarm(String sleep_end_time, String sleep_end_date) throws SQLException {
        PreparedStatement ps = ConnectionSingleton.getConn().prepareStatement(
                "INSERT INTO alarms (sleep_end_time, sleep_end_date, has_fired) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        ps.setString(1, sleep_end_time);
        ps.setString(2, sleep_end_date);
        ps.setBoolean(3, false);
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int res = rs.getInt(1);
        ps.close();
        rs.close();
        return res;
    }

    public void deleteAlarm(int alarm_id) throws SQLException {
        PreparedStatement ps = ConnectionSingleton
                .getConn().prepareStatement("DELETE FROM alarms WHERE id = ?");
        ps.setInt(1, alarm_id);
        ps.execute();
        ps.close();
    }

    public void fireAlarm(int alarm_id) throws SQLException {
        PreparedStatement ps = ConnectionSingleton.getConn().prepareStatement("UPDATE alarms SET has_fired = 1 WHERE id = ?");
        ps.setInt(1, alarm_id);
        ps.execute();
        ps.close();
    }
}
