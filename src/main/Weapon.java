package main;

public abstract class Weapon extends Item{
	public int atk;
	public Character c;
	
	
	public static Weapon getBow() {
		Weapon w=new Bow();
		w.name="Bow 1";
		w.atk=10;
		w.index=0;
		return w;
	}
	
	
	
	
	
	
	
	

}
