/* Programmer: Daniel Lopez
   IDE: Eclipse
   Date: 2/23/2017
   Description: Simple JavaFX Program That Uses The Oracle's Java Sound API and other neat features.
*/

package application;

import javax.sound.sampled.*;
import java.io.*;
 

public class JavaSoundRecorder {
	

	private String wavePath;
 
   
    private AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    private int count = 0;
    
    private TargetDataLine line;
 

    private AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        return format;
    }
    
    public void setFilePath(String wavePath)
    {
    	this.wavePath = wavePath + "/RecordingAudio";
    }
 
    
   private File createWaveFile()
   {
	   
	   return new File(wavePath + count + ".wav");
   }
   
   public void start() {
	
        try {
        	count++;
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
 
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();  
 
            System.out.println("Start capturing...");
 
            AudioInputStream ais = new AudioInputStream(line);
 
            System.out.println("Start recording...");
 
            AudioSystem.write(ais, fileType, createWaveFile());
 
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
 
  
   public void finish() {
        line.stop();
        line.close();
        System.out.println("Finished");
    }
    public JavaSoundRecorder()
    {
    	 
    	System.out.println("Calling JavaSoundRecorder Constructor");
        
    }
    
    
}