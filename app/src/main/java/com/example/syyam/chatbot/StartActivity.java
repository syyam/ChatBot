package com.example.syyam.chatbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Button regBtn;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        regBtn = (Button) findViewById(R.id.regBtn);
        loginBtn=(Button) findViewById(R.id.signInBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regA=new Intent(StartActivity.this,registerActivity.class);
                startActivity(regA);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logA=new Intent(StartActivity.this,loginActivity.class);
                startActivity(logA);
            }
        });

    }
}
