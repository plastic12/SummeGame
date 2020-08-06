package main;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Party implements ObjTile{
	protected Map map;
	protected IntegerProperty xPro;
	protected IntegerProperty yPro;
	public Entity[] characters=new Entity[5];
	public Entity[][] formation=new Entity[3][3];
	public static final int MONSTERS=0;
	public static final int CHAR=1;
	
	
	public Party( int x, int y) {
		xPro=new SimpleIntegerProperty(x);
		yPro=new SimpleIntegerProperty(y);
		/*
		for(int i=0;i<5;i++) {
			characters[0]=new Character();
		}*/
	}
	@SuppressWarnings("unchecked")
	public Party(JSONObject input) {
		xPro=new SimpleIntegerProperty(((Long)input.get("x")).intValue());
		yPro=new SimpleIntegerProperty(((Long)input.get("y")).intValue());
		JSONArray charactersJSON=(JSONArray)input.get("characters");
		Iterator<JSONObject> iter=charactersJSON.iterator();
		for(int i=0;iter.hasNext();i++) {
			JSONObject json=iter.next();
			characters[i]=Entity.getEntity(json);
		}
		JSONArray formationJSON=(JSONArray)input.get("formation");
		Iterator<JSONArray> iter2=formationJSON.iterator();
		while(iter2.hasNext()) {
			JSONArray temp=iter2.next();
			int x=((Long)temp.get(0)).intValue();
			int y=((Long)temp.get(1)).intValue();
			int index=((Long)temp.get(2)).intValue();
			setFormation(x,y,index);
		}
	}
	
	public void setLayer(Map map) {
		this.map=map;
	}
	public Entity getFormation(int x,int y) {
		if(x<0||x>=3||y<0||y>=3)
			return null;
		return formation[x][y];
	}
	public boolean setFormation(int x,int y,int index) {
		if(index<0||index>=5)
			return false;
		if(x<0||x>=3||y<0||y>=3)
			return false;
		Entity select=characters[index];
		if(select!=null) {
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if(select.equals(formation[i][j]))
					{
						if(x==i&&y==j)
							return false;
						else
						{
							formation[i][j]=null;
							formation[x][y]=select;
							return true;
						}
					}
				}
			}
		}
		formation[x][y]=select;
		return true;
	}
	

	public int getX() {return xPro.get();}
	public int getY() {return yPro.get();}
	public IntegerProperty xProperty() {return xPro;}
	public IntegerProperty yProperty() {return yPro;}
	@Override
	public int getIndex() {
		return characters[0].getIndex();
	}
	public Entity[] getCharacters() {return characters;}
	public boolean inParty(Entity e) {
		for(Entity compare:characters) {
			if(e==compare)
				return true;
		}
		return false;
	}
	public void updateDead() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(formation[i][j]!=null&&formation[i][j].isDead())
					formation[i][j]=null;
			}
		}
	}
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject output=new JSONObject();
		output.put("x",getX());
		output.put("y",getY());
		JSONArray charactersJSON=new JSONArray();
		for(Entity e:characters) {
			charactersJSON.add(e.toJSON());
		}
		output.put("characters",charactersJSON);
		JSONArray formationJSON=new JSONArray();
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				Entity e=formation[i][j];
				if(e!=null) {
					for(int k=0;k<characters.length;k++) {
						if(e==characters[k]) {
							JSONArray temp=new JSONArray();
							temp.add(i);
							temp.add(j);
							temp.add(k);
							formationJSON.add(temp);
						}
					}
				}
			}
		}
		output.put("formation",formationJSON);
		return output;
	}
}
