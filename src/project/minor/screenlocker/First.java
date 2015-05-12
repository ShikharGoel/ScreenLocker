package project.minor.screenlocker;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class First extends Activity {

	String check;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.first);
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(2500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if (checkFill()) {
						Intent log = new Intent(
								"project.minor.screenlocker.LOGIN");
						startActivity(log);
					} else if(Unlock.No_of_try<4){
						Intent opt = new Intent(
								"project.minor.screenlocker.UNLOCK");
						startActivity(opt);
					}
					else {
						Intent opt = new Intent(
								"project.minor.screenlocker.FORGOT");
						startActivity(opt);
					}
				}
			}
		};
		timer.start();
	}
	private boolean checkFill() {
		// TODO Auto-generated method stub
		try {
			// open the file for reading we have to surround it with a try

			InputStream instream = openFileInput("drowssap.slr");// open the
																	// text file
																	// for
																	// reading
			// if file the available for reading
			if (instream != null)
				return false;
	
		}
		// now we have to surround it with a catch statement for exceptions
		catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
}
