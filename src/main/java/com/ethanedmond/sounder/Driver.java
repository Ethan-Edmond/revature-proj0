package com.ethanedmond.sounder;

import com.ethanedmond.model.Entry;
import com.ethanedmond.service.AlarmService;
import com.ethanedmond.service.JournalService;
import com.ethanedmond.util.Dialog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Driver {
    public static void main(String[] args) {
        int alarm_id = Integer.parseInt(args[0]);
        String taskName = args[1];
        JournalService journalService = new JournalService();
        AlarmService alarmService = new AlarmService();
        Logger log = LogManager.getLogger(Driver.class);
        try {
            Runtime.getRuntime().exec("schtasks /DELETE /F /TN " + taskName);
            Clip clip = AudioSystem.getClip();
            URL url = Driver.class.getResource("/Alarm01.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            clip.open(audioStream);
            clip.loop(20);
            Dialog.runDialog("Are you awake? ");
            clip.stop();
            alarmService.fireAlarm(alarm_id, log);
            String content = Dialog.runDialog("What did you dream about? ");
            Entry added = new Entry(alarm_id, content);
            journalService.addEntry(added, log);
            log.info("Entry" + alarm_id + " successfully added!");
        } catch (IOException err) { // TODO log errors instead of printing them
            log.error("Error: cant run schtasks or cant get audioInputStream", err);
        } catch (UnsupportedAudioFileException err) {
            log.error("Error: audio file is invalid",err);
        } catch (LineUnavailableException err) {
            log.error("Error: unable to get clip from AudioSystem", err);
        }
    }
}
