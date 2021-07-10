package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class DetailActivity2 extends AppCompatActivity {

    public final String PREFERENCE = "com.example.shared";
    public final String key01 = "key01";
    public final String key02 = "key02";
    public final String key03 = "key03";
    public final String key04 = "key04";
    public final String key05 = "key05";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");

        getSupportActionBar().setTitle(name);

        final EditText name2 = (EditText) findViewById(R.id.name);
        final EditText address = (EditText) findViewById(R.id.address);
        final EditText phone = (EditText) findViewById(R.id.phone);
        final EditText family = (EditText) findViewById(R.id.family);
        final EditText animal = (EditText) findViewById(R.id.animal);

        Button button1 = (Button) findViewById(R.id.save_button);
        Button button2 = (Button) findViewById(R.id.load_button);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPreference(key01, name2.getText().toString());
                setPreference(key02, address.getText().toString());
                setPreference(key03, phone.getText().toString());
                setPreference(key04, family.getText().toString());
                setPreference(key05, animal.getText().toString());

                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name2.setText(getPreferenceString(key01));
                address.setText(getPreferenceString(key02));
                phone.setText(getPreferenceString(key03));
                family.setText(getPreferenceString(key04));
                animal.setText(getPreferenceString(key05));

                Toast.makeText(getApplicationContext(),"불러오기 성공",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setPreference(String key, String value) {
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getPreferenceString(String key){
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        return pref.getString(key,"");
    }
}

