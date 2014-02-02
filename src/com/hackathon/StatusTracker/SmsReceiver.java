package com.hackathon.StatusTracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
	
	static String MessageBody = "" ; 
	static String MessageNumber = "" ;
	static String DataString = "report";
	
	
	static int SmsLength=0;
	
	int trialCheck=1;
	
	

	
	
	@Override
	    public void onReceive(Context context, Intent intent) 
	    {
	        //---get the SMS message passed in---
	      
	
		
		 MessageBody = "" ; 
		 MessageNumber = "" ;
		 DataString = "report";
		
		 SmsLength=0;
		
	       
		
			  Bundle bundle = intent.getExtras();        
		        SmsMessage[] msgs = null;
		                   
		        if (bundle != null)
		        {
		            //---retrieve the SMS message received---
		            Object[] pdus = (Object[]) bundle.get("pdus");
		            msgs = new SmsMessage[pdus.length];            
		            for (int i=0; i<msgs.length; i++){
		                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
		                
		                
		                MessageBody += msgs[i].getMessageBody().toString();
		                 
		                MessageNumber += msgs[i].getOriginatingAddress().toString();
		                
		                
		                
		                
		            }
		            
		            SmsLength = MessageBody.length();
		            
		     
		          
		            
		          if (SmsLength <= 13)
		            {
		            	
		        	  
		           
		            Intent i = new Intent();
		            i.setClassName("com.hackathon.StatusTracker", "com.hackathon.StatusTracker.StatusTrackingActivity");
		            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		            context.startActivity(i);
		            
		            
		        
		            
		            
		           
		            
		           }
		            
		           
		            
		            
		           
		            	
		            	
		           
		            	
		     
		    		
		        	
		        
		           
		           
		            
		        }
		            
	            
	            
	           
	           
	        
	        
	    }
	
	
	public static String sendMessageBody()
	{
		return MessageBody;
		
	}
	
	
	public static String sendMessageNumber()
	{
		return MessageNumber;
		
	}

	
	
	
}
