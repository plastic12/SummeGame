package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Party extends ObjTile{
	protected Map map;
	protected IntegerProperty xPro;
	protected IntegerProperty yPro;
	public Character[] characters=new Character[5];
	public Character[][] formation=new Character[3][3];
	
	
	public Party( int x, int y) {
		xPro=new SimpleIntegerProperty(x);
		yPro=new SimpleIntegerProperty(y);
		characters[0]=Character.characterModel1();
		characters[1]=Character.characterModel2();
		characters[2]=Character.characterModel3();
		characters[3]=Character.characterModel4();
		characters[4]=Character.characterModel5();
		/*
		for(int i=0;i<5;i++) {
			characters[0]=new Character();
		}*/
	}
	public void setLayer(Map map) {
		this.map=map;
	}
	public Character getFormation(int x,int y) {
		if(x<0||x>=3||y<0||y>=3)
			return null;
		return formation[x][y];
	}
	public boolean setFormation(int x,int y,int index) {
		if(index<0||index>=5)
			return false;
		if(x<0||x>=3||y<0||y>=3)
			return false;
		Character select=characters[index];
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
	
	public void move(int newX,int newY) {
		if(!map.canMove(newX, newY))
			return;
		else {
			map.move(this,getX(),getY(),newX,newY);
			xPro.set(newX);
			yPro.set(newY);

			//System.out.println((layer.wholemap[xPro.get()][yPro.get()]==null));
		}
	}
	public int getX() {return xPro.get();}
	public int getY() {return yPro.get();}
	public IntegerProperty xProperty() {return xPro;}
	public IntegerProperty yProperty() {return yPro;}
	public void moveUp() {
		move(getX(),getY()-1);
	}
	public void moveDown() {
		move(getX(),getY()+1);
	}
	public void moveRight() {
		move(getX()+1,getY());
	}
	public void moveLeft() {
		move(getX()-1,getY());
	}
	@Override
	public int getIndex() {
		return characters[0].getIndex();
	}
	public Character[] getCharacters() {return characters;}
	
	
	
	
	
	
	

}
