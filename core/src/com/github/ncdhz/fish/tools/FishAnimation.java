package com.github.ncdhz.fish.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.github.ncdhz.fish.RunFishingGame;
import com.github.ncdhz.fish.superclass.AllAnimtion;

public class FishAnimation extends AllAnimtion{
	private Animation<TextureRegion> cannonAnimation;
	private float stateTime;
	private TextureRegion currentFrame;
	private int fish;
	private int left_right;
	public FishAnimation(String imageName, float x, float y, int col, int row,int fish,int left_right) {
		super(imageName, x, y, col, row);
		this.left_right =left_right;
		TextureRegion[] walkFrame = new TextureRegion[row*col-4];
        for (int i=0;i<walkFrame.length;i++) {
        	walkFrame[i] = getWalkFrames()[i];
		}
        if(x==0) {
        	setX(x-walkFrame[0].getRegionWidth()-10);
        	if(y+walkFrame[0].getRegionHeight()>=RunFishingGame.DY_HEIGHT) {
        		setY(RunFishingGame.DY_HEIGHT-walkFrame[0].getRegionHeight());
        	}
        }else {
        	setX(x+walkFrame[0].getRegionWidth()+10);
        }
        if(fish==11||fish==12) {
        	setHeight(0.8f*walkFrame[0].getRegionHeight()*RunFishingGame.RATE);
            setWidth(0.8f*walkFrame[0].getRegionWidth()*RunFishingGame.RATE);
        }
		cannonAnimation = new Animation<TextureRegion>(0.17F,walkFrame);
		cannonAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		this.fish = fish;
	}
	public int getFish() {
		return fish;
	}
	public void setFish(int fish) {
		this.fish = fish;
	}
	public void setDie() {
		TextureRegion[] walkFrame = new TextureRegion[4];
		TextureRegion[] walkFrames =  getWalkFrames();
        for (int i=0;i<walkFrame.length;i++) {
        	walkFrame[i] = walkFrames[walkFrames.length-4+i];
		}
		cannonAnimation = new Animation<TextureRegion>(0.15F,walkFrame);
		cannonAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		stateTime=0;
	}
	@Override
    public void act(float delta) {
        super.act(delta);
    }
	
	public int getLeft_right() {
		return left_right;
	}
	public void setLeft_right(int left_right) {
		this.left_right = left_right;
	}
	@Override
	 public void draw(Batch batch, float parentAlpha) {
	        if (cannonAnimation == null || !isVisible()) {
	            return;
	        }
	        if(getScaleX()==0&&getScaleY()==0) {
	        	FishProduceTool.fishAnimationList.remove(this);
	        	remove();
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
