package main;

import javafx.scene.layout.Pane;

public class BattlePanel extends Pane{
	public static BattleFormPane selfPane;
	public static BattleFormPane enemyPane;
	public BattlePanel() {
		setPrefHeight(500);
		setPrefWidth(500);
		setStyle("-fx-background-color:aliceblue");
		selfPane=new BattleFormPane();
		selfPane.setLayoutX(25);
		selfPane.setLayoutY(250);
		enemyPane=new BattleFormPane();
		enemyPane.setLayoutX(250);
		enemyPane.setLayoutY(250);
		this.getChildren().addAll(selfPane,enemyPane);
	}
	public void clear() {
		selfPane.clear();
		enemyPane.clear();
	}
	public void setSelfParty(Party party) {
		selfPane.setParty(party);
	}
	public void setEnemy(Party party) {
		enemyPane.setParty(party);
	}

}
