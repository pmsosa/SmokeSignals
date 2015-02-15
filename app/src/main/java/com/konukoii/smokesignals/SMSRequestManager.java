package com.konukoii.smokesignals;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import android.telephony.SmsManager;
import java.util.ArrayList;
import android.content.IntentFilter;

/**
 * Created by TransAtlantic on 2/14/2015.
 */



public class SMSRequestManager {

    //Debuggin' Purpouses
    private final static String TAG="SmokeSignals";

    //Void Read from file the messageCue

    private final static int LOCATION = 1;
    private final static int CONTACTSEARCH = 2;
    private final static int MISSEDCALLS = 3;
    private final static int BATTERYLIFE = 4;
    private final static int RING = 5;
    private final static int HELP = 6;

    private final static String HELP_TXT = "TEXT ME:\n'//Location' <- To query GPS coordinates\n" +
                                                    "'//Contacts [name]' <- For contact search\n" +
                                                    "'//Calls' <- To query missed calls\n" +
                                                    "'//Battery' <-To query battery life\n"+
                                                    "'//Ring' <-For phone to start ringing (for 2 Minutes)\n"+
                                                    "'//HELP' <-To display this help menu again\n";


    Context context;    //The context that called this
    Intent intent;      //The intent that called this
    String msg_from;    //Who is the app talking to

    //Void Go
        //Main thing from where everything stems from
    public void go(Context context, Intent intent){
        this.context = context;
        this.intent = intent;

        //Toast.makeText(context, "Pana te llego un mensaje!", Toast.LENGTH_LONG).show();
        Log.d(TAG, "New SMS Arrived");
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        msg_from="";
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

                //Toast.makeText(context, msg_from, Toast.LENGTH_LONG).show();
                //Toast.makeText(context, msg_body, Toast.LENGTH_LONG).show();

                int i = parseSMS(msg_body);
                if (i==HELP){

                    sendSMS(msg_from, HELP_TXT);
                }
                else if (i==BATTERYLIFE){
                    Toast.makeText(context, "Battery?", Toast.LENGTH_LONG).show();
                    QueryBattery();
                }
                //else if

                //Toast.makeText(context, "text"+i, Toast.LENGTH_LONG).show();
                //Toast.makeText(context, "sh",Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }
    }

    //ParseCmd
    private int parseSMS(String msg_body){
        if (msg_body.equals("//Location")){
            return LOCATION;
        }
        else if (msg_body.equals("//Ring")){
            return RING;
        }
        else if (msg_body.equals("//Contact")){
            return CONTACTSEARCH;
        }
        else if (msg_body.equals("//Battery")){
            return BATTERYLIFE;
        }
        else if (msg_body.equals("//Calls")){
            return MISSEDCALLS;
        }
        else if (msg_body.equals("//Help")){
            return HELP;
        }

        return 0;
    }


    //Respond_to_SMS
    private void sendSMS(String phoneNumber, String message)
    {

        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> parts = sms.divideMessage(message);
        sms.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
        //sms.sendTextMessage(phoneNumber, null, message, null, null);
        Log.d(TAG,"message sent!");
    }


    private void QueryBattery(){
        context.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    //Broadcast Receivers Inner Classes////////////////////////////////////////////////////////////
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context arg0, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            sendSMS(msg_from,"Battery Level: "+level+"%");
            Log.d(TAG,"Sent Battery Level");
            context.unregisterReceiver(this);
        }
    };



    //Blacklist or Whitelist Phones

    //Parse String
        //Is it really a request for us or just a random message

    //Respond_to_sms

    //Find Location

}
