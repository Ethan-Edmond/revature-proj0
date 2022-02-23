package com.ethanedmond.model;

public class Entry {
    public int alarm_id;
    public String content;
    public String sleep_end_date;

    public Entry(int alarm_id, String content) {
        this.alarm_id= alarm_id;
        this.content = content;
    }

    public Entry(int alarm_id, String content, String sleep_end_date) {
        this(alarm_id,content);
        this.sleep_end_date = sleep_end_date;
    }

    @Override
    public String toString() {
        if (this.sleep_end_date != null) {
            return "Entry" + this.alarm_id + " on date " + this.sleep_end_date + ":\n    " + this.content;
        } else {
            return "Entry" + this.alarm_id + ":\n    " + this.content;
        }
    }
}
