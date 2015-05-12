package project.minor.screenlocker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

class User {
	public String user_name,user_password;
}

public class View extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view);
		ViewText();
		
	}

	public void ViewText() {

		int lineno=0;
		StringBuilder text = new StringBuilder();

		try {
			// open the file for reading we have to surround it with a try

			InputStream instream = openFileInput("myaccounts.txt");// open the
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
                    lineno++;
					text.append(line);
					if(lineno%2==1)
					text.append("\t\t"); // to display the text in text line
					else
						text.append("\n\n");
					
				}
			}
		}

		// now we have to surround it with a catch statement for exceptions
		catch (IOException e) {
			e.printStackTrace();
		}

		// now we assign the text read to the textview
		TextView tv = (TextView) findViewById(R.id.title);
		tv.setText(text);

	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
