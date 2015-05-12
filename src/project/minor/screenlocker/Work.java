package project.minor.screenlocker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Work extends Activity {

	static Button view,add;
	static boolean done=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.work);
		view = (Button) findViewById(R.id.bView);
		add = (Button) findViewById(R.id.bAdd);
		Unlock.No_of_try=0;
		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent log = new Intent("project.minor.screenlocker.VIEW");
				startActivity(log);
			}
		});
		
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent starthome = new Intent("project.minor.screenlocker.ADD");
				startActivity(starthome);
			}
		});
	}


	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater pop = getMenuInflater();
		pop.inflate(R.menu.options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
			case R.id.aboutUs:
				Intent aboutus = new Intent("project.minor.screenlocker.ABOUTUS");
				startActivity(aboutus);
				break;
			case R.id.chPassword:
				done=true;
				Intent change = new Intent("project.minor.screenlocker.SETPASS");
				startActivity(change);
				break;
			case R.id.details:
				done=true;
				Intent edit = new Intent("project.minor.screenlocker.LOGIN");
				startActivity(edit);
				break;
		}
		return false;
	}




	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		done=false;
	}	
}
