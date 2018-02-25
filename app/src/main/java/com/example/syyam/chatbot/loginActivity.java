package com.example.syyam.chatbot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {

    private TextInputLayout mLoginUser;
    private TextInputLayout mLoginPass;
    private Button mLoginBtn;
    private Toolbar mToolBar;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        mToolBar=(Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProgressDialog=new ProgressDialog(this);
        mLoginUser=(TextInputLayout) findViewById(R.id.logUser);
        mLoginPass=(TextInputLayout) findViewById(R.id.logPass);
        mLoginBtn=(Button) findViewById(R.id.logCreateBtn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=mLoginUser.getEditText().getText().toString();
                String pass=mLoginPass.getEditText().getText().toString();

                if (!TextUtils.isEmpty(user)||!TextUtils.isEmpty(pass))
                {
                    mProgressDialog.setTitle("Logging In");
                    mProgressDialog.setMessage("Please wait.");
                    mProgressDialog.show();
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    loginUser(user,pass);
                }
            }
        });
    }

    private void loginUser(String user, String pass) {

        mAuth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                           // Log.w(TAG, "signInWithEmail:failed", task.getException());

                            mProgressDialog.dismiss();
                            Intent mainA=new Intent(loginActivity.this,MainActivity.class);
                            startActivity(mainA);
                            finish();

                        }
                        else {
                            mProgressDialog.hide();
                            Toast.makeText(loginActivity.this, "There is an error.",Toast.LENGTH_LONG).show();
                        }
                        // ...
                    }
                });

    }
}
