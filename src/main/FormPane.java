package main;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class FormPane extends Pane{
	
	private static final int HGAP=6;
	private static final int VGAP=6;
	public ImagePane images[][]=new ImagePane[3][3];
	private Party party;
	
	public FormPane() {
		this.setPrefHeight((VGAP+64)*3+VGAP);
		this.setPrefWidth((HGAP+64)*3+HGAP);
		this.setStyle("-fx-background-image:url(\"texture/grid.png\");");
		GridPane grid=new GridPane();
		grid.setHgap(HGAP);
		grid.setVgap(VGAP);
		grid.setLayoutX(HGAP);
		grid.setLayoutY(VGAP);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				//init image
				ImagePane imagePane=new ImagePane();
				images[i][j]=imagePane;
				//set init image
				grid.add(imagePane, i, j);
			}
		}
		
		this.getChildren().addAll(grid);
	}
	public void updateParty() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				Entity c=party.getFormation(i, j);
				images[i][j].setIndex((c!=null)?c.getIndex():UpdateImView.nullValue);
			}
		}
	}
	public void clear() {
		party=null;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				images[i][j].setIndex(UpdateImView.nullValue);
			}
		}
	}
	public void setParty(Party party) {
		this.party=party;
		updateParty();
	}
	public void set(int i,int j,int index) {
		images[i][j].setIndex(index);
	}
	
	
	static class ImagePane extends Pane{
		UpdateImView image;
		public ImagePane() {
			//his.setStyle("-fx-background-color:red");
			image=new UpdateImView(32,32,Library.textures.get(2));
			image.setFitWidth(64);
			image.setFitHeight(64);
			this.setPrefHeight(64);
			this.setPrefWidth(64);
			this.getChildren().add(image);
		}
		public void setIndex(int index) {
			image.setIndex(index);
		}
		
	}

}
