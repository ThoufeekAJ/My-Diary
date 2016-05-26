package com.example.mydiary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText e1;
	Button b1,b2;
	TextView t1;
	String data;
	private String file="mydata";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		t1=(TextView) findViewById(R.id.textView2);
		Calendar c=Calendar.getInstance();
		int month=c.get(Calendar.MONTH)+1;
		String sdate=month+"-"+c.get(Calendar.DAY_OF_MONTH)+"-"+c.get(Calendar.YEAR);
		t1.setText(sdate);
		b1=(Button) findViewById(R.id.button1);
		b2=(Button) findViewById(R.id.button2);
		e1=(EditText) findViewById(R.id.editText1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				data=e1.getText().toString();
				try{
					FileOutputStream fOut=openFileOutput(file,MODE_WORLD_READABLE);
					fOut.write(data.getBytes());
					fOut.close();
					Toast.makeText(getApplicationContext(),"file saved", Toast.LENGTH_LONG).show();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent email=new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, new String[]{"ramsurya980@gmail.com"});
				email.putExtra(Intent.EXTRA_SUBJECT, "Memories");
				email.putExtra(Intent.EXTRA_TEXT, data);
				email.setType("message/rfc822");
				startActivity(email);
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
