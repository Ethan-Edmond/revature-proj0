package com.ethanedmond.model;

public class Config {
    public int suggested_sleep_length;
    public String sleep_start_prompt;
    public String sleep_end_prompt;
    public String can_remember_prompt;
    public String write_in_journal_prompt;
    private static Config instance;

    private Config(int suggested_sleep_length, String sleep_start_prompt, String sleep_end_prompt, String can_remember_prompt, String write_in_journal_prompt) {
        this.suggested_sleep_length = suggested_sleep_length;
        this.sleep_start_prompt = sleep_start_prompt;
        this.sleep_end_prompt = sleep_end_prompt;
        this.can_remember_prompt = can_remember_prompt;
        this.write_in_journal_prompt = write_in_journal_prompt;
    }

    public static void setInstance(int suggested_sleep_length, String sleep_start_prompt, String sleep_end_prompt, String can_remember_prompt, String write_in_journal_prompt) {
        instance = new Config(suggested_sleep_length, sleep_start_prompt, sleep_end_prompt, can_remember_prompt, write_in_journal_prompt);
    }

    public static Config getInstance() {
        return instance;
    }
}
