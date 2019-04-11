package com.github.ncdhz.fish.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.github.ncdhz.fish.superclass.AllAnimtion;
/**
 * 炮台类
 */
public class Control extends AllAnimtion{
	private Animation<TextureRegion> cannonAnimation;
	private float stateTime;
	private TextureRegion currentFrame;
	private int pd;
	public Control(String imageName, float x, float y,int pd) {
		super(imageName, x, y, 5, 1);
		 cannonAnimation = new Animation<TextureRegion>(0.15F, getWalkFrames());
		 cannonAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		 this.pd = pd;
	}
	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}
	public int getPd() {
		return pd;
	}
	@Override
    public void act(float delta) {
        super.act(delta);
    }
	@Override
	 public void draw(Batch batch, float parentAlpha) {
	        if (cannonAnimation == null || !isVisible()) {
	            return;
	        }
	        stateTime += Gdx.graphics.getDeltaTime();
	        currentFrame = cannonAnimation.getKeyFrame(stateTime);
	        batch.draw(
	        		currentFrame,
	                getX(), getY(), 
	                 getWidth()/2, 10, 
	                getWidth(), getHeight(),
	                getScaleX(), getScaleY(), 
	                getRotation()
	        );  
	 }
	 
}
