package main;

import java.util.ArrayList;

public class MainChar extends Party{
	public ArrayList<Weapon> weapons=new ArrayList<Weapon>();
	public ArrayList<Armor> armors=new ArrayList<Armor>();
	public ArrayList<Consumable> items=new ArrayList<Consumable>();
	

	public MainChar(int x, int y) {
		super(x, y);
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
	
	
	
	
	

}
