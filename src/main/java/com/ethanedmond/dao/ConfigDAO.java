package com.ethanedmond.dao;

import com.ethanedmond.model.Config;
import com.ethanedmond.util.ConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfigDAO {
    public static Config getConfig() throws SQLException { // TODO Switch from using hard-coded prompts to db prompts, then test this
        PreparedStatement ps = ConnectionSingleton.getConn().prepareStatement("SELECT * FROM configs");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int suggested_sleep_length = rs.getInt("suggested_sleep_length");
        String sleep_start_prompt = rs.getString("sleep_start_prompt");
        String sleep_end_prompt = rs.getString("sleep_end_prompt");
        String can_remember_prompt = rs.getString("can_remember_prompt");
        String write_in_journal_prompt = rs.getString("write_in_journal_prompt");
        Config.setInstance(suggested_sleep_length, sleep_start_prompt, sleep_end_prompt, can_remember_prompt, write_in_journal_prompt);
        return Config.getInstance();
    }
}
