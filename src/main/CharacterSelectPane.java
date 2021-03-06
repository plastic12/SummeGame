package main;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class CharacterSelectPane extends ListView<Entity>{

	public CharacterSelectPane() {
		super();
	    setMaxHeight(200);
	    setPrefWidth(200);
		setCellFactory(param -> new ListCell<Entity>() {
			private UpdateImView imageView = new UpdateImView(32,32,Library.textures.get(2));
			@Override
			public void updateItem(Entity c, boolean empty) {
				super.updateItem(c, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
				} else {
					
					setText(c.getName());
					imageView.setIndex(c.getIndex());
					setGraphic(imageView);
				}
			}
		}
				);
	}

}
