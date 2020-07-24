package main;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class CharacterPane extends Pane{
	private Party party;
	
	
	public CharacterPane(Party party) {
		this.party=party;
		setPrefHeight(500);
		setPrefWidth(500);
	    Text nameLabel=new Text();
	    Text levelLabel=new Text();
	    Text strLabel=new Text();
	    Text agiLabel=new Text();
	    Text intLabel=new Text();
	    Text luckLabel=new Text();
	    FlowPane statusPane=new FlowPane();
	    UpdateImView characterView=new UpdateImView(32,32,Library.textures.get(2));
	    
		
	    CharacterSelectPane selectPane = new CharacterSelectPane();
		selectPane.setLayoutX(25);
		selectPane.setLayoutY(25);
		Character[] characters=party.getCharacters();
		for(Character c:characters) {
			selectPane.getItems().add(c);
		}
		
		selectPane.getSelectionModel().selectedItemProperty().addListener((obs,oldValue,newValue)->{
			nameLabel.setText("name:"+newValue.getName());
			levelLabel.setText("level:"+Integer.toString(newValue.getLevel()));
			strLabel.setText("str:"+Integer.toString(newValue.getStr()));
			agiLabel.setText("agi:"+Integer.toString(newValue.getAgi()));
			intLabel.setText("int:"+Integer.toString(newValue.getIntell()));
			luckLabel.setText("luck:"+Integer.toString(newValue.getLuck()));
			characterView.setIndex(newValue.getIndex());
		});
		
	    
	    statusPane.setLayoutX(25);
	    statusPane.setLayoutY(250);
	    statusPane.setPrefWidth(200);
	    statusPane.getChildren().addAll(nameLabel,levelLabel,strLabel,agiLabel,intLabel,luckLabel);
	    
	    
	    characterView.setLayoutX(275);
	    characterView.setLayoutY(25);
	    
	    
	    getChildren().addAll(selectPane,statusPane,characterView);
	    

	}
	

}
