package com.ethanedmond.model;

public class Alarm {
    public int id;
    public String sleep_end_time;
    public String sleep_end_date;
    public boolean has_fired;
    public Alarm(int id, String sleep_end_time, String sleep_end_date, boolean has_fired) {
        this.id = id;
        this.sleep_end_time = sleep_end_time;
        this.sleep_end_date = sleep_end_date;
        this.has_fired = has_fired;
    }

    @Override
    public String toString() {
        return "Alarm" +
                id +
                " set to ring at " + sleep_end_time +
                " " + sleep_end_date +
                ", has_fired=" + has_fired;
    }
}
