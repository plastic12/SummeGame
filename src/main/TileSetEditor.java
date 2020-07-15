package main;

import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TileSetEditor extends Application{
	
	public static int width;
	public static int height;
	public static IntegerProperty current;
	public static PrintWriter out;
	public static CheckBox box;

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		Library.load();
		out=new PrintWriter("map.json");
		JSONObject output=new JSONObject();
		JSONArray tileset=new JSONArray();
		output.put("tileset", tileset);
		Image image=Library.textures.get(0);
		width=(int)image.getWidth()/32;
		height=(int)image.getHeight()/32;
		for(int i=0;i<width*height;i++) {
			JSONObject temp=new JSONObject();
			temp.put("canPass",true);
			tileset.add(temp);
		}
		Group root=new Group();
		current=new SimpleIntegerProperty(0);
		VBox container=new VBox();
		root.getChildren().add(container);
		MapGrid grid=new MapGrid(0,0,current,image);
		box=new CheckBox();
		box.setSelected((Boolean)((JSONObject)tileset.get(current.get())).get("canPass"));
		box.selectedProperty().addListener((o,oldVal,newVal)->{
			((JSONObject)tileset.get(current.get())).put("canPass",newVal);
		});
		Button bt1=new Button("next");
		bt1.setOnAction((e)->{
			if(current.get()!=width*height)
			{
				current.set(current.get()+1);
				box.setSelected((Boolean)((JSONObject)tileset.get(current.get())).get("canPass"));
			}
		});
		Button bt2=new Button("previous");
		bt2.setOnAction((e)->{
			if(current.get()!=0)
			{
				current.set(current.get()-1);
				box.setSelected((Boolean)((JSONObject)tileset.get(current.get())).get("canPass"));
			}
		});
		Button bt3=new Button("save");
		bt3.setOnAction((e)->save(output));
		container.getChildren().addAll(grid,box,bt1,bt2,bt3);
		
		
		
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
	public static void save(JSONObject output) {
		out.println(output);
		out.flush();
	}
	

}
