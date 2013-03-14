package com.example.labyrinthe;

import android.graphics.RectF;

public class Bloc {

	// Here this is the different kind of bloc
	enum Type{ HOLE, START, END };
	
	// Bloc's size 
	private float SIZE = Ball.RADIUS * 2;
	
	// Bloc's coordinates
	private float mX;
	private float mY;
	
	// Bloc's Type
	private Type mType = null;

	// RectF to manage the collision with block
	private RectF mRectangle = null;
		
	public RectF getRectangle() {
		return mRectangle;
	}

	public Type getType() {
		return mType;
	}
	
	public Bloc(Type pType, int pX, int pY){
		this.mType = pType;
		this.mRectangle = new RectF(pX * SIZE, pY * SIZE, (pX + 1) * SIZE, (pY + 1) * SIZE);
	}
			
}
