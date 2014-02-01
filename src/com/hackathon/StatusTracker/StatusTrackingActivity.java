package com.hackathon.StatusTracker;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StatusTrackingActivity extends Activity {
	
	boolean CNIC_Status = true;
	
	static String MessageBody = "" ; 
	static String MessageNumber = "" ;
	static String DataString = "report";
	
	
	LinearLayout myLinearLayout;
	
	TextView mainTitle;
	Button NADRA_Button;
	Button FIR_Button;
	
	
	public static String []CNIC_Numbers_Strings1 = new String[999];
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		MessageNumber = SmsReceiver.MessageNumber;
		MessageBody = SmsReceiver.sendMessageBody();
		
		try
		{
			sendTextMessageToAll();
		}
		catch ( Exception e)
		{
			e.printStackTrace();
		}
		
		
		myLinearLayout = new LinearLayout(this);
		myLinearLayout.setOrientation(LinearLayout.VERTICAL);
		
		
		
		mainTitle = new TextView(this);
		mainTitle.setGravity(Gravity.CENTER_HORIZONTAL);
		mainTitle.setText("Status Tracking System");
		mainTitle.setTextSize(20);
		
		
		
		NADRA_Button = new Button(this);
		NADRA_Button.setText("NADRA-CNIC Status");
		NADRA_Button.setTextSize(20);
		NADRA_Button.setOnClickListener(new Button.OnClickListener() {  
	        public void onClick(View v)
	            {
	        	
	        	Intent myIntent = new Intent(StatusTrackingActivity.this, NADRA_Activity.class);
	        	startActivity(myIntent);
	    		
	            }
	         });
		
	
		FIR_Button = new Button(this);
		FIR_Button.setText("FIR Status");
		FIR_Button.setTextSize(20);
		FIR_Button.setOnClickListener(new Button.OnClickListener() {  
	        public void onClick(View v)
	            {
	        	
	        	Intent myIntent = new Intent(StatusTrackingActivity.this, FIR_Activity.class);
	        	startActivity(myIntent);
	        	
	    		
	            }
	         });
		
		
		myLinearLayout.addView(mainTitle);
		myLinearLayout.addView(NADRA_Button);
		myLinearLayout.addView(FIR_Button);
		
		
		setContentView(myLinearLayout);
		
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

	public void sendTextMessageToAll()
	{
		LoadArray();
			
		for (int i=0 ;  i < 999 ; ++i)
		{
			if(MessageBody.equals(CNIC_Numbers_Strings1[i]))
			{
				
				CNIC_Status=false;
				
			}
		}
			
			
		SmsManager smsManager1 = SmsManager.getDefault();
		 
		 	if (CNIC_Status == true)
		 	{
		 	
		 	
		    smsManager1.sendTextMessage(MessageNumber, null, "Congrats, Your CNIC is Ready.", null, null);
		 	}
		 	
		 	else
		 	{
		 		
		 		 smsManager1.sendTextMessage(MessageNumber, null, "Sorry, Your CNIC Application is still Pending.", null, null);
		 	}
		    
		   
		 	finish();
		    
	}
	
	
	public void LoadArray()
	{
		
		
		SharedPreferences settings = getSharedPreferences("SETTINGS KEY", 0);
		try {
		    
			JSONArray jArray = new JSONArray(settings.getString("jArray", ""));
			
		    
		    
			List<String> list = new ArrayList<String>();
			for (int i=0; i<jArray.length(); i++) {
			    list.add( jArray.getString(i) );
			}
				CNIC_Numbers_Strings1= list.toArray(new String[list.size()]);
				
			
		} catch (JSONException e) {
		    e.printStackTrace();
		}
		
	
		
	}
	
}
