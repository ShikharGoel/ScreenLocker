package project.minor.screenlocker;

import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

class DrawView extends View {
    int height,width;
	Paint paint = new Paint();
	

    public DrawView(Context context,int ht,int wh) {
        super(context);
        height=ht;
        width=wh;
        paint.setColor(Color.WHITE);
    }

    @Override
    public void onDraw(Canvas canvas) {
    	 
            canvas.drawLine(width/3, 0, width/3, height, paint);
            canvas.drawLine(2*width/3, 0, 2*width/3, height, paint);
            canvas.drawLine( 0, height/3, width, height/3, paint);
            canvas.drawLine( 0, 2*height/3, width, 2*height/3, paint);
    }
}
public class SetPass extends Activity{

		DrawView drawView;
	    int height,width,i,temp;
	    int No_of_Touches=0,cell=0,flag=10;
	    Integer[] touch=new Integer[14];
	    int id[]={R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,
	    		R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8,
	    		R.drawable.image9,R.drawable.image10,R.drawable.image11,R.drawable.image12,R.drawable.image13,
	    		R.drawable.image14};
	    String [] page={"B","C","D","E","F"};
	    String [] selectedCell={"1","2","3","4","5","6","7","8","9"};
	    public static String password;
	    DisplayMetrics displaymetrics = new DisplayMetrics();
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Toast.makeText(this, "Choose a Password", Toast.LENGTH_LONG).show();
	        password="A";
	        for(i=0;i<13;i++)
	        {
	        	touch[i]=1;
	        }
	        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
	        height = displaymetrics.heightPixels;
	        width = displaymetrics.widthPixels;
	        drawView = new DrawView(this,height,width);
	        drawView.setBackgroundResource(id[0]);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(drawView);    
	    }
	   
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			if(event.getAction() == MotionEvent.ACTION_DOWN) {				
	            No_of_Touches++;
			if(event.getX()>0&&event.getX()<=(width/3)&&event.getY()<=(height/3)&&event.getY()>=0)
			cell=temp=1;
			else if(event.getX()>(width/3)&&event.getX()<=(2*width/3)&&event.getY()<=(height/3)&&event.getY()>=0)
				cell=temp=2;
				else if(event.getX()>(2*width/3)&&event.getX()<=width&&event.getY()<=(height/3)&&event.getY()>=0)
					cell=temp=3;
					else if(event.getX()>0&&event.getX()<=width/3&&event.getY()<=(2*(height)/3)&&event.getY()>(height/3))
						cell=temp=4;
						else if(event.getX()>(width/3)&&event.getX()<=(2*width/3)&&event.getY()<=(2*height/3)&&event.getY()>height/3)
							cell=temp=5;
							else if(event.getX()>(2*width/3)&&event.getX()<=width&&event.getY()<=(2*height/3)&&event.getY()>height/3)
								cell=temp=6;
								else if(event.getX()>0&&event.getX()<=(width/3)&&event.getY()<=(height)&&event.getY()>2*height/3)
									cell=temp=7;
									else if(event.getX()>(width/3)&&event.getX()<=(2*width/3)&&event.getY()>(2*height/3)&&event.getY()<=height)
										cell=temp=8;
										else if(event.getX()>(2*width/3)&&event.getX()<=width&&event.getY()>(2*height/3)&&event.getY()<=height)
											cell=temp=9;
			
			password+=selectedCell[temp-1]+page[No_of_Touches-1];
			if(No_of_Touches==5)
			{
				alert();
			}
			else
				{
					if(touch[cell-1]!=1)
					{   
						cell=flag;
						flag++;
					}
					drawView.setBackgroundResource(id[cell]);
					touch[temp-1]++;
				}
			}
			return super.onTouchEvent(event);
			
			}
		

	void alert()
	{
		AlertDialog builder = new AlertDialog.Builder(SetPass.this)
	    .setTitle("Confirmation")
	    .setMessage("Confirm Your Password")

	    .setPositiveButton("OK", new DialogInterface.OnClickListener() {

	        public void onClick(DialogInterface dialog, int which) {                            
	        	Intent starthome = new Intent("project.minor.screenlocker.CONFIRM");
				startActivity(starthome);

	        }
	    })

	    .setNegativeButton("Reset", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	        	Intent start = new Intent("project.minor.screenlocker.SETPASS");
				startActivity(start);
	        }
	    })
	    .show(); 
	}
	 @Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			No_of_Touches = 0;
			cell = 0;
			flag = 10;
			drawView.setBackgroundResource(id[0]);
			finish();
		}
}
