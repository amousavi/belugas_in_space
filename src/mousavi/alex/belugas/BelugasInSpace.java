package mousavi.alex.belugas;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class BelugasInSpace extends Activity {
   
	private static final String TAG = BelugasInSpace.class.getSimpleName();

	SurfaceView gamePanel;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    	super.onCreate(savedInstanceState);
        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set our MainGamePanel as the View
        gamePanel = new MainGamePanel(this);
        setContentView(gamePanel);
        Log.d(TAG, "View added");
    }

    @Override
    protected void onPause(){
    	Log.v(TAG, "Pausing...");
    	((MainGamePanel) gamePanel).pauseThreads();
    	((MainGamePanel) gamePanel).stopThreads();
    	super.onPause();
    	
    }
    
    @Override
    protected void onResume(){
    	Log.v(TAG, "Resuming...");
    	//((MainGamePanel) gamePanel).resumeThreads();
    	((MainGamePanel) gamePanel).initThread();
    	super.onResume();
    }
    
	@Override
	protected void onDestroy() {
		Log.v(TAG, "Destroying...");
		//((MainGamePanel) gamePanel).stopThreads();
		gamePanel.setBackgroundResource(0);
		gamePanel = null;
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		Log.v(TAG, "Stopping...");
		
		super.onStop();
	}
}