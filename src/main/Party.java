package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Party implements ObjTile{
	protected Map map;
	protected IntegerProperty xPro;
	protected IntegerProperty yPro;
	public Entity[] characters=new Entity[5];
	public Entity[][] formation=new Entity[3][3];
	
	
	public Party( int x, int y) {
		xPro=new SimpleIntegerProperty(x);
		yPro=new SimpleIntegerProperty(y);
		/*
		for(int i=0;i<5;i++) {
			characters[0]=new Character();
		}*/
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

}
