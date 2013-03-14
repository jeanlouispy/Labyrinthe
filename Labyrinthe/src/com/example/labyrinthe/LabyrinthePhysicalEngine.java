package com.example.labyrinthe;

import java.util.ArrayList;
import java.util.List;

import com.example.labyrinthe.Bloc.Type;

import android.app.Service;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class LabyrinthePhysicalEngine {


	private Ball mBall = null;
	
	public Ball getBall() {
		return mBall;
	}

	public void setBall(Ball pBall) {
		this.mBall = pBall;
	}
	
	
	//The labyrinth
	private List<Bloc> mBlocks = null;
	
	private MainActivity mActivity = null;
	
	private SensorManager mManager = null;
	private Sensor mAccelerometer = null;
				
	SensorEventListener mSensorEventListener = new SensorEventListener(){
		
		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {


		}

		@Override
		public void onSensorChanged(SensorEvent event) {

			// Value of the x axis
			float x = event.values[0];

			// Value of the y axis
			float y = event.values[1];

			if(mBall != null){
				
				// We update the coordinate of the ball
				RectF hitbox = mBall.putXAndY(x, y);
				
				// For all the bloc of the labyrinth
				for(Bloc block : mBlocks){
					
					// We create a new rectangle for preserve the which of the bloc
					RectF inter = new RectF(block.getRectangle());
					
					if(inter.intersect(hitbox)){
						//We adapt the action in accordance with the type of the bloc
						switch(block.getType()){
						case HOLE:
							mActivity.showDialog(MainActivity.DEFEAT_DIALOG);
							break;
						case START:
							break;
						case END:
							mActivity.showDialog(MainActivity.VICTORY_DIALOG);
							break;
						}
						
						break;
					}
					
				}
				
			}
		
			
		}

	};

	public LabyrinthePhysicalEngine(MainActivity pView){
		
		mActivity = pView;
		mManager = (SensorManager) mActivity.getBaseContext().getSystemService(Service.SENSOR_SERVICE);
		mAccelerometer = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}
	
	// Reset the position of the ball
	public void reset(){
		mBall.reset();
	}

	// Stop the sensor
	public void stop(){
		mManager.unregisterListener(mSensorEventListener, mAccelerometer);
	}
	
	// Restart the sensor
	public void resume(){
		mManager.registerListener(mSensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);	
	}
	
	// Build the labyrinth
    public List<Bloc> buildLabyrinthe() {
        mBlocks = new ArrayList<Bloc>();
        mBlocks.add(new Bloc(Type.HOLE, 0, 0));
        mBlocks.add(new Bloc(Type.HOLE, 0, 1));
        mBlocks.add(new Bloc(Type.HOLE, 0, 2));
        mBlocks.add(new Bloc(Type.HOLE, 0, 3));
        mBlocks.add(new Bloc(Type.HOLE, 0, 4));
        mBlocks.add(new Bloc(Type.HOLE, 0, 5));
        mBlocks.add(new Bloc(Type.HOLE, 0, 6));
        mBlocks.add(new Bloc(Type.HOLE, 0, 7));
        mBlocks.add(new Bloc(Type.HOLE, 0, 8));
        mBlocks.add(new Bloc(Type.HOLE, 0, 9));
        mBlocks.add(new Bloc(Type.HOLE, 0, 10));
        mBlocks.add(new Bloc(Type.HOLE, 0, 11));
        mBlocks.add(new Bloc(Type.HOLE, 0, 12));
        mBlocks.add(new Bloc(Type.HOLE, 0, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 1, 0));
        mBlocks.add(new Bloc(Type.HOLE, 1, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 2, 0));
        mBlocks.add(new Bloc(Type.HOLE, 2, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 3, 0));
        mBlocks.add(new Bloc(Type.HOLE, 3, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 4, 0));
        mBlocks.add(new Bloc(Type.HOLE, 4, 1));
        mBlocks.add(new Bloc(Type.HOLE, 4, 2));
        mBlocks.add(new Bloc(Type.HOLE, 4, 3));
        mBlocks.add(new Bloc(Type.HOLE, 4, 4));
        mBlocks.add(new Bloc(Type.HOLE, 4, 5));
        mBlocks.add(new Bloc(Type.HOLE, 4, 6));
        mBlocks.add(new Bloc(Type.HOLE, 4, 7));
        mBlocks.add(new Bloc(Type.HOLE, 4, 8));
        mBlocks.add(new Bloc(Type.HOLE, 4, 9));
        mBlocks.add(new Bloc(Type.HOLE, 4, 10));
        mBlocks.add(new Bloc(Type.HOLE, 4, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 5, 0));
        mBlocks.add(new Bloc(Type.HOLE, 5, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 6, 0));
        mBlocks.add(new Bloc(Type.HOLE, 6, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 7, 0));
        mBlocks.add(new Bloc(Type.HOLE, 7, 1));
        mBlocks.add(new Bloc(Type.HOLE, 7, 2));
        mBlocks.add(new Bloc(Type.HOLE, 7, 5));
        mBlocks.add(new Bloc(Type.HOLE, 7, 6));
        mBlocks.add(new Bloc(Type.HOLE, 7, 9));
        mBlocks.add(new Bloc(Type.HOLE, 7, 10));
        mBlocks.add(new Bloc(Type.HOLE, 7, 11));
        mBlocks.add(new Bloc(Type.HOLE, 7, 12));
        mBlocks.add(new Bloc(Type.HOLE, 7, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 8, 0));
        mBlocks.add(new Bloc(Type.HOLE, 8, 5));
        mBlocks.add(new Bloc(Type.HOLE, 8, 9));
        mBlocks.add(new Bloc(Type.HOLE, 8, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 9, 0));
        mBlocks.add(new Bloc(Type.HOLE, 9, 5));
        mBlocks.add(new Bloc(Type.HOLE, 9, 9));
        mBlocks.add(new Bloc(Type.HOLE, 9, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 10, 0));
        mBlocks.add(new Bloc(Type.HOLE, 10, 5));
        mBlocks.add(new Bloc(Type.HOLE, 10, 9));
        mBlocks.add(new Bloc(Type.HOLE, 10, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 11, 0));
        mBlocks.add(new Bloc(Type.HOLE, 11, 5));
        mBlocks.add(new Bloc(Type.HOLE, 11, 9));
        mBlocks.add(new Bloc(Type.HOLE, 11, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 12, 0));
        mBlocks.add(new Bloc(Type.HOLE, 12, 1));
        mBlocks.add(new Bloc(Type.HOLE, 12, 2));
        mBlocks.add(new Bloc(Type.HOLE, 12, 3));
        mBlocks.add(new Bloc(Type.HOLE, 12, 4));
        mBlocks.add(new Bloc(Type.HOLE, 12, 5));
        mBlocks.add(new Bloc(Type.HOLE, 12, 9));
        mBlocks.add(new Bloc(Type.HOLE, 12, 8));
        mBlocks.add(new Bloc(Type.HOLE, 12, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 13, 0));
        mBlocks.add(new Bloc(Type.HOLE, 13, 8));
        mBlocks.add(new Bloc(Type.HOLE, 13, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 14, 0));
        mBlocks.add(new Bloc(Type.HOLE, 14, 8));
        mBlocks.add(new Bloc(Type.HOLE, 14, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 15, 0));
        mBlocks.add(new Bloc(Type.HOLE, 15, 8));
        mBlocks.add(new Bloc(Type.HOLE, 15, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 16, 0));
        mBlocks.add(new Bloc(Type.HOLE, 16, 4));
        mBlocks.add(new Bloc(Type.HOLE, 16, 5));
        mBlocks.add(new Bloc(Type.HOLE, 16, 6));
        mBlocks.add(new Bloc(Type.HOLE, 16, 7));
        mBlocks.add(new Bloc(Type.HOLE, 16, 8));
        mBlocks.add(new Bloc(Type.HOLE, 16, 9));
        mBlocks.add(new Bloc(Type.HOLE, 16, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 17, 0));
        mBlocks.add(new Bloc(Type.HOLE, 17, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 18, 0));
        mBlocks.add(new Bloc(Type.HOLE, 18, 13));
 
        mBlocks.add(new Bloc(Type.HOLE, 19, 0));
        mBlocks.add(new Bloc(Type.HOLE, 19, 1));
        mBlocks.add(new Bloc(Type.HOLE, 19, 2));
        mBlocks.add(new Bloc(Type.HOLE, 19, 3));
        mBlocks.add(new Bloc(Type.HOLE, 19, 4));
        mBlocks.add(new Bloc(Type.HOLE, 19, 5));
        mBlocks.add(new Bloc(Type.HOLE, 19, 6));
        mBlocks.add(new Bloc(Type.HOLE, 19, 7));
        mBlocks.add(new Bloc(Type.HOLE, 19, 8));
        mBlocks.add(new Bloc(Type.HOLE, 19, 9));
        mBlocks.add(new Bloc(Type.HOLE, 19, 10));
        mBlocks.add(new Bloc(Type.HOLE, 19, 11));
        mBlocks.add(new Bloc(Type.HOLE, 19, 12));
        mBlocks.add(new Bloc(Type.HOLE, 19, 13));
 
        Bloc b = new Bloc(Type.START, 2, 2);
        mBall.setmInitialRectangle(new RectF(b.getRectangle()));
        mBlocks.add(b);
 
        mBlocks.add(new Bloc(Type.END, 8, 11));
 
        return mBlocks;
    }
	
}
