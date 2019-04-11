package com.github.ncdhz.fish.superclass;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.github.ncdhz.fish.RunFishingGame;

public class AllAnimtion extends Actor{
	private Texture walkSheetTexture;
	private TextureRegion[] walkFrames;
	
	/**
	 * 
	 * @param imageName 资源路径
	 * @param x 宽
	 * @param y 高
	 * @param col 能切割的列
	 * @param row 能切割的行
	 */
	public AllAnimtion(String imageName,float x,float y,int col,int row){
		super();
		setX(x);
		setY(y);
        walkSheetTexture = new Texture(imageName);
        int perCellWidth = walkSheetTexture.getWidth() / row;
        int perCellHeight = walkSheetTexture.getHeight() / col;
        setWidth(perCellWidth*RunFishingGame.RATE);
        setHeight(perCellHeight*RunFishingGame.RATE);
        TextureRegion[][] cellRegions = TextureRegion.split(walkSheetTexture, perCellWidth, perCellHeight);
        walkFrames = new TextureRegion[row*col];
        for (int i = 0; i < cellRegions.length; i++) {
        		for(int j=0;j<cellRegions[i].length;j++) {
        			walkFrames[i*row+j] = cellRegions[i][j];
        		}
        }
	}
	public TextureRegion[] getWalkFrames() {
		return walkFrames;
	}
	public void setWalkFrames(TextureRegion[] walkFrames) {
		this.walkFrames = walkFrames;
	}
	
}
