package main;

import java.io.PrintWriter;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{
	public static final int screenSize=11;
	public static PrintWriter log;
	public static IntegerProperty[][] view;
	public static Map map;
	public static int cameraX=0;
	public static int cameraY=0;
	public static Party party;

	@Override
	public void start(Stage primaryStage) throws Exception {
    	log=new PrintWriter("log.txt");
    	log.append("start");
    	log.flush();
    	party=new Party();
    	map=new Map(50,50);
		Group root=new Group();
		GridPane grid=new GridPane();
		
		
		
		
		root.getChildren().add(grid);
		Scene scene=new Scene(root);
		scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt)->{
			switch(evt.getCode())
			{
			case UP:
				log.println("up");
				log.flush();
				setCamera(cameraX,cameraY-1);
				break;
			case DOWN:
				log.println("down");
				log.flush();
				setCamera(cameraX,cameraY+1);
				break;
			case RIGHT:
				log.println("right");
				log.flush();
				setCamera(cameraX+1,cameraY);
				break;
			case LEFT:
				log.println("left");
				log.flush();
				setCamera(cameraX-1,cameraY);
				break;
			default:
				break;
			
			}
		});
		
		
		
		
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		
	}
	
	public static void main(String args) {
		Application.launch(args);
	}
	

}
