package main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class BattlePanel extends Pane{
	public Party party;
	public Party enemy;
	public BattleFormPane selfPane;
	public BattleFormPane enemyPane;
	public Queue<Entity> turnQ;
	public HBox QRender;
	public HBox skillBar;
	private Skill currentSkill;
	private List<Position> positions;

	public BattlePanel() {
		setPrefHeight(500);
		setPrefWidth(500);
		setStyle("-fx-background-color:aliceblue");
		selfPane=new BattleFormPane();
		selfPane.layoutXProperty().bind(selfPane.widthProperty().divide(-2).add(125));
		selfPane.setLayoutY(250);
		enemyPane=new BattleFormPane();
		enemyPane.layoutXProperty().bind(enemyPane.widthProperty().divide(-2).add(375));
		enemyPane.setLayoutY(250);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				final int xPos=i;
				final int yPos=j;
				enemyPane.images[i][j].setOnMouseClicked(e->{
					if(currentSkill!=null) {
						for(Position p:positions) {
							if(xPos==p.x&&yPos==p.y) {
								turnQ.peek().skill(currentSkill, enemy, xPos, yPos);
								Platform.runLater(()->endTurn());
							}
						}
					}
				});
			}
		}
		turnQ=new ArrayDeque<Entity>();
		QRender=new HBox();
		QRender.setLayoutX(32);
		QRender.setLayoutY(0);
		skillBar=new HBox();
		skillBar.layoutXProperty().bind(skillBar.widthProperty().divide(-2).add(250));
		skillBar.layoutYProperty().bind(skillBar.heightProperty().divide(-2).add(125));


		ImageView arrow=new ImageView("texture/arrow.png");




		this.getChildren().addAll(selfPane,enemyPane,arrow,QRender,skillBar);
	}
	public boolean win() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(enemy.getFormation(i, j)!=null)
					return false;
			}
		}
		return true;
	}
	public void clear() {
		selfPane.clear();
		enemyPane.clear();
	}
	private void endTurn() {
		turnQ.poll();
		QRender.getChildren().remove(0);
		enemyPane.updateParty();
		selfPane.updateParty();
		if(win()) {
			System.out.println("win");
			Main.map.removeEntity(enemy);
			Main.changeScene(SceneStatus.map);
			clear();
			return;
		}
		if(turnQ.isEmpty()) {
			reOrder();
		}
		Platform.runLater(()->setTurn());
	}
	
	
	
	public void setup(Party self,Party enemy) {
		party=self;
		this.enemy=enemy;
		selfPane.setParty(party);
		enemyPane.setParty(enemy);
		reOrder();
		setTurn();
	}
	private void setTurn() {
		currentSkill=null;
		enemyPane.clearHL();
		Entity e=turnQ.peek();
		if(party.inParty(e)) {
			setSkill();
		}
		else
			Platform.runLater(()->endTurn());
	}
	private void setSkill() {
		skillBar.getChildren().clear();
		Entity e=turnQ.peek();

		for(Skill s:e.getSkill()) {
			UpdateImView image=s.getImage();
			image.setOnMouseClicked(event->{
				currentSkill=s;
				positions=s.getSelector(enemy);
				enemyPane.highlight(positions);
			});
			skillBar.getChildren().add(image);
		}
	}
	private void reOrder() {
		PriorityQueue<Entity> sort_machine=new PriorityQueue<Entity>(18,(e1,e2)->{
			Integer c1=e1.getAgi();
			Integer c2=e2.getAgi();
			return c2.compareTo(c1);
		});
		//add sort
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				Entity e=enemy.getFormation(i, j);
				if(e!=null)
					sort_machine.add(e);
			}
		}
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				Entity e=party.getFormation(i, j);
				if(e!=null)
					sort_machine.add(e);
			}
		}
		//sort
		ObservableList<Node> render=QRender.getChildren();
		if(!turnQ.isEmpty()) {
			turnQ.clear();
		}
		for(;!sort_machine.isEmpty();) {
			turnQ.add(sort_machine.poll());
		}
		for(Entity e:turnQ) {
			render.add(e.getImage());
		}
	}

}
