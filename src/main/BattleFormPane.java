package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import main.FormPane.ImagePane;

public class BattleFormPane extends FormPane{
	public StatusBar[][] statusBars=new StatusBar[3][3];
	public BattleFormPane() {
		super();
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				//System.out.println((images[i][j]==null));
				StatusBar bar=new StatusBar();
				bar.setFillColor(Color.GREEN);
				bar.setLayoutY(50);
				statusBars[i][j]=bar;
				images[i][j].getChildren().add(bar);
				
			}
		}	
	}
	public void setParty(Party party) {
		super.setParty(party);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				Entity e=party.getFormation(i, j);
				if(e==null) {
					statusBars[i][j].setVisible(false);
				}
				else {
					statusBars[i][j].setVisible(true);
					statusBars[i][j].bindMax(e.MaxHPProperty());
					statusBars[i][j].bindCurrent(e.HPProperty());
				}
			}
		}
	}
	public void clear() {
		super.clear();
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				statusBars[i][j].reset();
			}
		}
	}
	static class StatusBar extends StackPane{
		public IntegerProperty max;
		public IntegerProperty current;
		private Rectangle r1;
		private Rectangle r2;
		public StatusBar() {
			max=new SimpleIntegerProperty(100);
			current=new SimpleIntegerProperty(100);
			Text text=new Text();
			text.textProperty().bind(current.asString().concat("/").concat(current));
			r1=new Rectangle(64,text.getFont().getSize());
			r2=new Rectangle(64,text.getFont().getSize());
			this.getChildren().addAll(r2,r1,text);
		}
		public void setFillColor(Color color) {
			r1.setFill(color);
		}
		public void setEmptyColor(Color color) {
			r2.setFill(color);
		}
		public void bindMax(IntegerProperty bind) {
			max.bind(bind);
		}
		public void bindCurrent(IntegerProperty bind) {
			current.bind(bind);
		}
		public void reset() {
			max.unbind();
			current.unbind();
		}
		
	}
}
