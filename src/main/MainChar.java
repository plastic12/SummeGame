package main;

import java.util.ArrayList;

public class MainChar extends Party implements Movable{
	public ArrayList<Weapon> weapons=new ArrayList<Weapon>();
	public ArrayList<Armor> armors=new ArrayList<Armor>();
	public ArrayList<Consumable> items=new ArrayList<Consumable>();
	

	public MainChar(int x, int y) {
		super(x, y);
		characters[0]=Character.characterModel1();
		characters[1]=Character.characterModel2();
		characters[2]=Character.characterModel3();
		characters[3]=Character.characterModel4();
		characters[4]=Character.characterModel5();
	}
	public void addItem(Item item) {
		if(item instanceof Weapon) {
			weapons.add((Weapon) item);
		}
		else if(item instanceof Armor) {
			armors.add((Armor) item);
		}
		else {
			items.add((Consumable) item);
		}
	}
	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}
	public ArrayList<Armor> getArmors() {
		return armors;
	}
	public ArrayList<Consumable> getItems() {
		return items;
	}
	@Override
	public void move(int newX,int newY) {
		if(!map.canMove(newX, newY)) {
			//event
			ObjTile obj=map.getEntity(newX, newY);
			if(obj!=null&&obj instanceof Event) {
				Event e=(Event) obj;
				e.event(this);
			}
		}
		else {
			//move
			map.move(this,getX(),getY(),newX,newY);
			
		}
	}
	@Override
	public void setX(int x) {
		xPro.set(x);
		
	}
	@Override
	public void setY(int y) {
		yPro.set(y);
		
	}
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
	
	
	
	
	

}
