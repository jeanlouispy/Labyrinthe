package com.example.labyrinthe;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LabyrintheGraphicalEngine extends SurfaceView implements
		SurfaceHolder.Callback {

	Ball mBall;

	public Ball getmBall() {
		return mBall;
	}

	public void setmBall(Ball pBall) {
		this.mBall = pBall;
	}

	SurfaceHolder mSurfaceHolder;
	DrawingThread mThread;

	private List<Bloc> mBlocks = null;

	public List<Bloc> getmBlocks() {
		return mBlocks;
	}

	public void setmBlocks(List<Bloc> pBlocks) {
		this.mBlocks = pBlocks;
	}

	Paint mPaint;

	public LabyrintheGraphicalEngine(Context context) {
		super(context);

		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
		mThread = new DrawingThread();

		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.FILL);

		mBall = new Ball();

	}

	@Override
	protected void onDraw(Canvas pCanvas) {

		// We draw the background firth
		pCanvas.drawColor(Color.CYAN);

		if (mBlocks != null) {
			// Drawing all the blocs of the labyrinth
			for (Bloc b : mBlocks) {
				switch (b.getType()) {
				case START:
					mPaint.setColor(Color.WHITE);
					break;
				case END:
					mPaint.setColor(Color.RED);
					break;
				case HOLE:
					mPaint.setColor(Color.BLACK);
					break;
				}
				pCanvas.drawRect(b.getRectangle(), mPaint);
			}
		}
		
		//Drawing the ball
		if(mBall != null){
			mPaint.setColor(mBall.getColor());
			pCanvas.drawCircle(mBall.getX(), mBall.getY(), Ball.RADIUS, mPaint);
		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		
		mThread.keepDrawing = true;
		mThread.start();
		
		//When we create the ball we indicate the coordinate of the screen
		if(mBall != null){
			this.mBall.setHeight(getHeight());
			this.mBall.setWidth(getWidth());
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		mThread.keepDrawing = false;
		boolean retry = true;
		while(retry){
			try{
				mThread.join();
				retry = false;
			}catch(InterruptedException e){}
		}

	}

	
	// Class Thread
	public class DrawingThread extends Thread {

		// use for stop the drawing when we need
		boolean keepDrawing = true;

		public void run() {

			while(keepDrawing){

				Canvas canvas = null;

				try{
					// Getting the canvas for drawing on
					canvas = mSurfaceHolder.lockCanvas();

					// We ensure they are not any other thread can access to the holder (critical section)
					synchronized (mSurfaceHolder){
						onDraw(canvas);
					}

				}finally{
					// Our drawing is finish we freeing the canvas for let draw itself
					if(canvas != null)
						mSurfaceHolder.unlockCanvasAndPost(canvas);
				}

				//For drawing at 50 fsp
				try{
					Thread.sleep(20);
				}catch(InterruptedException e){}
			}

		}

	}
	//
	
}
