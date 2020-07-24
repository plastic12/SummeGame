package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UpdateImView extends ImageView{
	public Image texture;
	public int textureWidth;
	public static final int nullValue=-1;
	private int width;
	private int height;
	private IntegerProperty index=new SimpleIntegerProperty(nullValue);
	public UpdateImView(int width,int height,IntegerProperty ref,Image texture) {
		this(width,height,texture);
		setRef(ref);
	}
	public UpdateImView(int width,int height,Image texture) {
		this.texture=texture;
		this.width=width;
		this.height=height;
		textureWidth=(int) (texture.getWidth()/width);
		setSmooth(true);
		setFitWidth(width);
		setFitHeight(height);
		updateImage();
		index.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->{
			if(!oldValue.equals(newValue))
				updateImage();
		});
	}
	public void setRef(IntegerProperty ref) {
		index.bind(ref);
	}
	public void setIndex(int newVal) {
		index.set(newVal);
	}
	private void updateImage() {


		if(index.get()!=nullValue)
		{
			setVisible(true);
			setImage(texture);
			int i=index.get()%textureWidth;
			int j=index.get()/textureWidth;
			Rectangle2D croppedPortion = new Rectangle2D(i*width, j*height, width, height);
			setViewport(croppedPortion);
		}
		else {
			//setVisible(false);
			setImage(null);
			//Rectangle2D croppedPortion = new Rectangle2D(0,0, 32, 32);
			//setViewport(croppedPortion);
		}


	}
}
