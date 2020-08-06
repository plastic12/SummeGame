package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
	public static PrintWriter log;
	//gui component
	private static SceneStatus sceneStatus;
	public static MapPanel mapPane;
	public static CharacterPane characterPane;
	public static FormationSelectPane formationPane;
	public static HBox optionFrame;
	public static BattlePanel battlePane;

	private static ArrayList<Map> maps;
	public static int mapIndex;
	public static MainChar party;





	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		//init data
		maps=new ArrayList<Map>();
		mapIndex=0;
		//input file
		JSONParser parser=new JSONParser();
		JSONObject fileData=(JSONObject) parser.parse(fileToString("data/mapData.json"));
		//output log
		log=new PrintWriter("log.txt");
		log.append("start");
		log.flush();

		//load data
		Library.load();
		BGLayer.loadData(fileData);
		//load object data
		JSONArray mapsData=(JSONArray) fileData.get("maps");
		Iterator<JSONObject> iter=mapsData.iterator();
		while(iter.hasNext()) {
			maps.add(new Map(iter.next()));
		}
		Position spawn=getMap().getLocation("start");
		party=new MainChar(spawn.x,spawn.y);
		party.addItem(Weapon.getBow());
		((Character)party.characters[0]).equipWeapon(party.weapons.get(0));
		//temp add entity

		getMap().addEntity(party);

		party.setLayer(getMap());
		party.setFormation(0, 0, 0);
		party.setFormation(0, 1,1);

		//main code
		Group root=new Group();
		VBox mainFrame=new VBox();
		optionFrame=new HBox();
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
		mapPane=new MapPanel();
		characterPane=new CharacterPane(party);
		formationPane=new FormationSelectPane(party);
		battlePane=new BattlePanel();




		sP.getChildren().addAll(mapPane,characterPane,formationPane,battlePane);

		Scene scene=new Scene(root);
		scene.addEventFilter(KeyEvent.KEY_PRESSED, evt->{
			if(evt.getCode()==KeyCode.ESCAPE)
				mapPane.requestFocus();
		});
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		initScene();
	}
	public static void changeMap(int index,String location) {
		if(mapIndex==index)
		{
			Position pos=getMap().getLocation(location);
			party.move(pos.x,pos.y);
			return;
		}
		else {
			Position pos=maps.get(index).getLocation(location);
			party.move(pos.x, pos.y, maps.get(index));
			mapIndex=index;
			mapPane.updateView();
		}
			
	}
	
	
	public static Map getMap() {
		return maps.get(mapIndex);
	}
	public static void battle(Party enemy) {
		changeScene(SceneStatus.battle);
		setUpBattle(enemy);
	}
	public static void setUpBattle(Party enemy) {
		battlePane.setup(party,enemy);
	}
	public static void initScene() {
		sceneStatus=SceneStatus.map;
		mapPane.setVisible(true);
		mapPane.updateView();
		characterPane.setVisible(false);
		formationPane.setVisible(false);
		battlePane.setVisible(false);
		mapPane.requestFocus();
	}
	public static void changeScene(SceneStatus newVal) {
		if(sceneStatus==newVal)
			return;
		else{
			//close
			switch(sceneStatus) {
			case map:
				mapPane.setVisible(false);
				break;
			case character:
				characterPane.reset();
				characterPane.setVisible(false);
				break;
			case formation:
				formationPane.setVisible(false);
				break;
			case battle:
				battlePane.setVisible(false);
				optionFrame.setVisible(true);
				break;
			default:
				break;
			}
			//open
			switch(newVal) {
			case map:
				mapPane.updateView();
				mapPane.setVisible(true);
				mapPane.requestFocus();
				break;
			case character:
				characterPane.setVisible(true);
				break;
			case formation:
				formationPane.setVisible(true);
				break;
			case option:
				break;
			case battle:
				battlePane.setVisible(true);
				optionFrame.setVisible(false);
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


	public static void main(String[] args) {
		Application.launch(args);
	}


}
