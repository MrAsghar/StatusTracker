package com.hackathon.StatusTracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class FIR_Activity  extends Activity {
	
	public static final String PREFS_NAME = "MyPrefsFileFIR";
	
	static boolean CNIC_status=true;
	
	
	
	LinearLayout myLinearLayout;
	ScrollView sV;
	
	
	TextView textMessage;
	TextView []CNIC_Numbers = new TextView[999];
	public static String []CNIC_Numbers_Strings = new String[999];
	
	Button addCNIC;
	EditText CNIC_Field;
	
	int counter=0;
	float RealCounter=0;
	
	ScrollView scrollView;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		
		LoadArray();
		LoadInt();
			
		scrollView = new ScrollView(this);

		
		myLinearLayout = new LinearLayout(this);
		myLinearLayout.setOrientation(LinearLayout.VERTICAL);
		
		scrollView.addView(myLinearLayout);
		
		
		textMessage = new TextView(this);
		textMessage.setText("FIRs - Under Investigations");
		textMessage.setGravity(Gravity.CENTER_HORIZONTAL);
		textMessage.setTextSize(20);
		
		
		
		CNIC_Field = new EditText(this);
		CNIC_Field.setGravity(Gravity.CENTER_HORIZONTAL);
		CNIC_Field.setHint("Enter FIR No");
		
		for (int i=0 ; i < 999 ; ++i)
		{
		CNIC_Numbers[i] = new TextView(this);
		CNIC_Numbers[i].setGravity(Gravity.CENTER_HORIZONTAL);
		CNIC_Numbers[i].setTextSize(20);
		}
		
	
		
		
		
		
		addCNIC = new Button(this);
		addCNIC.setText("Add FIR No");
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
		
		
		
		
		setContentView(scrollView);
		
			
	}	
	
	
	
	public void SaveArray () {   
	   	

	
		SharedPreferences settings = getSharedPreferences("SETTINGS KEY FIR", 0);
		SharedPreferences.Editor editor = settings.edit();
		
		JSONArray jArray = new JSONArray(Arrays.asList(CNIC_Numbers_Strings));	
		editor.putString("jArray", jArray.toString());
	
		editor.commit();
		
	
	    
	}

	public void LoadArray()
	{
		
		
		SharedPreferences settings = getSharedPreferences("SETTINGS KEY FIR", 0);
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
	      editor.putFloat("valueFIR", RealCounter);

	      // Commit the edits!
	      editor.commit();
	}
	
	public void LoadInt()
	{
	       SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	       RealCounter = settings.getFloat("valueFIR", RealCounter);
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
		
		LoadArray();
		LoadInt();
		
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		LoadArray();
		LoadInt();
		
		
	}



	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		SaveArray();
		SaveInt();
		
		
	}
	
	
	
	
	
	
	

}
