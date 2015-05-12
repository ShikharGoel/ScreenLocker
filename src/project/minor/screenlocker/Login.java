package project.minor.screenlocker;

import java.io.OutputStreamWriter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

	EditText title, pass, conf;
	Button submit;
	String pwd, confirm, ttl, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		submit = (Button) findViewById(R.id.bSubmit);
		title = (EditText) findViewById(R.id.textTitle);
		pass = (EditText) findViewById(R.id.textPass);
		conf = (EditText) findViewById(R.id.textConf);

		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ttl= title.getText().toString();
				pwd = pass.getText().toString();
				confirm = conf.getText().toString();
				if (title.length() == 0) {
					title.setError("Field cannot be left empty!");
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
				if (!Confirm.set) {

					Intent starthome = new Intent(
							"project.minor.screenlocker.SETPASS");
					startActivity(starthome);
				} else {
					if(!Work.done){
					Intent opt = new Intent("project.minor.screenlocker.WORK");
					startActivity(opt);
					}
					else
						finish();
				}
			}

		});
	}

	private void SaveText() {
		// TODO Auto-generated method stub
		try {

			// open myfilename.txt for writing
			OutputStreamWriter out = new OutputStreamWriter(openFileOutput(
					"myfilename.txt", MODE_PRIVATE));
			// write the contents to the file
			out.write(ttl);
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
