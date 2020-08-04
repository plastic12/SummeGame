package main;

import javafx.scene.layout.Pane;

public class FormationSelectPane extends Pane{
	private CharacterSelectPane selectPane;
	private FormPane formPane;
	public FormationSelectPane(Party party) {
		this.setStyle("-fx-background-color:aliceblue");
		selectPane = new CharacterSelectPane();
	    formPane=new FormPane();
	    formPane.setParty(party);
	    //set image grid
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				//place holder
				final int xplace=i;
				final int yplace=j;
				//set init image
				Entity c=party.getFormation(i, j);
				if(c!=null)
					formPane.set(i,j,c.getIndex());
				//set formation event
				formPane.images[i][j].setOnMouseClicked(e->{
					boolean change=party.setFormation(xplace, yplace,selectPane.getSelectionModel().getSelectedIndex());
					if(change) {
						//update
						formPane.updateParty();
					}
				});
			}
		}
		
		
		
		//grid.add(new Button("testing"),0,3);
		formPane.setLayoutX(25);
		formPane.setLayoutY(25);
		selectPane.setLayoutX(275);
		selectPane.setLayoutY(25);
		Entity[] characters=party.getCharacters();
		for(Entity c:characters) {
			selectPane.getItems().add(c);
		}
		
		
	    getChildren().addAll(selectPane,formPane);
	}
	public void reset() {
		formPane.clear();
	}
	

}
