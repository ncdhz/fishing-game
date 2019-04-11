package com.github.ncdhz.fish.tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.github.ncdhz.fish.RunFishingGame;

public class ControlButton{
	private ImageButton imageButton;
	
	public ControlButton(String imageUp,String imageDown){
		TextureRegion textureRegion =new TextureRegion(new Texture(imageUp));
		Drawable drawableImageUp = new TextureRegionDrawable(textureRegion);
		Drawable drawableImageDown = new TextureRegionDrawable(new TextureRegion(new Texture(imageDown)));
		imageButton = new ImageButton(drawableImageUp, drawableImageDown);
		imageButton.setWidth(textureRegion.getRegionWidth()*RunFishingGame.RATE);
		imageButton.setHeight(textureRegion.getRegionHeight()*RunFishingGame.RATE);
	}
	
	
	public ImageButton getImageButton() {
		return imageButton;
	}
	public void setImageButton(ImageButton imageButton) {
		this.imageButton = imageButton;
		
	}
}
