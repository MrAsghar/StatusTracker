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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class StatusTrackingActivity extends Activity {
	
	boolean Status_NADRA = false;
	boolean Status_FIR = false;
	boolean Status_Vehicle = false;
	static boolean SMS=false;
	
	
	static String MessageBody = "" ; 
	static String MessageNumber = "" ;
	static int MessageLength;
	
	
	
    LinearLayout.LayoutParams lparams;
    
    ImageView img;
    LinearLayout.LayoutParams lp;
    
    
		
	
	LinearLayout myLinearLayout;
	
	TextView mainTitle;
	Button NADRA_Button;
	Button FIR_Button;
	Button Vehicle_Button;
	
	LinearLayout rl;
	

	
	
	public static String []CNIC_Numbers_Strings_NADRA = new String[999];
	public static String []CNIC_Numbers_Strings_FIR = new String[999];
	public static String []CNIC_Numbers_Strings_Vehicle = new String[999];
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		MessageNumber = SmsReceiver.MessageNumber;
		MessageBody = SmsReceiver.sendMessageBody();
		MessageLength = SmsReceiver.SmsLength;
		
		lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.gravity= Gravity.CENTER;
		
		img = new ImageView(this);
		img.setBackgroundResource(R.drawable.myicon);
		img.setLayoutParams(lp);
		
		
		
		
		
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
		
		
		Vehicle_Button = new Button(this);
		Vehicle_Button.setText("Vehicle Theft Status");
		Vehicle_Button.setTextSize(20);
		Vehicle_Button.setOnClickListener(new Button.OnClickListener() {  
	        public void onClick(View v)
	            {
	        	
	        	Intent myIntent = new Intent(StatusTrackingActivity.this, VehicleInquiry_Activity.class);
	        	startActivity(myIntent);
	        	
	    		
	            }
	         });
		
		
		
		
		myLinearLayout.addView(mainTitle);
		myLinearLayout.addView(NADRA_Button);
		myLinearLayout.addView(FIR_Button);
		myLinearLayout.addView(Vehicle_Button);
		myLinearLayout.addView(img);
		
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
		
		
		
		
			SmsManager smsManager1 = SmsManager.getDefault();
			
			if (MessageLength == 13)
			{
				LoadNadraData();
				
				
				for (int i=0 ;  i < 999 ; ++i)
				{
					if(MessageBody.equals(CNIC_Numbers_Strings_NADRA[i]))
					{
						
						Status_NADRA=true;
						
					}
				}
				
				
				
				if (Status_NADRA == true)
		 		{
		 	
				
		 	
		 		smsManager1.sendTextMessage(MessageNumber, null, "Congrats, Your CNIC is Ready.", null, null);
		 	
		 		}
		 	
		 		else
		 		{
		 		
		 		 smsManager1.sendTextMessage(MessageNumber, null, "Sorry, Your CNIC Application is still Pending.", null, null);
		 		}
				
				
				
			}
			
			
			
				if (MessageLength == 6)
				{
					
					LoadFIRData();
					
					for (int i=0 ;  i < 999 ; ++i)
					{
						if(MessageBody.equals(CNIC_Numbers_Strings_FIR[i]))
						{
							
							
							Status_FIR=true;
							
							
						}
					}
					
					
					
					
					if (Status_FIR == true)
			 		{
			 	
			 	
			 		smsManager1.sendTextMessage(MessageNumber, null, "The FIR Number you entered is under investigation", null, null);
			 	
			 		}
			 	
			 		else
			 		{
			 		
			 		 smsManager1.sendTextMessage(MessageNumber, null, "The FIR Number you entered has either NO records or still pending.", null, null);
			 		}
					
					
					
				}
				
				if (MessageLength != 13 && MessageLength != 6 && MessageLength < 13 )
				{
					LoadVehicleData();
					
					for (int i=0 ;  i < 999 ; ++i)
					{
						if(MessageBody.equals(CNIC_Numbers_Strings_Vehicle[i]))
						{
							
							Status_Vehicle=true;
							
						}
					}
					
					
					if (Status_Vehicle == true)
			 		{
			 	
			 	
			 		smsManager1.sendTextMessage(MessageNumber, null, "The Vehicle registered at this Number is reported as Stolen. BlackListed Vehicle!", null, null);
			 	
			 		}
			 	
			 		else
			 		{
			 		
			 		 smsManager1.sendTextMessage(MessageNumber, null, "The Vehicle at this Registration Number is reported as Stolen", null, null);
			 		}
					
					
					
				}
			
		   

		    
	}
	
	
	public void LoadNadraData()
	{
		
		
		SharedPreferences settings = getSharedPreferences("SETTINGS KEY", 0);
		try {
		    
			JSONArray jArray = new JSONArray(settings.getString("jArray", ""));
			
		    
		    
			List<String> list = new ArrayList<String>();
			for (int i=0; i<jArray.length(); i++) {
			    list.add( jArray.getString(i) );
			}
				CNIC_Numbers_Strings_NADRA= list.toArray(new String[list.size()]);
				
			
	}    catch (JSONException e) {
		    e.printStackTrace();
	}
		
	
		
	}
	
	public void LoadFIRData()
	{
		
		SharedPreferences settings = getSharedPreferences("SETTINGS KEY FIR", 0);
		try {
		    
			JSONArray jArray = new JSONArray(settings.getString("jArray", ""));
			
		    
		    
			List<String> list = new ArrayList<String>();
			for (int i=0; i<jArray.length(); i++) {
			    list.add( jArray.getString(i) );
			}
				CNIC_Numbers_Strings_FIR= list.toArray(new String[list.size()]);
				
			
		} catch (JSONException e) {
		    e.printStackTrace();
		}
		
		
	}
	
	
	public void LoadVehicleData()
	{
		SharedPreferences settings = getSharedPreferences("SETTINGS KEY VEHICLE", 0);
		try {
		    
			JSONArray jArray = new JSONArray(settings.getString("jArray", ""));
			
		    
		    
			List<String> list = new ArrayList<String>();
			for (int i=0; i<jArray.length(); i++) {
			    list.add( jArray.getString(i) );
			}
				CNIC_Numbers_Strings_Vehicle= list.toArray(new String[list.size()]);
				
			
		} catch (JSONException e) {
		    e.printStackTrace();
		}
		
		
	

	
		
	}


	
	
	
	
	
}
