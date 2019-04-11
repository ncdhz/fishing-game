package com.github.ncdhz.fish.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.github.ncdhz.fish.superclass.AllAnimtion;
/**
 * 货币继承动作基类
 */
public class Currency extends AllAnimtion{
	private Animation<TextureRegion> cannonAnimation;
	private float stateTime;
	private TextureRegion currentFrame;
	public Currency(String imageName, float x, float y,float time) {
		super(imageName, x, y, 10, 1);
		cannonAnimation = new Animation<TextureRegion>(time,getWalkFrames());
		cannonAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
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
	        if(getScaleX()==0&&getScaleY()==0) {
	        	remove();
	        	return;
	        }
	        stateTime += Gdx.graphics.getDeltaTime();
	        currentFrame = cannonAnimation.getKeyFrame(stateTime);
	        batch.draw(
	        		currentFrame,
	                getX(), getY(), 
	                getWidth()/2, getHeight()/2, 
	                getWidth(), getHeight(),
	                getScaleX(), getScaleY(), 
	                getRotation()
	        );  
	 }

}
