package main;

import javafx.scene.layout.Pane;

public class FormationSelectPane extends Pane{
	private CharacterSelectPane selectPane;
	public FormationSelectPane(Party party) {
		selectPane = new CharacterSelectPane();
	    FormPane formPane=new FormPane();
	    //set image grid
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				//place holder
				final int xplace=i;
				final int yplace=j;
				//set init image
				Character c=party.getFormation(i, j);
				if(c!=null)
					formPane.set(i,j,c.getIndex());
				//set formation event
				formPane.images[i][j].setOnMouseClicked(e->{
					boolean change=party.setFormation(xplace, yplace,selectPane.getSelectionModel().getSelectedIndex());
					if(change) {
						//update
						for(int x=0;x<3;x++) {
							for(int y=0;y<3;y++) {
								Character c2=party.getFormation(x, y);
								if(c2==null)
									formPane.set(x,y,-1);
								else
									formPane.set(x,y,c2.getIndex());
							}
						}
						
					}
				});
			}
		}
		
		
		
		//grid.add(new Button("testing"),0,3);
		formPane.setLayoutX(25);
		formPane.setLayoutY(25);
		selectPane.setLayoutX(275);
		selectPane.setLayoutY(25);
		Character[] characters=party.getCharacters();
		for(Character c:characters) {
			selectPane.getItems().add(c);
		}
		
		
	    getChildren().addAll(selectPane,formPane);
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
	public void reset() {
		selectPane.getSelectionModel().select(-1);
	}
	

}
