package Controller.Tool;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundManager {
    public int volumeScale = 3;
    public float volume;
    Clip clip;
    URL[] soundUrl = new URL[10];
    FloatControl floatControl;

    public SoundManager() {
        soundUrl[0] = getClass().getResource("/Sound/music_background.wav");
        soundUrl[1] = getClass().getResource("/Sound/music_gameplay.wav");
        soundUrl[2] = getClass().getResource("/Sound/sound_effect_death.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void checkVolume() {
        // Volume is between -80f to 6f
        switch (volumeScale) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }
        floatControl.setValue(volume);
    }
}
