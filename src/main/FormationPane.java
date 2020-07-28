package main;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FormationPane extends Pane{
	private Party party;
	public FormationPane(Party party) {
		this.party=party;
		GridPane grid=new GridPane();
	    CharacterSelectPane selectPane = new CharacterSelectPane();
	    
	    //set image grid
	    ImagePane[][] images=new ImagePane[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				//place holder
				final int xplace=i;
				final int yplace=j;
				//init image
				ImagePane image=new ImagePane();
				images[i][j]=image;
				//set init image
				Character c=party.getFormation(i, j);
				if(c!=null)
					image.setIndex(c.getIndex());
				//set formation event
				image.setOnMouseClicked(e->{
					System.out.println("click");
					boolean change=party.setFormation(xplace, yplace,selectPane.getSelectionModel().getSelectedIndex());
					if(change) {
						//update
						for(int x=0;x<3;x++) {
							for(int y=0;y<3;y++) {
								Character c2=party.getFormation(x, y);
								if(c2==null)
									images[x][y].setIndex(-1);
								else
									images[x][y].setIndex(c2.getIndex());
							}
						}
						
					}
				});
				grid.add(image, i, j);
			}
		}
		//grid.add(new Button("testing"),0,3);
		grid.setLayoutX(25);
		grid.setLayoutY(25);
		selectPane.setLayoutX(275);
		selectPane.setLayoutY(25);
		Character[] characters=party.getCharacters();
		for(Character c:characters) {
			selectPane.getItems().add(c);
		}
		
		
	    getChildren().addAll(selectPane,grid);
	}
	static class ImagePane extends Pane{
		UpdateImView image;
		public ImagePane() {
			image=new UpdateImView(32,32,Library.textures.get(2));
			this.setPrefHeight(32);
			this.setPrefWidth(32);
			this.getChildren().add(image);
		}
		void setIndex(int index){
			image.setIndex(index);
		}
	}
	

}
