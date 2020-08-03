package main;

public class Monster extends Entity{
	
	private int def;
	protected Item drop;

	@Override
	public void damage(int d) {
		int damage=(d>def)? d-def:0;
		HP.set((HP.get()>damage)? HP.get()-damage:0);
	}

}
