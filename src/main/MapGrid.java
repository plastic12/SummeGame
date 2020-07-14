package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MapGrid extends ImageView{
	public Image texture;
	public int textureWidth;
	public static final int nullValue=-1;
	public static Image nullTexture=new Image("texture/white.png");
	public int x;
	public int y;
	private IntegerProperty index=new SimpleIntegerProperty();
	public MapGrid(int x,int y,IntegerProperty ref,Image texture) {
		this.texture=texture;
		textureWidth=(int) (texture.getWidth()/32);
		this.x=x;
		this.y=y;
		index.bind(ref);
		setSmooth(true);
		setFitHeight(32);
		setFitHeight(32);
		updateImage();
		index.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->{
			if(!oldValue.equals(newValue))
				updateImage();
		});
		GridPane.setConstraints(this,x,y);
	}
	private void updateImage() {


		if(index.get()!=nullValue)
		{
			setImage(texture);
			int i=index.get()%textureWidth;
			int j=index.get()/textureWidth;
			Rectangle2D croppedPortion = new Rectangle2D(i*32, j*32, 32, 32);
			setViewport(croppedPortion);
		}
		else {
			setImage(nullTexture);
			Rectangle2D croppedPortion = new Rectangle2D(0,0, 32, 32);
			setViewport(croppedPortion);
		}


	}
}