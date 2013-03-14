package com.example.labyrinthe;

import android.graphics.Color;
import android.graphics.RectF;

public class Ball {

	// Ball's radius
	public static final int RADIUS = 10;
	
	// Max speed
	private static final float MAX_SPEED = 20.0f;
	
	// Compensatory
	private static final float COMPENSATORY = 8.0f;
	
	// Bounce
	private static final float BOUNCE = 1.75f;
		
	// Ball's color
	private int mColor = Color.GREEN;
	
	public int getColor() {
		return mColor;
	}

	// Rectangle which represent the initial position of the ball
	private RectF mInitialRectangle = null;
	
	// Until the initial rectangle we define the position of the ball	
	public void setmInitialRectangle(RectF pInitialRectangle) {
		this.mInitialRectangle = pInitialRectangle;
		this.mX = pInitialRectangle.left + RADIUS;
		this.mY = pInitialRectangle.top + RADIUS;
	}

	// Rectangle to manage the collisions
	private RectF mRectangle = null;
	
	// Coordinate in x
	private float mX;
		
	public float getX() {
		return mX;
	}

	public void setPosX(float pPosX) {
		this.mX = pPosX;
		
		// if the ball go outside of the border, it bounce
		if(mX < RADIUS){
			mX = RADIUS;
			// We change the direction of the ball
			mSpeedY = -mSpeedY / BOUNCE;
		}else if(mX > mWidth - RADIUS){
			mX = mWidth - RADIUS;
			mSpeedY = -mSpeedY / BOUNCE;
		}
	}
	
	// Coordinate in y
	private float mY;
		
	public float getY() {
		return mY;
	}

	public void setPosY(float pPosY) {
		this.mY = pPosY;
		if(mY < RADIUS){
			mY = RADIUS;
			mSpeedX = -mSpeedX / BOUNCE;
		}else if(mY > mHeight - RADIUS){
			mY = RADIUS;
			mSpeedX = -mSpeedX / BOUNCE;
		}
	}

	// Speed on the x axis
	private float mSpeedX = 0;
	
	// Use when we bounce on the horizontal wall
	public void ChangeXSpeed(){
		mSpeedX = -mSpeedX;
	}
	
	// Speed on the y axis
	private float mSpeedY = 0;
	
	// Use when we bounce on the vertical wall
	public void ChangeYSpeed(){
		mSpeedY = -mSpeedY;
	}
	
	// Height of the screen
	private int mHeight = -1;
		
	public void setHeight(int pHeight) {
		this.mHeight = pHeight;
	}
	
	// Width of the screen
	private int mWidth = -1;

	public void setWidth(int pWidth) {
		this.mWidth = pWidth;
	}
	
	//Builder
	public Ball(){
		mRectangle = new RectF();
	}
	
	// Update the coordinate of the ball
	public RectF putXAndY(float pX, float pY){
		
		mSpeedX += pX / COMPENSATORY;
		
		if(mSpeedX > MAX_SPEED)
			mSpeedX = MAX_SPEED;
		if(mSpeedX < -MAX_SPEED)
			mSpeedX = -MAX_SPEED;

		mSpeedY += pY /COMPENSATORY;
		
		if(mSpeedY > MAX_SPEED)
			mSpeedY = MAX_SPEED;
		if(mSpeedY < -MAX_SPEED)
			mSpeedY = -MAX_SPEED;
		
		setPosX(mX + mSpeedY);
		setPosY(mY + mSpeedX);
		
		// Update the rectangle of collision
		mRectangle.set(mX - RADIUS, mY - RADIUS, mX + RADIUS, mY + RADIUS);
		
		return mRectangle;
	}
	
	// Put the ball at the initial position
	public void reset(){
		mSpeedX = 0;
		mSpeedY = 0;
		this.mX = mInitialRectangle.left + RADIUS;
		this.mY = mInitialRectangle.top + RADIUS;
	}
			
}
