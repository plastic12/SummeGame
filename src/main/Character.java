package main;

public class Character {
	private String name;
	private int level;
	private int HP;
	private int MP;
	private int MaxMP;
	private int MaxHP;
	private int agi;
	private int str;
	private int Intell;
	private int luck;
	private int statRes;
	private Weapon weapon;
	private Armor armor;
	private int image;
	
	
	private Character() {
	}
	
	public static Character characterModel1() {
		Character c=new Character();
		c.name="Charles";
		c.level=1;
		c.HP=100;
		c.MP=10;
		c.MaxHP=100;
		c.MaxMP=10;
		c.agi=10;
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
		c.HP=50;
		c.MP=30;
		c.MaxHP=50;
		c.MaxMP=30;
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
		c.HP=80;
		c.MP=20;
		c.MaxHP=80;
		c.MaxMP=20;
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
		c.HP=100;
		c.MP=30;
		c.MaxHP=100;
		c.MaxMP=30;
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
		c.HP=50;
		c.MP=10;
		c.MaxHP=50;
		c.MaxMP=10;
		c.agi=10;
		c.str=10;
		c.Intell=5;
		c.luck=15;
		c.image=4;
		return c;
	}
	
	
	
	public int getIndex() {return image;}
	
	
	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public int getHP() {
		return HP;
	}

	public int getMP() {
		return MP;
	}

	public int getMaxMP() {
		return MaxMP;
	}

	public int getMaxHP() {
		return MaxHP;
	}

	public int getAgi() {
		return agi;
	}

	public int getStr() {
		return str;
	}

	public int getIntell() {
		return Intell;
	}

	public int getLuck() {
		return luck;
	}

	public int getStatRes() {
		return statRes;
	}


	public String toString() {return name;}
	
	
	
	
	
	
	
	

}
