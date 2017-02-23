/* Programmer: Daniel Lopez
   IDE: Eclipse
   Date: 2/23/2017
   Description: Simple JavaFX Program That Uses The Oracle's Java Sound API and other neat features.
*/

package application;

import java.awt.EventQueue;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;


public class Main extends Application implements Initializable {

	@FXML
	private Button startRecordingButton, directoryButton, stopRecordingButton;
	
	private AnchorPane page;
	Scene scene;
	
	private JavaSoundRecorder javaRecorder = new JavaSoundRecorder();
	private DirectoryChooser directoryChooser = new DirectoryChooser();
	private String fileName;

	private Stage primaryStage;

	public static void main(String[] args) {
		launch(args);
	}

	
	 
	
	public void findDirectoryPath() 
	{
		
		try
		{
			
			fileName = directoryChooser.showDialog(primaryStage).getPath().replace("\\", "/");
			javaRecorder.setFilePath(fileName);
			
			//javaRecorder.insertFilePath(fileName);
			
			System.out.println("FILE NAME : " + fileName);
			
			startRecordingButton.setDisable(false);
			startRecordingButton.setOpacity(1);
			
		}
		catch(NullPointerException e)
		{
			System.out.println("Selection Has Been Interrupted");
		
		}
		
		
	}
	
	private void recordWave()
	{
		//if(fileName!=null){
		
			
	
		
		startRecordingButton.setOpacity(.5);
		startRecordingButton.setDisable(true);
		
		stopRecordingButton.setOpacity(1);
		stopRecordingButton.setDisable(false);
		
		//javaRecorder.threadAndStuff();
		
		
		EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
                javaRecorder.start();
             
            }
        });
 
       
       
        
		
		//}
		
		
	}
	private void stopRecording()
	{
		
		stopRecordingButton.setOpacity(.5);
		stopRecordingButton.setDisable(true);
		
		startRecordingButton.setOpacity(1);
		startRecordingButton.setDisable(false);
		
		
		javaRecorder.finish();
		
	}
	
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		// TODO Auto-generated method stub

		stopRecordingButton.setDisable(true);
		stopRecordingButton.setOpacity(.5);
		
		startRecordingButton.setDisable(true);
		startRecordingButton.setOpacity(.5);
		
		stopRecordingButton.setOnAction(e -> stopRecording());
		startRecordingButton.setOnAction(e -> recordWave());
		directoryButton.setOnAction(e -> findDirectoryPath());

		
		//System.out.println(directoryChooser.showDialog(primaryStage).toPath());
	

	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {

			page = (AnchorPane) FXMLLoader.load(Main.class.getResource("audioFXML.fxml"));

			scene = new Scene(page);
			
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("FXML is Simple");
			primaryStage.show();
			this.primaryStage = primaryStage;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
