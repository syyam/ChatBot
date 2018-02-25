    //but i want to do that job happening in trying.java to happpen here(its a tab),did u get that?were is the tab

        package com.example.syyam.chatbot;

    import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


    /**
 * A simple {@link Fragment} subclass.
 */
public class Chat extends Fragment {

            private DatabaseReference myRef;
            private FirebaseAuth mAuth;
            private FirebaseAuth.AuthStateListener mAuthListener;
            private FirebaseDatabase mFirebasedatabase;
            private String userId;
            private String newURL;

    public Chat() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//this is not used
    }

//i am using onCreateView, u can compare "trying.java " class with this one. Are you there?

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,//its onCreateView...over
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_chat, container, false);
        /*TextView textView03 = (TextView) v.findViewById(R.id.textView03);
        textView03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMA=new Intent(getActivity(),trying.class);
                startActivity(intentMA);
            }
        });*/


        final String url="http://olimp.stanusch.com//player-kafelkowy/mvc//eng2_en//?q=";
        final WebView view=(WebView) v.findViewById(R.id.mWebView);

        mAuth = FirebaseAuth.getInstance();
      mFirebasedatabase=FirebaseDatabase.getInstance();

        myRef = mFirebasedatabase.getReference(); //logcat locatedthe reference is null here.....but the code works fine in regular activity
        FirebaseUser user=mAuth.getCurrentUser();//
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
                    //Toast.makeText(getActivity(),"na kr",Toast.LENGTH_LONG).show();
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
                    //Uinfo.setName(ds.child(userId).getValue(userInfo.class).getName());
                    Uinfo.setPhone(ds.child(userId).getValue(userInfo.class).getPhone());


                   // String NAME=Uinfo.getName().toString();
                    String PHONE=Uinfo.getPhone().toString();

                    newURL= url+"MobileNumber|"+"92"+PHONE;
                    //Toast.makeText(getActivity(),newURL,Toast.LENGTH_LONG).show();
                    view.loadUrl(newURL);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        view.getSettings().setJavaScriptEnabled(true);
        return v;
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

