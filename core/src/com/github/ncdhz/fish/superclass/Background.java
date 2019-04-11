package com.github.ncdhz.fish.superclass;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Background extends Actor{
	private TextureRegion region;
	protected Background(Texture texture){
		if(region==null&&texture!=null) {
			region = new TextureRegion(texture);
		}
	}
	public boolean backgroundAction() {
		return true;
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(region==null&&isVisible()) {
			return;
		}
		if(!backgroundAction()) {
			remove();
			return;
		}
		if(getScaleX()==0&&getScaleY()==0) {
			remove();
			return;
		}
		batch.draw(region, getX(), getY(),
				getOriginX(), getOriginY(), 
				getWidth(), getHeight(),
				getScaleX(), getScaleY(), 
				getRotation());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
}
