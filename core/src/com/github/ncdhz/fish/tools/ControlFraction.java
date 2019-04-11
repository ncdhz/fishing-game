package com.github.ncdhz.fish.tools;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.github.ncdhz.fish.RunFishingGame;
import com.github.ncdhz.fish.superclass.AllAnimtion;
/**
 * 炮弹分数
 */
public class ControlFraction extends AllAnimtion{
	private int fraction;

	TextureRegion[] walkFrames;
	public ControlFraction(float x, float y,int fraction) {
		super("number_black.png", x, y*RunFishingGame.RATE, 10,1);
		this.fraction = fraction;
		walkFrames=getWalkFrames();
	}
	public void setFraction(int fraction) {
		this.fraction = fraction;
	}
	@Override
    public void act(float delta) {
        super.act(delta);
    }
	@Override
	 public void draw(Batch batch, float parentAlpha) {
		int f = fraction;
		 for(int i=0;i<6;i++) {
			 batch.draw(walkFrames[9-f%10], getX()-i*23*RunFishingGame.RATE, getY(),getOriginX(), getOriginY(),
						getWidth(), getHeight(),
						getScaleX(), getScaleY(), 
						getRotation());;
			 f=f/10;
		 } 
	 }
	 
}
