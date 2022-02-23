package com.ethanedmond.sounder;

import com.ethanedmond.model.Entry;
import com.ethanedmond.service.AlarmService;
import com.ethanedmond.service.JournalService;
import com.ethanedmond.util.Dialog;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Driver {
    public static void main(String[] args) { // TODO schtasks /DELETE here instead of doing it in sounder.bat
        int alarm_id = Integer.parseInt(args[0]);
        JournalService journalService = new JournalService();
        AlarmService alarmService = new AlarmService();
        try {
            Clip clip = AudioSystem.getClip();
            URL url = Driver.class.getResource("/Alarm01.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            clip.open(audioStream);
            clip.loop(20);
            Dialog.runDialog("Are you awake? ");
            clip.stop();
            alarmService.fireAlarm(alarm_id);
            String content = Dialog.runDialog("What did you dream about? ");
            Entry added = new Entry(alarm_id, content);
            journalService.addEntry(added);
            System.out.println("Entry successfully added to journal"); // TODO change this to a log this message instead of printing it
        } catch (IOException err) { // TODO log errors instead of printing them
            err.printStackTrace();
        } catch (UnsupportedAudioFileException err) {
            err.printStackTrace();
        } catch (LineUnavailableException err) {
            err.printStackTrace();
        }
    }
}
