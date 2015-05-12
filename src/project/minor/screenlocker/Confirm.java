package project.minor.screenlocker;

import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Confirm extends Activity {
	DrawView drawView;
	int height, width, i, temp;
	int No_of_Touches = 0, cell = 0, flag = 10;
	Integer[] touch = new Integer[14];
	int id[] = { R.drawable.image1, R.drawable.image2, R.drawable.image3,
			R.drawable.image4, R.drawable.image5, R.drawable.image6,
			R.drawable.image7, R.drawable.image8, R.drawable.image9,
			R.drawable.image10, R.drawable.image11, R.drawable.image12,
			R.drawable.image13, R.drawable.image14 };
	String[] page = { "B", "C", "D", "E", "F" };
	String[] selectedCell = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	public static String confirmPassword = "A";
	public static boolean set = false;
	DisplayMetrics displaymetrics = new DisplayMetrics();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		set = false;
		for (i = 0; i < 13; i++) {
			touch[i] = 1;
		}
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		height = displaymetrics.heightPixels;
		width = displaymetrics.widthPixels;
		drawView = new DrawView(this, height, width);
		drawView.setBackgroundResource(id[0]);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
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

			confirmPassword += selectedCell[temp - 1] + page[No_of_Touches - 1];
			if (No_of_Touches == 5) {
				if (confirmPassword.contentEquals(SetPass.password)) {
					set = true;
					SaveText();
					if(!Work.done){
					Intent startwork = new Intent(
							"project.minor.screenlocker.WORK");
					startActivity(startwork);
					}
					else
						finish();
				} else {
					
					Toast.makeText(this, "Mismatch!! Enter Again!!",
							Toast.LENGTH_LONG).show();
					Intent starthome = new Intent(
							"project.minor.screenlocker.SETPASS");
					startActivity(starthome);
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
	private void SaveText() {
		// TODO Auto-generated method stub
		try {

			// open myfilename.txt for writing
			OutputStreamWriter out = new OutputStreamWriter(openFileOutput(
					"drowssap.slr", MODE_PRIVATE));
			// write the contents to the file
			out.write(confirmPassword);
			out.write('\n');

			// close the file

			out.close();

			Toast.makeText(this, "Password Confirmed !", Toast.LENGTH_SHORT).show();
		}

		catch (java.io.IOException e) {

			// do something if an IOException occurs.
			Toast.makeText(this, "Sorry Text could't be added",
					Toast.LENGTH_LONG).show();

		}
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		No_of_Touches = 0;
		cell = 0;
		flag = 10;
		confirmPassword="A";
		drawView.setBackgroundResource(id[0]);
		finish();

	}

}
