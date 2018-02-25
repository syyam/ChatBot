package com.example.syyam.chatbot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ExtraActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    // public static FirebaseDatabase mFirebasedatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        mAuth = FirebaseAuth.getInstance();
        // mFirebasedatabase=FirebaseDatabase.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                    Intent manageA=new Intent(ExtraActivity.this,MainActivity.class);
                    startActivity(manageA);


                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    sendTostart();

                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        /*mtextView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manageA=new Intent(MainActivity.this,manageAccounts.class);
                startActivity(manageA);
            }
        });*/
    }
    private void sendTostart() {
        Intent startA =new Intent(ExtraActivity.this,StartActivity.class);
        startActivity(startA);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }
}

