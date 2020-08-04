package main;

import javafx.beans.property.SimpleIntegerProperty;

public class Character extends Entity{
	public final int PUNCH_INDEX=3;
	private Weapon weapon;
	private Armor armor;


	private Character() {
		skills.add(new DamageSkill("punch",1,Skill.MELEE,PUNCH_INDEX));
		HP=new SimpleIntegerProperty();
		MP=new SimpleIntegerProperty();
		MaxHP=new SimpleIntegerProperty();
		MaxMP=new SimpleIntegerProperty();
	}

	public static Character characterModel1() {
		Character c=new Character();
		c.name="Charles";
		c.level=1;
		c.HP.set(110);
		c.MP.set(10);
		c.MaxHP.set(110);;
		c.MaxMP.set(10);
		c.agi=11;
		c.str=20;
		c.Intell=5;
		c.luck=5;
		c.image=0;
		return c;
	}

	public static Character characterModel2() {
		Character c=new Character();
		c.name="Mary";
		c.level=1;
		c.HP.set(50);
		c.MP.set(30);
		c.MaxHP.set(50);;
		c.MaxMP.set(30);
		c.agi=10;
		c.str=10;
		c.Intell=20;
		c.luck=5;
		c.image=1;
		return c;
	}
	public static Character characterModel3() {
		Character c=new Character();
		c.name="Sam";
		c.level=1;
		c.HP.set(80);
		c.MP.set(20);
		c.MaxHP.set(c.getHP());
		c.MaxMP.set(c.getMP());
		c.agi=20;
		c.str=10;
		c.Intell=6;
		c.luck=10;
		c.image=2;
		return c;
	}
	public static Character characterModel4() {
		Character c=new Character();
		c.name="Emily";
		c.level=1;
		c.HP.set(100);
		c.MP.set(30);
		c.MaxHP.set(c.getHP());
		c.MaxMP.set(c.getMP());
		c.agi=10;
		c.str=10;
		c.Intell=10;
		c.luck=10;
		c.image=3;
		return c;
	}
	public static Character characterModel5() {
		Character c=new Character();
		c.name="David";
		c.level=1;
		c.HP.set(50);
		c.MP.set(10);
		c.MaxHP.set(c.getHP());
		c.MaxMP.set(c.getMP());
		c.agi=10;
		c.str=10;
		c.Intell=5;
		c.luck=15;
		c.image=4;
		return c;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}

	public Armor getArmor() {
		return armor;
	}


	public void damage(int d) {
		int def=(armor==null)? 0:armor.def;
		int damage=(d>def)? d-def:0;
		HP.set((HP.get()>damage)? HP.get()-damage:0);
	}


	public String toString() {return name;}

	public void equipWeapon(Weapon w) {
		if(weapon!=null) {
			weapon.c=null;
		}
		if(w.c!=null) {
			w.c.weapon=null;
		}
		weapon=w;
		w.c=this;
		skills.set(0,w.getSkill());
	}
	public void equipArmor(Armor a) {
		if(armor!=null){
			armor.c.armor=null;
			armor.c=null;
		}
		armor=a;
		a.c=this;
	}

}
