package com.example.labyrinthe;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Menu;

public class MainActivity extends Activity {

	public static final int VICTORY_DIALOG = 0;
	public static final int DEFEAT_DIALOG = 1;
	
	// Graphical engine of the game
	private LabyrintheGraphicalEngine mGraphicalEngine = null;
	
	// Physical engine of the game
	private LabyrinthePhysicalEngine mPhysicalEngine = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mGraphicalEngine = new LabyrintheGraphicalEngine(this);
		setContentView(mGraphicalEngine);
		
		mPhysicalEngine = new LabyrinthePhysicalEngine(this);
		
		Ball b = new Ball();
		mGraphicalEngine.setmBall(b);
		mPhysicalEngine.setBall(b);
		
		List<Bloc> mList = mPhysicalEngine.buildLabyrinthe();
		mGraphicalEngine.setmBlocks(mList);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		mPhysicalEngine.resume();
	}

	@Override
	protected void onPause() {
		super.onStop();
		mPhysicalEngine.stop();
	}
	
	@Override
	@Deprecated
	    public Dialog onCreateDialog (int id) {
	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        switch(id) {
	        case VICTORY_DIALOG:
	            builder.setCancelable(false)
	            .setMessage("Bravo, vous avez gagné !")
	            .setTitle("Champion ! Le roi des Zörglubienotchs est mort grâce à vous !")
	            .setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
	                @Override
	                public void onClick(DialogInterface dialog, int which) {
	                    // L'utilisateur peut recommencer s'il le veut
	                	mPhysicalEngine.reset();
	                	mPhysicalEngine.resume();
	                }
	            });
	            break;
	 
	        case DEFEAT_DIALOG:
	            builder.setCancelable(false)
	            .setMessage("La Terre a été détruite à cause de vos erreurs.")
	            .setTitle("Bah bravo !")
	            .setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
	                @Override
	                public void onClick(DialogInterface dialog, int which) {
	                	mPhysicalEngine.reset();
	                	mPhysicalEngine.resume();
	                }
	            });
	        }
	        return builder.create();
	    }
	 
	    @Override
	    public void onPrepareDialog (int id, Dialog box) {
	        // A chaque fois qu'une boîte de dialogue est lancée, on arrête le moteur physique
	        mPhysicalEngine.stop();
	    }

}
