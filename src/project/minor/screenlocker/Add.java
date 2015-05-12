package project.minor.screenlocker;

import java.io.OutputStreamWriter;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends Activity {

	EditText name, pass, conf;
	Button submit;
	String pwd, confirm, acc_name, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		submit = (Button) findViewById(R.id.bSubmit);
		name = (EditText) findViewById(R.id.textTitle);
		pass = (EditText) findViewById(R.id.textPass);
		conf = (EditText) findViewById(R.id.textConf);

		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pwd = pass.getText().toString();
				confirm = conf.getText().toString();
				acc_name= name.getText().toString();
				if (name.length() == 0) {
					name.setError("Field cannot be left empty!");
					return;
				}
				if (pass.length() == 0) {
					pass.setError("Field cannot be left empty!");
					return;
				}
				if (conf.length() == 0) {
					conf.setError("Field cannot be left empty!");
					return;
				}
				if (confirm.contentEquals(pwd)) {
					password = confirm;
				} else {
					conf.setError("Mismatch!!");
					pass.setError("Mismatch!!");
					return;
				}
				SaveText();
				alert();
			}

		});
	}

	void alert()
	{
		AlertDialog builder = new AlertDialog.Builder(Add.this)
	    .setTitle("Continue")
	    .setMessage("Do You Wish To Continue")

	    .setPositiveButton("Add Accounts", new DialogInterface.OnClickListener() {

	        public void onClick(DialogInterface dialog, int which) {                            
	        	Intent starthome = new Intent("project.minor.screenlocker.ADD");
				startActivity(starthome);

	        }
	    })

	    .setNegativeButton("Done", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	        	//Intent start = new Intent("project.minor.screenlocker.WORK");
				//startActivity(start);
	        	finish();
	        }
	    })
	    .show(); 
	}
	private void SaveText() {
		// TODO Auto-generated method stub
		try {

			// open myfilename.txt for writing
			OutputStreamWriter out = new OutputStreamWriter(openFileOutput(
					"myaccounts.txt", MODE_APPEND));
			// write the contents to the file
			out.write(acc_name);
			out.write('\n');
			out.write(password);
			out.write('\n');

			// close the file

			out.close();

			Toast.makeText(this, "Details Saved !", Toast.LENGTH_SHORT).show();
		}

		catch (java.io.IOException e) {

			// do something if an IOException occurs.
			Toast.makeText(this, "Sorry Text couldn't be added",
					Toast.LENGTH_LONG).show();

		}
	}
	 @Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			finish();
		}
	
}
