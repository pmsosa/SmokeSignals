package com.konukoii.smokesignals;

/**
 * Created by TransAtlantic on 2/14/2015.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.telephony.gsm.SmsMessage;
import android.os.Bundle;
import android.provider.Telephony.Sms;

public class MainService extends Service{

    //Debuggin' Purpouses
    private final static String TAG="SmokeSignals";


    //Broadcast Reciever
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            SMSRequestManager smsRequestManager = new SMSRequestManager();
            if(action.equals("android.provider.Telephony.SMS_RECEIVED")){
                Log.d(TAG,"Firing up the SMSRequestManager!");
                smsRequestManager.go(context, intent);
            }
        }
    };




    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Congrats! MyService Created", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate");

        //Initialize the SMS: Broadcast_Receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(receiver, filter);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "MyService Stopped", Toast.LENGTH_LONG).show();

        //Destroy the SMS_Broadcast_Receiver
        unregisterReceiver(receiver);

        Log.d(TAG, "onDestroy");

    }

}
