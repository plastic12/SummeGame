package main;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class FormPane extends Pane{
	
	private static final int HGAP=3;
	private static final int VGAP=3;
	public ImagePane images[][]=new ImagePane[3][3];
	
	public FormPane() {
		this.setPrefHeight((VGAP+32)*3+VGAP);
		this.setPrefWidth((HGAP+32)*3+HGAP);
		this.setStyle("-fx-background-image:url(\"texture/grid.png\");");
		GridPane grid=new GridPane();
		grid.setHgap(HGAP);
		grid.setVgap(VGAP);
		grid.setTranslateX(HGAP);
		grid.setTranslateY(VGAP);
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
	public void set(int i,int j,int index) {
		images[i][j].setIndex(index);
	}
	
	
	static class ImagePane extends Pane{
		UpdateImView image;
		public ImagePane() {
			//his.setStyle("-fx-background-color:red");
			image=new UpdateImView(32,32,Library.textures.get(2));
			
			this.setPrefHeight(32);
			this.setPrefWidth(32);
			this.getChildren().add(image);
		}
		public void setIndex(int index) {
			image.setIndex(index);
		}
		
	}

}
