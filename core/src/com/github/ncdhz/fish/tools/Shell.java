package com.github.ncdhz.fish.tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import com.github.ncdhz.fish.RunFishingGame;
import com.github.ncdhz.fish.game.Game;
import com.github.ncdhz.fish.superclass.Background;
/**
 *炮弹类
 */
public class Shell extends Background{
	private int cannonNam;
	private Stage stage;
	float y;
	public Shell(Texture texture,float x,float y,int cannonNam,Stage stage) {
		super(texture);
		setX(x);
		setY(y);
		this.y = y;
		setWidth(texture.getWidth()*RunFishingGame.RATE);
		setHeight(texture.getHeight()*RunFishingGame.RATE);
		this.cannonNam = cannonNam;
		this.stage = stage;
	}
	
	public int getCannonNam() {
		return cannonNam;
	}
	public void setCannonNam(int cannonNam) {
		this.cannonNam = cannonNam;
	}
	/**
	 * 判断鱼是否打中
	 */
	@Override
	public boolean backgroundAction() {
	 	float fishY =18*RunFishingGame.RATE+(float) (getY()*Math.cos(getRotation()*Math.PI/180));
        float fishX =getX()+getWidth()/2-(float) (getY()*Math.sin(getRotation()*Math.PI/180));
        if(fishX>1024*RunFishingGame.RATE||fishX<0||fishY>720*RunFishingGame.RATE||fishY<0) {
        		return false;
        }
        int fiahdie=0;
        for(int i=0;i<FishProduceTool.fishAnimationList.size();i++) {
        	FishAnimation fish = FishProduceTool.fishAnimationList.get(i);
        		float x = fish.getX();
        		float y=fish.getY();
        		if(fish.getLeft_right()==1) {
        			y -=fish.getHeight();
        		}
        		if(fishX>=x&&fishX<=x+fish.getWidth()&&fishY>=y&&fishY<y+fish.getHeight()) {
        			fiahdie++;
        			Game.soundWang.play();
        			stage.addActor(FishProduceTool.fishingNet(cannonNam, fishX+10, fishY));
        			if(FishProduceTool.victory(fish.getFish(), cannonNam)) {
        				if(fish.getFish()<=5) {
        					stage.addActor(FishProduceTool.currency("coinAni1.png", fishX, fishY,fish.getX()));
        				}else {
        					stage.addActor(FishProduceTool.currency("coinAni2.png", fishX, fishY,fish.getX()));
        				}
        				
        				stage.addActor(FishProduceTool.score(fishX, fishY, FishProduceTool.scoreNum[fish.getFish()-1]));
        				Game.money += FishProduceTool.scoreNum[fish.getFish()-1];
        				Game.controlFraction.setFraction(Game.money);
        				Game.soundBY.play();
        				fish.clearActions();
        				fish.setDie();
        				i--;
        				FishProduceTool.fishAnimationList.remove(fish);
        				MoveByAction moveByAction = Actions.moveBy(0, 0,0.7f);
        				ScaleToAction scaleToAction1 = Actions.scaleTo(0, 0,0.0f);
        				SequenceAction parallelAction = Actions.sequence(moveByAction, scaleToAction1);
	        			fish.addAction(parallelAction);
        			}	
        		}
        }
        if(fiahdie!=0) {
    		return false;
        }
		setOriginX(getWidth()/2);
		setOriginY(-(getY()-18*RunFishingGame.RATE));
		return true;
	}


}
