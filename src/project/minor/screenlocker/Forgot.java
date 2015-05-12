package project.minor.screenlocker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Forgot extends Activity {

	EditText name, pass;
	String username, pwd, name_in_file, pass_in_file;
	boolean matchvar;
	Button sub;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgot);
		name = (EditText) findViewById(R.id.editText1);
		pass = (EditText) findViewById(R.id.editText3);
		sub = (Button) findViewById(R.id.bSubmit);

		sub.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				username = name.getText().toString();
				pwd = pass.getText().toString();
				matchvar = match();
				if (matchvar) {
					Unlock.No_of_try=0;
					Intent start = new Intent("project.minor.screenlocker.SETPASS");
					startActivity(start);
				} else {
					name.setError("Incorrect Name or Password");
					pass.setError("Incorrect Name or Password");
				}
			}
		});
	}

	private boolean match() {
		// TODO Auto-generated method stub
		int lineNo = 0;
		StringBuilder text = new StringBuilder();

		try {
			// open the file for reading we have to surround it with a try

			InputStream instream = openFileInput("myfilename.txt");// open the
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

				while ((line = buffreader.readLine()) != null) {
					// buffered reader reads only one line at a time, hence we
					// give a while loop to read all till the text is null
					lineNo++;
					text.append(line);
					if (lineNo == 1)
						name_in_file = text.toString();
					else 
						pass_in_file = text.toString();
					 
					text.delete(0, text.length());				
				}
				if (name_in_file.contentEquals(username)&&pass_in_file.contentEquals(pwd))
						return true;
				
			}
		}

		// now we have to surround it with a catch statement for exceptions
		catch (IOException e) {
			e.printStackTrace();
		}	
			return false;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
}
