package com.ethanedmond.service;

import com.ethanedmond.dao.AlarmDAO;
import com.ethanedmond.datastructures.DynamicArray;
import com.ethanedmond.model.Alarm;
import com.ethanedmond.util.Dialog;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class AlarmService {
    AlarmDAO dao;
    public AlarmService() {
        this.dao = new AlarmDAO();
    }

    public AlarmService(AlarmDAO dao) {
        this.dao = dao;
    }

    public DynamicArray<Alarm> getAllAlarms() {  // TODO maybe prettify the output later
        try {
            return this.dao.getAllAlarms();
        } catch (SQLException e) {
            System.out.println("Failed to retrieve alarms");
            return null;
        }
    }

    public void addAlarm() {  // TODO get prompts from db and add start alarm functionality
        String sleep_end_time = Dialog.runDialog("Please enter a time to wake up in 24 hour HH:MM format: ");
        String sleep_end_date = Dialog.runDialog("Please enter a date to set the alarm in MM/DD/YYYY format: ");
        try {
            int id = this.dao.addAlarm(sleep_end_time, sleep_end_date);
            Alarm added = new Alarm(id, sleep_end_time, sleep_end_date, false);
            String currDir = System.getProperty("user.dir");
            Runtime.getRuntime().exec( // I am sorry about the nastiness here, might fix later
                    "schtasks /CREATE /TN DreamClockSounder"
                            + added.id
                            + " /SC ONCE /ST \""
                            + added.sleep_end_time
                            + "\" /sd \""
                            + added.sleep_end_date
                            + "\" /tr \""
                            + currDir
                            + "\\src\\main\\resources\\sounder.bat "
                            + added.id + " "
                            + currDir
                            + "\""
            ); // TODO figure out how to get the program to recognize when this command fails
            System.out.println("Successfully added alarm!");
        } catch (SQLException e) {
            System.out.println("Failed to add alarm");
        } catch (IOException err) {
            System.out.println("Failed to add alarm to task scheduler");
        }
    }

    public void deleteAlarm() { // TODO make this only work on unfired alarms
        try {
            int id = Integer.parseInt(Dialog.runDialog("Please input the id of the alarm you wish to delete: "));
            this.dao.deleteAlarm(id);
            Runtime.getRuntime().exec("schtasks /DELETE /F /TN DreamClockSounder" + id);
            System.out.println("Alarm successfully deleted");
        } catch (NumberFormatException e) {
            System.out.println("The inputted id is not parse-able to an integer");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to delete alarm");
            e.printStackTrace();
        } catch (IOException err) {
            System.out.println("Failed to delete task");
            err.printStackTrace();
        }
    }

    public void fireAlarm(int id, Logger log) {
        try {
            this.dao.fireAlarm(id);
        } catch (SQLException e) {
            log.error("Failed to fire alarm", e);
        }
    }
}
