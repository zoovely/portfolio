package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DetailActivity3 extends AppCompatActivity {

    private final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1000;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    public final String PREFERENCE = "com.example.shared";
    public final String key02 = "key02";
    public final String key04 = "key04";
    public final String key05 = "key05";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail3);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");

        getSupportActionBar().setTitle(name);

        Button button1 = (Button) findViewById(R.id.call_button);
        Button button2 = (Button) findViewById(R.id.sms_button);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage();
            }
        });
    }

    public void Call() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        } else {
            String Phone = "tel:01075616175";
            Intent intent = new Intent("android.intent.action.CALL", Uri.parse(Phone));
            startActivity(intent);
        }
    }

    public void SendMessage() {
        String Phone = "01075616175";
        String Message = getPreferenceString(key02).concat("에 화재가 발생하였습니다. ").concat(getPreferenceString(key04)).concat("명의 사람과 ").concat(getPreferenceString(key05)).concat("마리의 반려동물이 있습니다.");

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {}
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        else {
            SmsManager SM = SmsManager.getDefault();
            SM.sendTextMessage(Phone, null, Message, null, null);
            Toast.makeText(getApplicationContext(), "신고하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public String getPreferenceString(String key){
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        return pref.getString(key,"");
    }
}