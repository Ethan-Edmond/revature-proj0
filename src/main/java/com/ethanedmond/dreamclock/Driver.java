package com.ethanedmond.dreamclock;

public class Driver {
    public static void main(String[] args) {
        switch (args[0]) {
            case "list": // TODO
                // list alarms from azure database
                break;
            case "read": // TODO
                // outputs journal from azure database
                break;
            case "add": // TODO
                // adds alarm to azure database and Schtask.exe
                // it should add an alarm to wake up and one to go to sleep
                // it should take the time to wake up and confirm the time to go to sleep, with the possibility to edit the time to go to sleep
                // this editing possibility may need to change a default value for how long you should sleep
                break;
            case "del": // TODO
                // deletes the alarm with given id from the azure database and Schtask.exe
                // should delete both the wake up and go to sleep alarms
                break;
            default:
                System.out.println("You can use dreamclock with list, read, add or del. Please provide one.");
        }
    }
}
