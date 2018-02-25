//this is the activity which is running webView and its fine ok?
// as u can see everything is same ini this activity and tha chat.java, except for 1 thing.


package com.example.syyam.chatbot;
//this is regular activity, and the code working fine, i think the error is in onCreatView
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class trying extends AppCompatActivity {

    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebasedatabase;
    private String userId;
    private String newURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//here im using Oncreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trying);




        final String url="http://olimp.stanusch.com//player-kafelkowy/mvc//eng2_en//?q=";
        final WebView view=(WebView) findViewById(R.id.mWebView);


        mAuth = FirebaseAuth.getInstance();
        mFirebasedatabase=FirebaseDatabase.getInstance();
        myRef = mFirebasedatabase.getReference();
        FirebaseUser user=mAuth.getCurrentUser();
        userId=user.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Toast.makeText(trying.this,"na kr",Toast.LENGTH_LONG).show();
                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    userInfo Uinfo=new userInfo();
                    Uinfo.setName(ds.child(userId).getValue(userInfo.class).getName());
                    Uinfo.setPhone(ds.child(userId).getValue(userInfo.class).getName());

                    String NAME=Uinfo.getName().toString();
                    String PHONE=Uinfo.getPhone().toString();

                    newURL= url+"MobileNumber|"+PHONE;
                    //Toast.makeText(trying.this,newURL,Toast.LENGTH_LONG).show();
                    view.loadUrl(newURL);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        view.getSettings().setJavaScriptEnabled(true);


    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener!=null)
        {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
