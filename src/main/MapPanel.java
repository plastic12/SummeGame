package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MapPanel extends Pane{
	public static final int screenSize=11;
	private GridPane grid;
	private GridPane grid2;
	private Text dialog;
	private IntegerProperty[][] view=new IntegerProperty[screenSize][screenSize];
	private IntegerProperty[][] view2=new IntegerProperty[screenSize][screenSize];
	private IntegerProperty cameraX;
	private IntegerProperty cameraY;
	
	public MapPanel() {
		
		
		for(int i=0;i<screenSize;i++) {
			for(int j=0;j<screenSize;j++) {
				view[i][j]=new SimpleIntegerProperty(-1);
				view2[i][j]=new SimpleIntegerProperty(-1);
			}
		}
		cameraX=new SimpleIntegerProperty(0);
		cameraY=new SimpleIntegerProperty(0);
		cameraX.bind(Main.party.xProperty().add(-5));
		cameraY.bind(Main.party.yProperty().add(-5));

		cameraX.addListener((observable,oldValue,newValue)->{
			if(!oldValue.equals(newValue))
				updateView();
		});
		cameraY.addListener((observable,oldValue,newValue)->{
			if(!oldValue.equals(newValue))
				updateView();
		});
		
		grid=new GridPane();
		for(int i=0;i<screenSize;i++) {
			for(int j=0;j<screenSize;j++) {
				MapGrid temp=new MapGrid(i,j,view[i][j],Library.textures.get(0));
				grid.getChildren().add(temp);
			}
		}
		grid2=new GridPane();
		for(int i=0;i<screenSize;i++) {
			for(int j=0;j<screenSize;j++) {
				ObjectGrid temp=new ObjectGrid(i,j,view2[i][j],Library.textures.get(1));
				grid.getChildren().add(temp);
			}
		}
		dialog =new Text();
		dialog.setLayoutX(0);
		dialog.setLayoutY(352+dialog.getFont().getSize());
		this.getChildren().addAll(grid,grid2,dialog);
		setOnKeyPressed((evt)->{
			if(dialog.getText()=="")
			{
				switch(evt.getCode())
				{
				case UP:
					Main.party.moveUp();
					break;
				case DOWN:
					Main.party.moveDown();
					break;
				case RIGHT:
					Main.party.moveRight();
					break;
				case LEFT:
					Main.party.moveLeft();
					break;
				default:
					break;

				}
			}
			else {
				if(evt.getCode()==KeyCode.ENTER)
				{
					resetText();
				}
			}
			evt.consume();
		});
		
		
	}
	public void updateView() {
		int[][] tempBg=Main.getMap().display(cameraX.get(), cameraY.get(), screenSize,0);
		int[][] tempObj=Main.getMap().display(cameraX.get(), cameraY.get(), screenSize,1);
		for(int i=0;i<screenSize;i++) {
			for(int j=0;j<screenSize;j++) {
				view[i][j].set(tempBg[i][j]);
				view2[i][j].set(tempObj[i][j]);
			}
		}
	}
	public void setText(String text) {
		dialog.setText(text);
	}
	public void resetText() {
		dialog.setText("");
	}
	

}
