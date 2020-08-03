package main;

import javafx.scene.layout.Pane;

public class BattlePanel extends Pane{
	public static BattleFormPane selfPane;
	public BattlePanel() {
		setPrefHeight(500);
		setPrefWidth(500);
		setStyle("-fx-background-color:aliceblue");
		selfPane=new BattleFormPane();
		this.getChildren().addAll(selfPane);
	}
	public void setSelfParty(Party party) {
		selfPane.setParty(party);
	}

}
