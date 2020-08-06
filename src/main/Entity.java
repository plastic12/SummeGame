package main;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
	public static int MONSTER=0;
	public static int CHARACTER=1;
	

	public int getIndex() {return image;}
	
	protected Entity(JSONObject input) {
		name=(String)input.get("name");
		HP=new SimpleIntegerProperty(((Long)input.get("HP")).intValue());
		MP=new SimpleIntegerProperty(((Long)input.get("MP")).intValue());
		MaxMP=new SimpleIntegerProperty(((Long)input.get("MaxMP")).intValue());
		MaxHP=new SimpleIntegerProperty(((Long)input.get("MaxHP")).intValue());
		level=((Long)input.get("level")).intValue();
		agi=((Long)input.get("agi")).intValue();
		str=((Long)input.get("str")).intValue();
		Intell=((Long)input.get("Intell")).intValue();
		luck=((Long)input.get("luck")).intValue();
		statRes=((Long)input.get("statRes")).intValue();
		image=((Long)input.get("image")).intValue();
	}
	public Entity() {}
	
	public static Entity getEntity(JSONObject input) {
		int entityType=((Long)input.get("entityType")).intValue();
		if(entityType==MONSTER)
			return new Monster(input);
		else
			return null;
	}
	
	public UpdateImView getImage() {
		return new UpdateImView(32,32,image,Library.textures.get(2));
	};
	public List<Skill> getSkill() {
		return skills;
	}

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
		p.updateDead();
	}
	public void skill(Skill s,Party p,int x,int y) {
		s.action(this, p, x, y);
		p.updateDead();
	}
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject output=new JSONObject();
		output.put("name",name);
		output.put("level",level);
		output.put("HP",HP.get());
		output.put("MP",MP.get());
		output.put("MaxHP",MaxHP.get());
		output.put("MaxMP",MaxMP.get());
		output.put("agi",agi);
		output.put("str",str);
		output.put("Intell",Intell);
		output.put("luck",luck);
		output.put("statRes",statRes);
		output.put("image",image);
		return output;
	}

}
