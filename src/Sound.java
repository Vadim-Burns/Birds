

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineListener;

public class Sound {


	public static synchronized void playSound(final String fileName) {
		  new Thread(new Runnable() {

		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        File f = new File(fileName);
		        System.out.println(f.getAbsolutePath());
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		          f);
		       
		        clip.open(inputStream);
		        clip.start(); 
		       
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
	
}
