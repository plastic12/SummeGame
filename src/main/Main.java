package main;

import java.io.FileReader;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
	//gui component
	public static SceneStatus sceneStatus=SceneStatus.map;
	public static GridPane grid=new GridPane();
	public static GridPane grid2=new GridPane();
	public static CharacterPane characterPane;


	public static Map map;
	public static IntegerProperty cameraX=new SimpleIntegerProperty(0) ;
	public static IntegerProperty cameraY=new SimpleIntegerProperty(0);
	public static Party party;
	//public static Party party;




	@Override
	public void start(Stage primaryStage) throws Exception {
		//input file
		JSONParser parser=new JSONParser();
		JSONObject fileData=(JSONObject) parser.parse(new FileReader("out.json"));
		//output log
		log=new PrintWriter("log.txt");
		log.append("start");
		log.flush();

		//load data
		//load static data
		Library.load();
		BGLayer.loadData(fileData);
		//load object data
		map=new Map(fileData);
		party=new Party(map.getSpawnX(),map.getSpawnY());
		map.addEntity(party);
		party.setLayer(map);
		cameraX.bind(party.xProperty().add(-5));
		cameraY.bind(party.yProperty().add(-5));

		cameraX.addListener((observable,oldValue,newValue)->{
			if(!oldValue.equals(newValue))
				updateView();
		});
		cameraY.addListener((observable,oldValue,newValue)->{
			if(!oldValue.equals(newValue))
				updateView();
		});

		//main code
		updateView();
		Group root=new Group();
		VBox mainFrame=new VBox();
		HBox optionFrame=new HBox();
		Button mapBtn=new Button("map");
		mapBtn.setOnAction(e->{
			sceneStatus=SceneStatus.map;
			updateScene();
		});
		Button characterBtn=new Button("character");
		characterBtn.setOnAction(e->{
			sceneStatus=SceneStatus.character;
			updateScene();
		});
		Button formationBtn=new Button("formation");
		formationBtn.setOnAction(e->{
			sceneStatus=SceneStatus.formation;
			updateScene();
		});
		Button optionBtn=new Button("option");
		optionBtn.setOnAction(e->{
			sceneStatus=SceneStatus.option;
			updateScene();
		});
		optionFrame.getChildren().addAll(mapBtn,characterBtn,formationBtn,optionBtn);
		StackPane sP=new StackPane();
		sP.setPrefWidth(500);
		sP.setPrefHeight(500);
		mainFrame.getChildren().addAll(sP,optionFrame);
		root.getChildren().add(mainFrame);

		grid=new GridPane();
		for(int i=0;i<screenSize;i++) {
			for(int j=0;j<screenSize;j++) {
				MapGrid temp=new MapGrid(i,j,view[i][j],Library.textures.get(0));
				grid.getChildren().add(temp);
			}
		}
		grid2=new GridPane();
		for(int i=0;i<screenSize;i++) {
			for(int j=0;j<screenSize;j++) {
				ObjectGrid temp=new ObjectGrid(i,j,view2[i][j],Library.textures.get(1));
				grid.getChildren().add(temp);
			}
		}
		characterPane=new CharacterPane(party);

		sP.getChildren().addAll(grid,grid2,characterPane);
		updateScene();

		Scene scene=new Scene(root);
		scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt)->{
			if(sceneStatus==SceneStatus.map)
			{
				switch(evt.getCode())
				{
				case UP:
					log.println("up");
					log.flush();
					party.moveUp();
					break;
				case DOWN:
					log.println("down");
					log.flush();
					party.moveDown();
					break;
				case RIGHT:
					log.println("right");
					log.flush();
					party.moveRight();
					break;
				case LEFT:
					log.println("left");
					log.flush();
					party.moveLeft();
					break;
				default:
					break;

				}
			}
		});



		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();

	}
	public static void updateScene() {
		switch(sceneStatus) {
		case map:
			grid.setVisible(true);
			grid2.setVisible(true);
			characterPane.setVisible(false);
			break;
		case character:
			grid.setVisible(false);
			grid2.setVisible(false);
			characterPane.setVisible(true);
			break;
		case formation:
			break;
		case option:
			break;
		default:
			break;
		}
	}


	public static void updateView() {
		int[][] tempBg=map.display(cameraX.get(), cameraY.get(), screenSize,0);
		int[][] tempObj=map.display(cameraX.get(), cameraY.get(), screenSize,1);
		for(int i=0;i<screenSize;i++) {
			for(int j=0;j<screenSize;j++) {
				view[i][j].set(tempBg[i][j]);
				view2[i][j].set(tempObj[i][j]);
			}
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}


}
