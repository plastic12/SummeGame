package main;

import org.json.simple.JSONArray;

public class Position implements Comparable<Position>{
	public int x;
	public int y;
	public Position(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public Position(JSONArray input) {
		x=((Long)input.get(0)).intValue();
		y=((Long)input.get(1)).intValue();
	}
	@Override
	public int compareTo(Position o) {
		if(o.x!=x)
			return (o.x>x)? 1:-1;
		else if(o.y!=y)
			return (o.y>y)? 1:-1;
		else
			return 0;
	}
	

}
