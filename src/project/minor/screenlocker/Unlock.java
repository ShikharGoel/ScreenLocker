package project.minor.screenlocker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Unlock extends Activity {

	String check;
	DrawView drawView;
	int height, width, i, temp;
	int No_of_Touches = 0, cell = 0, flag = 10;
	public static int No_of_try=0;
	Integer[] touch = new Integer[14];
	int id[] = { R.drawable.image1, R.drawable.image2, R.drawable.image3,
			R.drawable.image4, R.drawable.image5, R.drawable.image6,
			R.drawable.image7, R.drawable.image8, R.drawable.image9,
			R.drawable.image10, R.drawable.image11, R.drawable.image12,
			R.drawable.image13, R.drawable.image14 };
	String[] page = { "B", "C", "D", "E", "F" };
	String[] selectedCell = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	public static String tryPass = "A";
	DisplayMetrics displaymetrics = new DisplayMetrics();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		for (i = 0; i < 13; i++) {
			touch[i] = 1;
		}
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		height = displaymetrics.heightPixels;
		width = displaymetrics.widthPixels;
		drawView = new DrawView(this, height, width);
		drawView.setBackgroundResource(id[0]);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(drawView);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			No_of_Touches++;
			if (event.getX() > 0 && event.getX() <= (width / 3)
					&& event.getY() <= (height / 3) && event.getY() >= 0)
				cell = temp = 1;
			else if (event.getX() > (width / 3)
					&& event.getX() <= (2 * width / 3)
					&& event.getY() <= (height / 3) && event.getY() >= 0)
				cell = temp = 2;
			else if (event.getX() > (2 * width / 3) && event.getX() <= width
					&& event.getY() <= (height / 3) && event.getY() >= 0)
				cell = temp = 3;
			else if (event.getX() > 0 && event.getX() <= width / 3
					&& event.getY() <= (2 * (height) / 3)
					&& event.getY() > (height / 3))
				cell = temp = 4;
			else if (event.getX() > (width / 3)
					&& event.getX() <= (2 * width / 3)
					&& event.getY() <= (2 * height / 3)
					&& event.getY() > height / 3)
				cell = temp = 5;
			else if (event.getX() > (2 * width / 3) && event.getX() <= width
					&& event.getY() <= (2 * height / 3)
					&& event.getY() > height / 3)
				cell = temp = 6;
			else if (event.getX() > 0 && event.getX() <= (width / 3)
					&& event.getY() <= (height)
					&& event.getY() > 2 * height / 3)
				cell = temp = 7;
			else if (event.getX() > (width / 3)
					&& event.getX() <= (2 * width / 3)
					&& event.getY() > (2 * height / 3)
					&& event.getY() <= height)
				cell = temp = 8;
			else if (event.getX() > (2 * width / 3) && event.getX() <= width
					&& event.getY() > (2 * height / 3)
					&& event.getY() <= height)
				cell = temp = 9;

			tryPass += selectedCell[temp - 1] + page[No_of_Touches - 1];
			if (No_of_Touches == 5) {
				No_of_try++;
				if (match()) {
					Intent work = new Intent("project.minor.screenlocker.WORK");
					startActivity(work);
				   } 
				else if(No_of_try<4)
				    {
					alert();
					}
					else
					{
						Intent forgot = new Intent("project.minor.screenlocker.FORGOT");
						startActivity(forgot);
					}
				
			} else {
				if (touch[cell - 1] != 1) {
					cell = flag;
					flag++;
				}
				drawView.setBackgroundResource(id[cell]);
				touch[temp - 1]++;
			}
		}
		return super.onTouchEvent(event);

	}

	void alert() {
		AlertDialog builder = new AlertDialog.Builder(Unlock.this)
				.setTitle("Confirmation")
				.setMessage("Incorrect Password")

				.setPositiveButton("Forgot Password",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								Intent starthome = new Intent(
										"project.minor.screenlocker.FORGOT");
								startActivity(starthome);

							}
						})

				.setNegativeButton("Retry",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Intent start = new Intent(
										"project.minor.screenlocker.UNLOCK");
								startActivity(start);
							}
						}).show();
	}
	
	private boolean match() {
		// TODO Auto-generated method stub
		StringBuilder text = new StringBuilder();

		try {
			// open the file for reading we have to surround it with a try

			InputStream instream = openFileInput("drowssap.slr");// open the
																	// text file
																	// for
																	// reading

			// if file the available for reading
			if (instream != null) {

				// prepare the file for reading
				InputStreamReader inputreader = new InputStreamReader(instream);
				BufferedReader buffreader = new BufferedReader(inputreader);

				String line = null;
				// We initialize a string "line"

				if((line = buffreader.readLine()) != null) {
					// buffered reader reads only one line at a time, hence we
					// give a while loop to read all till the text is null
					text.append(line);
					
				}
			}
		}

		// now we have to surround it with a catch statement for exceptions
		catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(tryPass.contentEquals(text.toString()))
				return true;
			else
				return false;
		}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		No_of_Touches = 0;
		cell = 0;
		flag = 10;
		tryPass = "A";
		drawView.setBackgroundResource(id[0]);
		finish();
	}

}