package main;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;

public abstract class Entity {
	protected String name;
	protected int level;
	protected IntegerProperty HP;
	protected IntegerProperty MP;
	protected IntegerProperty MaxMP;
	protected IntegerProperty MaxHP;
	protected int agi;
	protected int str;
	protected int Intell;
	protected int luck;
	protected int statRes;
	protected int image;
	protected List<Skill> skills=new ArrayList<Skill>();
	

	public int getIndex() {return image;}


	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public int getHP() {
		return HP.get();
	}
	public IntegerProperty HPProperty() {return HP;}

	public int getMP() {
		return MP.get();
	}
	public IntegerProperty MPProperty() {return MP;}

	public int getMaxMP() {
		return MaxMP.get();
	}
	public IntegerProperty MaxMPProperty() {return MaxMP;}

	public int getMaxHP() {
		return MaxHP.get();
	}
	public IntegerProperty MaxHPProperty() {return MaxHP;}

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
	public boolean isDead() {
		return (HP.get()==0);
	}
	public abstract void damage(int d);
	public void skill(int i,Party p,int x,int y) {
		skills.get(i).action(this, p, x, y);
	}


}
