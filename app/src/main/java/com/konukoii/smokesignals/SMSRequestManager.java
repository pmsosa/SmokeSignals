package com.konukoii.smokesignals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by TransAtlantic on 2/14/2015.
 */



public class SMSRequestManager {

    //Debuggin' Purpouses
    private final static String TAG="SmokeSignals";

    //Void Read from file the messageCue

    // Location
    // Contact Search
    // Calls
    // Battery
    // Ring

    //Void Go
        //Main thing from where everything stems from
    public void go(Context context, Intent intent){
        Toast.makeText(context, "Pana te llego un mensaje!", Toast.LENGTH_LONG).show();
        Log.d(TAG, "New SMS Arrived");
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String msg_from="";
        String msg_body="";
        if (bundle != null) {
            try {
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];
                for (int i = 0; i < msgs.length; i++) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    msg_from = msgs[i].getOriginatingAddress();
                    msg_body = msgs[i].getMessageBody();
                }

                Toast.makeText(context, msg_from, Toast.LENGTH_LONG).show();
                Toast.makeText(context, msg_body, Toast.LENGTH_LONG).show();

                int i = parseSMS(msg_body);

                Toast.makeText(context, "text"+i, Toast.LENGTH_LONG).show();
                Toast.makeText(context, "sh",Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }
    }

    //ParseCmd
    private int parseSMS(String msg_body){
        if (msg_body.equals("//Location")){
            return 1;
        }
        else if (msg_body.equals("//Ring")){
            return 2;
        }
        else if (msg_body.equals("//Contact")){
            return 3;
        }

        return 0;
    }

    //Blacklist or Whitelist Phones

    //Parse String
        //Is it really a request for us or just a random message

    //Respond_to_sms

    //Find Location

}
