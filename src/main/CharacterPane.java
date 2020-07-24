package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
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
	    statusPane.setLayoutX(20);
	    statusPane.setLayoutY(250);
	    statusPane.getChildren().addAll(nameLabel,levelLabel,strLabel,agiLabel,intLabel,luckLabel);
		
		ListView<Character> selectPane = new ListView<Character>();
		selectPane.setLayoutX(20);
		selectPane.setLayoutY(20);
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
		});
		
	    selectPane.setMaxHeight(200);
	    selectPane.setPrefWidth(200);
	    getChildren().addAll(selectPane,statusPane);
	    

	}
	

}
