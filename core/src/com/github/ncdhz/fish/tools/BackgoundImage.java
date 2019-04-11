package com.github.ncdhz.fish.tools;

import com.badlogic.gdx.graphics.Texture;

import com.github.ncdhz.fish.superclass.Background;

public class BackgoundImage extends Background{
	public BackgoundImage(Texture texture,float x,float y,float width,float height) {
		super(texture);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
}
