package mousavi.alex.belugas;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.start);
    	
    	final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(StartScreen.this, BelugasInSpace.class);
            	StartScreen.this.startActivity(myIntent);
            }
        });
	}
	
}
