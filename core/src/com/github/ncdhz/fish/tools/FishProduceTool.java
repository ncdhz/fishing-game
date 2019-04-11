package com.github.ncdhz.fish.tools;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import com.github.ncdhz.fish.RunFishingGame;

/**
 * 鱼的逻辑实现
 */
public class FishProduceTool{
	public static List<FishAnimation> fishAnimationList;
	public static int[] scoreNum = {1,3,5,8,10,20,30,40,50,60,100,200};
	private static float[] spedTime= {30*RunFishingGame.RATE,
			33*RunFishingGame.RATE,35*RunFishingGame.RATE,38*RunFishingGame.RATE,
			40*RunFishingGame.RATE,45*RunFishingGame.RATE,47*RunFishingGame.RATE,
			49*RunFishingGame.RATE,50*RunFishingGame.RATE,51*RunFishingGame.RATE,
			60*RunFishingGame.RATE,60*RunFishingGame.RATE};
	private Stage stage;
	static {
		if(fishAnimationList==null) {
			fishAnimationList = new ArrayList<FishAnimation>();
		}
	}
	public FishProduceTool(Stage stage) {
		this.stage = stage;
	}
	/**
	 * 鱼运动的类
	 */
	public synchronized void fish() {
		if(fishAnimationList.size()<150) {
			if(Math.random()*100<5) {
				int x=(int)(Math.random()*100);
				int y = x==1?12:x>1&&x<=3?11:x>3&&x<=6?10:x>6&&x<=10?9:x>10&&x<=15?8:x>15&&x<=21?7:x>21&&x<=28?6:x>28&&x<=36?5:x>36&&x<=45?4:x>45&&x<=60?3:x>60&&x<=80?2:x>80&&x<=100?1:0;
				if(y!=0) {
					FishAnimation fishAnimation=null;
					if((int)(Math.random()*2)%2==1) {
						float fristHeight = (float)(Math.random()*RunFishingGame.DY_HEIGHT);
						fishAnimation=chooseFish(RunFishingGame.DY_WIDTH,fristHeight, y,1);
						fristHeight = fristHeight - fishAnimation.getHeight();
						if(fishAnimation!=null) {
							fishAnimation.setRotation(180);
							SequenceAction sequenceAction =null;
							if((int)(Math.random()*2)%2==1) {
								MoveByAction movetoAction = Actions.moveBy(-1800*RunFishingGame.RATE,0,spedTime[y-1]);
								ScaleToAction scaleToAction = Actions.scaleTo(0, 0,0);
								sequenceAction= Actions.sequence(movetoAction, scaleToAction);
							}else {
								int xaction1 = (int)(Math.random()*850*RunFishingGame.RATE);
								MoveByAction movetoAction = Actions.moveBy(-xaction1,0,xaction1/(1800f*RunFishingGame.RATE)*spedTime[y-1]);
								
								float lastTime = (float) (Math.sqrt((xaction1%2==1?-xaction1-RunFishingGame.DY_HEIGHT+fristHeight-200:-xaction1-fristHeight-200)*(xaction1%2==1?-xaction1-RunFishingGame.DY_HEIGHT+fristHeight-200:-xaction1-fristHeight-200)+
										(xaction1%2==1?RunFishingGame.DY_HEIGHT+200-fristHeight:fristHeight+200)*(xaction1%2==1?RunFishingGame.DY_HEIGHT+200-fristHeight:fristHeight+200))/(1800f*RunFishingGame.RATE)*spedTime[y-1]);
								RotateByAction rotateByAction = Actions.rotateBy(xaction1%2==1?-90:90,lastTime);
								MoveToAction moveToAction = Actions.moveTo(xaction1%2==1?-xaction1-RunFishingGame.DY_HEIGHT+fristHeight-200:-xaction1-fristHeight-200, xaction1%2==1?RunFishingGame.DY_HEIGHT+200:-200,lastTime);
								ParallelAction parallelAction = Actions.parallel(moveToAction,rotateByAction);
								ScaleToAction scaleToAction = Actions.scaleTo(0, 0,0);
								
								sequenceAction= Actions.sequence(movetoAction, parallelAction,scaleToAction);
							}
							fishAnimation.addAction(sequenceAction);
						}
					}
					else {
						float fristHeight =(float)(Math.random()*RunFishingGame.DY_HEIGHT);
						fishAnimation=chooseFish(0, fristHeight, y,0);
						if(fishAnimation!=null) {
							SequenceAction sequenceAction=null;
							if((int)(Math.random()*2)%2==1) {
								MoveByAction movetoAction = Actions.moveBy(1800*RunFishingGame.RATE,0,spedTime[y-1]);
								ScaleToAction scaleToAction = Actions.scaleTo(0, 0,0);
								sequenceAction = Actions.sequence(movetoAction, scaleToAction);
							}else {
								int xaction1 = (int)(Math.random()*850);
								MoveByAction movetoAction = Actions.moveBy(xaction1,0,xaction1/(1800f*RunFishingGame.RATE)*spedTime[y-1]);
								
								float lastTime = (float) (Math.sqrt((xaction1%2==1?xaction1+RunFishingGame.DY_HEIGHT-fristHeight+200:xaction1+fristHeight+200)*(xaction1%2==1?xaction1+RunFishingGame.DY_HEIGHT-fristHeight+200:xaction1+fristHeight+200)+
										(xaction1%2==1?RunFishingGame.DY_HEIGHT+200-fristHeight:fristHeight+200)*(xaction1%2==1?RunFishingGame.DY_HEIGHT+200-fristHeight:fristHeight+200))/(1800f*RunFishingGame.RATE)*spedTime[y-1]);
								
								RotateByAction rotateByAction = Actions.rotateBy(xaction1%2==1?90:-90,lastTime);
								MoveToAction moveToAction = Actions.moveTo(xaction1%2==1?xaction1+RunFishingGame.DY_HEIGHT-fristHeight+200:xaction1+fristHeight+200, xaction1%2==1?RunFishingGame.DY_HEIGHT+200:-200,lastTime);
								
								ParallelAction parallelAction = Actions.parallel( moveToAction,rotateByAction);
								
								
								ScaleToAction scaleToAction = Actions.scaleTo(0, 0,0);
								sequenceAction = Actions.sequence(movetoAction,parallelAction, scaleToAction);
							}
							fishAnimation.addAction(sequenceAction);
						}
					}
					if(fishAnimation!=null) {
						fishAnimationList.add(fishAnimation);
						stage.addActor(fishAnimation);
						fishAnimation.setZIndex(1);
					}
					
				}
			}	
		}
	}
	/**
	 * 创建鱼的类
	 * @return
	 */
	private FishAnimation chooseFish(float x,float y,int choose,int left_right) {
		FishAnimation fishAnimation = null;
		switch (choose) {
		case 1:
			fishAnimation=new FishAnimation("fish1.png",x,y,8,1,1,left_right);
			break;
		case 2:
			fishAnimation=new FishAnimation("fish2.png",x,y,8,1,2,left_right);
			break;
		case 3:
			fishAnimation=new FishAnimation("fish3.png",x,y,8,1,3,left_right);
			break;
		case 4:
			fishAnimation=new FishAnimation("fish4.png",x,y,8,1,4,left_right);
			break;
		case 5:
			fishAnimation=new FishAnimation("fish5.png",x,y,8,1,5,left_right);
			break;
		case 6:
			fishAnimation=new FishAnimation("fish6.png",x,y,12,1,6,left_right);
			break;
		case 7:
			fishAnimation=new FishAnimation("fish7.png",x,y,10,1,7,left_right);
			break;
		case 8:
			fishAnimation=new FishAnimation("fish8.png",x,y,12,1,8,left_right);
			break;
		case 9:
			fishAnimation=new FishAnimation("fish9.png",x,y,12,1,9,left_right);
			break;
		case 10:
			fishAnimation=new FishAnimation("fish10.png",x,y,10,1,10,left_right);
			break;
		case 11:
			fishAnimation=new FishAnimation("shark1.png",x,y,12,1,11,left_right);
			break;
		default:
			fishAnimation=new FishAnimation("shark2.png",x,y,12,1,12,left_right);
			break;
		}
		return fishAnimation;
	}
	/**
	 * 击中鱼时调用的网
	 */
	public static BackgoundImage fishingNet(int cannonNam,float x,float y) {
		Texture texture= new Texture("web"+cannonNam+".png");
		BackgoundImage fishingNet = new BackgoundImage(texture,x,y,1,1);
		fishingNet.setOrigin(fishingNet.getWidth() / 2, fishingNet.getHeight() / 2);
		ScaleToAction scaleToAction = Actions.scaleTo(texture.getWidth()*RunFishingGame.RATE, texture.getHeight()*RunFishingGame.RATE,0.15f*RunFishingGame.RATE);
		ScaleToAction scaleToAction1 = Actions.scaleTo(0, 0,0.15f);
		SequenceAction parallelAction = Actions.sequence(scaleToAction, scaleToAction1);
		fishingNet.addAction(parallelAction);
		return fishingNet;
	}
	/**
	 * 分数动态效果 打中之后调用
	 */
	public static Score score(float x,float y,int num) {
		Score gameScore = new Score(x,y,num);
		MoveByAction moveByAction = Actions.moveBy(0,30*RunFishingGame.RATE,0.5f*RunFishingGame.RATE);
		ScaleToAction scaleToAction = Actions.scaleTo(0, 0,0);
		SequenceAction parallelAction = Actions.sequence(moveByAction, scaleToAction);
		gameScore.addAction(parallelAction);	
		return gameScore;
	}
	/**
	 * 金币动态效果 打鱼中了之后调用
	 */
	public static Currency currency(String imageName, float x, float y,float time) {
		Currency gameCurrency =new Currency(imageName, x, y,0.02f*RunFishingGame.RATE);
		MoveToAction moveToAction = Actions.moveTo(250*RunFishingGame.RATE, 20*RunFishingGame.RATE,time/(500f*RunFishingGame.RATE)*1.2f*RunFishingGame.RATE);
		ScaleToAction scaleToAction = Actions.scaleTo(0, 0,0.05f*RunFishingGame.RATE);
		SequenceAction parallelAction = Actions.sequence(moveToAction, scaleToAction);
		gameCurrency.addAction(parallelAction);
		return gameCurrency;
	}
	/**
	 * 捕中鱼概率
	 */
	public static boolean victory(Integer fish,Integer bubble) {
		double[] fishs = {0.79,0.77,0.75,0.72, 0.70,0.65,0.60,0.50,0.40,0.30,0.02,0.01};
		double[] bubbles = {0.2,0.3,0.4,0.5,0.6,0.7,0.8};
		double probability = Math.ceil(fishs[fish-1]*bubbles[bubble-1]*1000);
		int x=(int)(Math.random()*1000);
		if(x<=probability) {
			return true;
		}
		return false;
	}
}
