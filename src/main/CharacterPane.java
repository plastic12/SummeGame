package main;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class CharacterPane extends Pane{

	private CharacterSelectPane selectPane;
	private ItemPanel itemPanel;
	private Character current;
	private UpdateImView weaponView;
	private UpdateImView armorView;

	public CharacterPane(MainChar party) {
		setPrefHeight(500);
		setPrefWidth(500);
		//this.setStyle("-fx-background-color:aliceblue");
		this.setStyle("-fx-background-image: url(\"texture/bg.png\")");
		Text nameLabel=new Text();
		Text levelLabel=new Text();
		Text strLabel=new Text();
		Text agiLabel=new Text();
		Text intLabel=new Text();
		Text luckLabel=new Text();
		FlowPane statusPane=new FlowPane();
		UpdateImView characterView=new UpdateImView(32,32,Library.textures.get(2));
		weaponView=new UpdateImView(32,32,Library.textures.get(3));
		armorView=new UpdateImView(32,32,Library.textures.get(3));
		itemPanel=new ItemPanel();

		weaponView.setDefault(3);
		weaponView.setVisible(false);
		armorView.setDefault(8);
		armorView.setVisible(false);
		itemPanel.setVisible(false);


		selectPane = new CharacterSelectPane();
		selectPane.setLayoutX(25);
		selectPane.setLayoutY(25);
		Entity[] characters=party.getCharacters();
		for(Entity c:characters) {
			selectPane.getItems().add(c);
		}

		selectPane.getSelectionModel().selectedItemProperty().addListener((obs,oldValue,newValue)->{
			if(newValue==null) {
				nameLabel.setText("");
				levelLabel.setText("");
				strLabel.setText("");
				agiLabel.setText("");
				intLabel.setText("");
				luckLabel.setText("");
				characterView.setIndex(UpdateImView.nullValue);

			}
			else {
				current=(Character) newValue;
				nameLabel.setText("name:"+current.getName());
				levelLabel.setText("level:"+Integer.toString(current.getLevel()));
				strLabel.setText("str:"+Integer.toString(current.getStr()));
				agiLabel.setText("agi:"+Integer.toString(current.getAgi()));
				intLabel.setText("int:"+Integer.toString(current.getIntell()));
				luckLabel.setText("luck:"+Integer.toString(current.getLuck()));
				characterView.setIndex(current.getIndex());
				weaponView.setVisible(true);
				Weapon w=current.getWeapon();
				weaponView.setIndex((w==null)? UpdateImView.nullValue:w.index);
				armorView.setVisible(true);
				Armor a=current.getArmor();
				armorView.setIndex((a==null)? UpdateImView.nullValue:a.index);
			}
		});


		statusPane.setLayoutX(25);
		statusPane.setLayoutY(250);
		statusPane.setPrefWidth(200);
		statusPane.getChildren().addAll(nameLabel,levelLabel,strLabel,agiLabel,intLabel,luckLabel);


		characterView.setLayoutX(275);
		characterView.setLayoutY(25);

		weaponView.setLayoutX(275);
		weaponView.setLayoutY(400);
		weaponView.setOnMouseClicked((e)->{
			if(!itemPanel.isVisible()){
				//open item panel
				//disable things
				ArrayList<Item> toAdd=new ArrayList<Item>();
				for(Weapon w:party.getWeapons())
					toAdd.add(w);
				showItem(toAdd);
				itemPanel.getSelectionModel().selectedItemProperty().addListener((obs,oldValue,newValue)->{
					if(newValue!=null) {
						current.equipWeapon((Weapon) newValue);
						Weapon w=current.getWeapon();
						weaponView.setIndex((w==null)? UpdateImView.nullValue:w.index);
						Platform.runLater(()->{
							resetItemPanel();
						}
								);}
				});
			}
			else {
				resetItemPanel();
			}
		});


		armorView.setLayoutX(325);
		armorView.setLayoutY(400);
		armorView.setOnMouseClicked((e)->{
			if(!itemPanel.isVisible()){
				//open item panel
				//disable things
				ArrayList<Item> toAdd=new ArrayList<Item>();
				for(Armor w:party.getArmors())
					toAdd.add(w);
				showItem(toAdd);
				//selection listener
				itemPanel.getSelectionModel().selectedItemProperty().addListener((obs,oldValue,newValue)->{
					if(newValue!=null) {
						current.equipArmor((Armor) newValue);
						Armor a=current.getArmor();
						armorView.setIndex((a==null)? UpdateImView.nullValue:a.index);
						Platform.runLater(()->{
							resetItemPanel();
						}
								);}
				});
			}
			else {
				resetItemPanel();
			}
		});
		

		itemPanel.setLayoutX(25);
		itemPanel.setLayoutY(25);



		getChildren().addAll(selectPane,statusPane,characterView,weaponView,armorView,itemPanel);


	}
	public void showItem(ArrayList<Item> items) {
		selectPane.setMouseTransparent(true);
		itemPanel.setVisible(true);
		for(Item i:items) {
			itemPanel.getItems().add(i);
		}
	}
	public void resetItemPanel() {
		itemPanel.setVisible(false);
		itemPanel.getItems().clear();


		selectPane.setMouseTransparent(false);
	}

	public void reset() {
		current=null;
		selectPane.getSelectionModel().select(-1);
		resetItemPanel();

	}
	class ItemPanel extends ListView<Item>{
		public ItemPanel() {
			super();
			setCellFactory(param -> new ListCell<Item>() {
				private UpdateImView imageView = new UpdateImView(32,32,Library.textures.get(3));
				@Override
				public void updateItem(Item item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
						setGraphic(null);
					} else {
						if(item instanceof Weapon){
							if(((Weapon)item).c!=null)
								setText(item.getName()+"(E)");
						}
						else if(item instanceof Armor) {
							if(((Armor)item).c!=null)
								setText(item.getName()+"(E)");
						}
						else
							setText(item.getName());
						imageView.setIndex(item.getIndex());
						setGraphic(imageView);
					}
				}
			}
					);
		}

	}

}
