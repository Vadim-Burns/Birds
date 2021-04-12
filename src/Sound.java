import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
	private static boolean soundEnabled = false;

	private static synchronized void playSound(final String fileName) {
		if (soundEnabled) {
			new Thread(() -> {
				try {
					Clip clip = AudioSystem.getClip();
					File f = new File(fileName);
					System.out.println(f.getAbsolutePath());
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);

					clip.open(inputStream);
					clip.start();

				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}).start();
		}
	}

	public static void jump() {
		playSound("jump.wav");
	}

	public static void backgroundMusic() {
		playSound("funny_birds.wav");
	}
}
