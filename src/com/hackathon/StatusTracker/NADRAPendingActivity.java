package com.hackathon.StatusTracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NADRAPendingActivity extends Activity {
	
	public static final String PREFS_NAME = "MyPrefsFile";
	
	static boolean CNIC_status=true;
	
	
	
	LinearLayout myLinearLayout;
	
	
	TextView textMessage;
	TextView []CNIC_Numbers = new TextView[999];
	public static String []CNIC_Numbers_Strings = new String[999];
	
	Button addCNIC;
	EditText CNIC_Field;
	
	int counter=0;
	float RealCounter=0;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		LoadArray();
		LoadInt();
			
	
		
		
		
		
		
		

		
		myLinearLayout = new LinearLayout(this);
		myLinearLayout.setOrientation(LinearLayout.VERTICAL);
		
		textMessage = new TextView(this);
		textMessage.setText("Sorry, Your CNIC Application is still Pending."+ RealCounter);
		textMessage.setTextSize(10);
		
		
		CNIC_Field = new EditText(this);
		CNIC_Field.setGravity(Gravity.CENTER_HORIZONTAL);
		CNIC_Field.setHint("Enter CNIC Number to Add to List");
		
		for (int i=0 ; i < 999 ; ++i)
		{
		CNIC_Numbers[i] = new TextView(this);
		CNIC_Numbers[i].setGravity(Gravity.CENTER_HORIZONTAL);
		CNIC_Numbers[i].setTextSize(20);
		}
		
	
		
		
		
		
		addCNIC = new Button(this);
		addCNIC.setText("Add CNIC");
		addCNIC.setTextSize(20);
		addCNIC.setOnClickListener(new Button.OnClickListener() {  
	        public void onClick(View v)
	        {	
	        	if ( RealCounter < 999 )
	        	{
	        		counter = (int) RealCounter;
	        		
	        	
	        	CNIC_Numbers_Strings[counter] = CNIC_Field.getText().toString();
	        
	        	
	        		CNIC_Numbers[counter].setText(CNIC_Numbers_Strings[counter]);
	        		myLinearLayout.addView(CNIC_Numbers[counter]);
	        		
	        	
	        		
	        		++counter;
	        		
	        		++RealCounter;
	        		
	        		
	        	}
	        	
	        	
	    	
	    	
			
	        }
	     });
		
		myLinearLayout.addView(textMessage);
		myLinearLayout.addView(CNIC_Field);
		myLinearLayout.addView(addCNIC);
	
		
		if (RealCounter > 0 )
		{
			for ( int i=0 ; i <  RealCounter ; ++i)
		{
				CNIC_Numbers[i].setText(CNIC_Numbers_Strings[i]);
        		myLinearLayout.addView(CNIC_Numbers[i]);
			}
		}
		

		
		setContentView(myLinearLayout);
		
			
	}	
	
	
	
	public void SaveArray () {   
	   	

	
		SharedPreferences settings = getSharedPreferences("SETTINGS KEY", 0);
		SharedPreferences.Editor editor = settings.edit();
		
		JSONArray jArray = new JSONArray(Arrays.asList(CNIC_Numbers_Strings));	
		editor.putString("jArray", jArray.toString());
	
		editor.commit();
		
	
	    
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
				CNIC_Numbers_Strings= list.toArray(new String[list.size()]);
				
			
		} catch (JSONException e) {
		    e.printStackTrace();
		}
		
	
		
	}
	
	
	public void SaveInt()
	{
	      SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	      SharedPreferences.Editor editor = settings.edit();
	      editor.putFloat("value", RealCounter);

	      // Commit the edits!
	      editor.commit();
	}
	
	public void LoadInt()
	{
	       SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	       RealCounter = settings.getFloat("value", RealCounter);
	}
	
	


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		SaveArray();
		SaveInt();
		
	}



	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		SaveArray();
		SaveInt();
		
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		LoadArray();
		LoadInt();
		
		
	}
	
	
	
	
	

}
