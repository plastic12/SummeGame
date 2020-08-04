package main;

import java.util.ArrayList;
import java.util.List;

public abstract class Skill {
	public String name;
	public abstract void action(Entity entity,Party p,int x,int y);
	public abstract List<Position> getSelector(Party p);
	public int image;
	public static final int RANGE=1;
	public static final int MELEE=0;
	
	public Skill(String name,int image) {
		this.name=name;
		this.image=image;
	}
	public UpdateImView getImage() {
		return new UpdateImView(32,32,image,Library.textures.get(3));
	}
	
	public static List<Position> standardSelector(int indicator,Party p){
		ArrayList<Position> output=new ArrayList<Position>();
		if(indicator==RANGE) {
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					Entity c=p.getFormation(i, j);
					if(c!=null&&!c.isDead())
						output.add(new Position(i,j));
				}
			}
			return output;
		}
		else if(indicator==MELEE) {
			for(int j=0;j<3;j++) {
				for(int i=0;i<3;i++) {
					Entity c=p.getFormation(i, j);
					if(c!=null&&!c.isDead())
					{
						output.add(new Position(i,j));
						i=3;
					}
				}
			}
			return output;
		}
		else {
			System.out.println("indicator value out of range");
			return null;
		}
	}
}
