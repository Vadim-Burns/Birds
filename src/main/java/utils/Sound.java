package utils;

import config.ConfigVars;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
	private static synchronized void playSound(final String fileName) {
		if (ConfigVars.isSoundEnabled) {
			new Thread(() -> {
				try {
					Clip clip = AudioSystem.getClip();
					File f = new File(
							Sound.class.getClassLoader().getResource(fileName).getPath()
					);
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);

					clip.open(inputStream);
					clip.start();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
	}

	public static void playJumpSound() {
		playSound("jump.wav");
	}

	public static void playBackgroundMusic() {
		playSound("funny_birds.wav");
	}
}
