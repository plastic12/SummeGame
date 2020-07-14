package main;

import java.io.PrintWriter;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
	public static final int screenSize=11;
	public static PrintWriter log;
	public static IntegerProperty[][] view=new IntegerProperty[screenSize][screenSize];
	public static IntegerProperty[][] view2=new IntegerProperty[screenSize][screenSize];
	static {
		for(int i=0;i<screenSize;i++) {
			for(int j=0;j<screenSize;j++) {
				view[i][j]=new SimpleIntegerProperty(-1);
				view2[i][j]=new SimpleIntegerProperty(-1);
			}
		}
	}
	public static Map map;
	public static int cameraX=-5;
	public static int cameraY=-5;
	public static Party party;
	
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
    	log=new PrintWriter("log.txt");
    	log.append("start");
    	log.flush();
    	Library.load();
    	party=new Party();
    	map=new Map(50,50);
    	//test set
    	map.set(0, 0, 0, 1);
    	updateView();
		Group root=new Group();
		StackPane sP=new StackPane();
		root.getChildren().add(sP);
		
		GridPane grid=new GridPane();
		for(int i=0;i<screenSize;i++) {
			for(int j=0;j<screenSize;j++) {
				MapGrid temp=new MapGrid(i,j,view[i][j],Library.textures.get(0));
				grid.getChildren().add(temp);
			}
		}
		GridPane grid2=new GridPane();
		for(int i=0;i<screenSize;i++) {
			for(int j=0;j<screenSize;j++) {
				ObjectGrid temp=new ObjectGrid(i,j,view2[i][j],Library.textures.get(1));
				grid.getChildren().add(temp);
			}
		}
		sP.getChildren().addAll(grid,grid2);
		
		Scene scene=new Scene(root);
		scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt)->{
			switch(evt.getCode())
			{
			case UP:
				log.println("up");
				log.flush();
				moveCamera(cameraX,cameraY-1);
				break;
			case DOWN:
				log.println("down");
				log.flush();
				moveCamera(cameraX,cameraY+1);
				break;
			case RIGHT:
				log.println("right");
				log.flush();
				moveCamera(cameraX+1,cameraY);
				break;
			case LEFT:
				log.println("left");
				log.flush();
				moveCamera(cameraX-1,cameraY);
				break;
			default:
				break;
			
			}
		});
		
		
		
		
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		
	}
	public static void updateView() {
		int[][] tempBg=map.display(cameraX, cameraY, screenSize,0);
		int[][] tempObj=map.display(cameraX, cameraY, screenSize,1);
		for(int i=0;i<screenSize;i++) {
			for(int j=0;j<screenSize;j++) {
				view[i][j].set(tempBg[i][j]);
				view2[i][j].set(tempObj[i][j]);
			}
		}
	}
	public static void moveCamera(int x,int y) {
		cameraX=x;
		cameraY=y;
		updateView();
	}
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	

}
