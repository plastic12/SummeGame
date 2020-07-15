package main;

import org.json.simple.JSONObject;

public class Tile {
	public boolean canPass;
	public Tile(boolean canPass) {
		this.canPass=canPass;
	}
	public Tile(JSONObject input) {
		canPass=(Boolean)input.get("canPass");
	}
	
}
