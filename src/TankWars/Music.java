package TankWars;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Alvin Nguyen
 */
public class Music {

    public static void music() {

	/**
	 * Source code provided on StackOverflow: https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
	 *
	 */
	try {
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/TankWars/resources/STAGE ZERO.wav").getAbsoluteFile());
	    Clip clip = AudioSystem.getClip();
	    clip.open(audioInputStream);
	    clip.start();
	    clip.loop(5);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

    }

}
