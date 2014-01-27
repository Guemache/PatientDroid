package patientdroid.activities;

import patientdroid.database.PatientBDD;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
	// Get the object of SmsManager
	final SmsManager sms = SmsManager.getDefault();

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		// Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        try {
            
            if (bundle != null) {
                
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                
                for (int i = 0; i < pdusObj.length; i++) {
                    
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                   
                   if(message.contains("sos")||(message.contains("SOS"))){
                	   // Show Alert
                       int duration = Toast.LENGTH_LONG;
                       Toast toast = Toast.makeText(context, 
                                    "PatientDroid: nouveau message de : "+ senderNum + ", message: " + message, duration+100);
                       toast.show();
                       //passer les arguments à l'Activity Messages
                       
                       Intent intent1 = new Intent(context, Messages.class);  
                       intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
                       String msgArg = senderNum+";"+message;
               		   intent1.putExtra("msgArg", msgArg);
       				   context.startActivity(intent1); 
                       
                     
                     
                   }

                  
                    
                } 
              } 

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
            
        }

	}

}
