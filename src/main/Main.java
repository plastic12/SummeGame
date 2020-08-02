package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
	private static SceneStatus sceneStatus=SceneStatus.map;
	public static GridPane grid=new GridPane();
	public static GridPane grid2=new GridPane();
	public static CharacterPane characterPane;
	public static FormationSelectPane formationPane;


	public static Map map;
	public static IntegerProperty cameraX=new SimpleIntegerProperty(0) ;
	public static IntegerProperty cameraY=new SimpleIntegerProperty(0);
	public static MainChar party;
	//public static Party party;




	@Override
	public void start(Stage primaryStage) throws Exception {
		//input file
		ClassLoader loader = Main.class.getClassLoader();
		JSONParser parser=new JSONParser();

		JSONObject fileData=(JSONObject) parser.parse(fileToString("data/out.json"));
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
		party=new MainChar(map.getSpawnX(),map.getSpawnY());
		party.addItem(Weapon.getBow());
		party.characters[0].equipWeapon(party.weapons.get(0));
		map.addEntity(party);
		party.setLayer(map);
		party.setFormation(0, 0, 0);
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
			changeScene(SceneStatus.map);
		});
		Button characterBtn=new Button("character");
		characterBtn.setOnAction(e->{
			changeScene(SceneStatus.character);
		});
		Button formationBtn=new Button("formation");
		formationBtn.setOnAction(e->{
			changeScene(SceneStatus.formation);
		});
		Button optionBtn=new Button("option");
		optionBtn.setOnAction(e->{
			changeScene(SceneStatus.option);
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
		formationPane=new FormationSelectPane(party);




		sP.getChildren().addAll(grid,grid2,characterPane,formationPane);
		grid.setVisible(true);
		grid2.setVisible(true);
		characterPane.setVisible(false);
		formationPane.setVisible(false);

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
	public static void changeScene(SceneStatus newVal) {
		if(sceneStatus==newVal)
			return;
		else{
			//close
			switch(sceneStatus) {
			case map:
				grid.setVisible(false);
				grid2.setVisible(false);
				break;
			case character:
				characterPane.reset();
				characterPane.setVisible(false);
				break;
			case formation:
				formationPane.reset();
				formationPane.setVisible(false);
				break;
			default:
				break;
			}
			//open
			switch(newVal) {
			case map:
				grid.setVisible(true);
				grid2.setVisible(true);
				break;
			case character:
				characterPane.setVisible(true);
				break;
			case formation:
				formationPane.setVisible(true);
				break;
			case option:
				break;
			default:
				break;
			}
		}
		sceneStatus=newVal;
	}
	public static String fileToString(String filename) throws IOException {
		InputStream is=((InputStream) ClassLoader.getSystemResource(filename).getContent());
		return new BufferedReader(new InputStreamReader(is))
				.lines().collect(Collectors.joining("\n"));
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
