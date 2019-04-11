package com.github.ncdhz.fish.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.github.ncdhz.fish.RunFishingGame;
import com.github.ncdhz.fish.superclass.AllAnimtion;

public class Score extends AllAnimtion{
	TextureRegion[] walkFrames;
	private Integer fraction;
	public Score(float x,float y,int num)  {
		super("coinText.png", x, y, 1, 11);
		walkFrames = getWalkFrames();
		this.fraction = num;
	}
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(getScaleX()==0&&getScaleY()==0) {
			remove();
			return;
		}
		int f =fraction;
		batch.draw(walkFrames[10], getX(), getY(),25*RunFishingGame.RATE,40*RunFishingGame.RATE);
		List<Integer> list = new ArrayList<Integer>();  
		while (f!=0) {
			list.add(f%10);
			f = f/10;
		}
        Collections.reverse(list); 
        for(int i=1;i<=list.size();i++) {
        		batch.draw(walkFrames[list.get(i-1)], getX()+i*30*RunFishingGame.RATE, getY(),25*RunFishingGame.RATE,40*RunFishingGame.RATE);
        }
	}
}
