package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sounds {

    private static final String COMPLETE_SOUND = "./data/CashRegister1.wav";
    private static final String ERROR_SOUND = "./data/WINDOWS XP ERROR SOUND.wmv.wav";

    public void playCompleteSound() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(COMPLETE_SOUND));
            Clip sound = AudioSystem.getClip();
            sound.open(ais);
            sound.start();
            while (!sound.isRunning()) {
                Thread.sleep(10);
            }
            while (sound.isRunning()) {
                Thread.sleep(10);
            }
            sound.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playErrorSound() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(ERROR_SOUND));
            Clip sound = AudioSystem.getClip();
            sound.open(ais);
            sound.start();
            while (!sound.isRunning()) {
                Thread.sleep(10);
            }
            while (sound.isRunning()) {
                Thread.sleep(10);
            }
            sound.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
