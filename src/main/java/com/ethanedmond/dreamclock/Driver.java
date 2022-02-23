package com.ethanedmond.dreamclock;

import com.ethanedmond.service.AlarmService;
import com.ethanedmond.service.JournalService;
import com.ethanedmond.util.Dialog;

import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        AlarmService alarmService = new AlarmService();
        JournalService journalService = new JournalService();
        if (args.length == 0) {
            System.out.println("You can use dreamclock with list, read, add or del. Please provide one.");
        } else {
            switch (args[0]) {
                case "list":
                    System.out.println(alarmService.getAllAlarms());
                    break;
                case "read": // TODO write some tests for this stuff
                    System.out.println(journalService.getAllEntries());
                    break;
                case "add":
                    alarmService.addAlarm();
                    break;
                case "delete":
                    alarmService.deleteAlarm();
                    break;
                default:
                    System.out.println("You can use dreamclock with list, read, add or del. Please provide one.");
            }
        }
    }
}
