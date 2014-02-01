package com.hackathon.StatusTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NADRA_Activity extends Activity {


	LinearLayout myLinearLayout;

	TextView textTitle;
	Button PendingList_button;
	Button CompletedList_button;



@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	myLinearLayout = new LinearLayout(this);
	myLinearLayout.setOrientation(LinearLayout.VERTICAL);
	
	textTitle = new TextView(this);
	textTitle.setGravity(Gravity.CENTER_HORIZONTAL);
	textTitle.setText("NADRA-CNIC Status System");
	textTitle.setTextSize(20);
	
	
	PendingList_button = new Button(this);
	PendingList_button.setText("Pending List");
	PendingList_button.setTextSize(20);
	PendingList_button.setOnClickListener(new Button.OnClickListener() {  
        public void onClick(View v)
        {
    	
    	Intent myIntent = new Intent(NADRA_Activity.this, NADRAPendingActivity.class);
    	startActivity(myIntent);
    	
		
        }
     });
	
	CompletedList_button = new Button(this);
	CompletedList_button.setText("Completed List");
	CompletedList_button.setTextSize(20);
	CompletedList_button.setOnClickListener(new Button.OnClickListener() {  
        public void onClick(View v)
        {
    	
    	Intent myIntent = new Intent(NADRA_Activity.this, NADRACompletedActivity.class);
    	startActivity(myIntent);
    	
		
        }
     });
	
	
	myLinearLayout.addView(textTitle);
	myLinearLayout.addView(PendingList_button);
	myLinearLayout.addView(CompletedList_button);
	
	
	setContentView(myLinearLayout);
	
	
		
}	
	
}
