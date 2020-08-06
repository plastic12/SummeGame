package main;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Library {
	public static ArrayList<Image> textures;
	
	
	
	
	public static void load() {
		textures=new ArrayList<Image>();
		textures.add(new Image("texture/tileset.png"));
		textures.add(new Image("texture/tileset2.png"));
		textures.add(new Image("texture/character.png"));
		textures.add(new Image("texture/item.png"));
	}

}
