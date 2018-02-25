package com.example.syyam.chatbot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class registerActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    private Toolbar mToolBar;
    private EditText mName;
    private EditText mUser;
    private EditText mPass;
    private EditText mPhone;
    private Button regCreateBtn;
    private ProgressDialog mProgressDialog;
    private FirebaseDatabase database;
    private DatabaseReference mdatabaseR;
    public static TextView textView11;
    private String duplicate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mProgressDialog=new ProgressDialog(this);

        mToolBar=(Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        database = FirebaseDatabase.getInstance();
        mdatabaseR = FirebaseDatabase.getInstance().getReference().child("users");


        //authentication
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out

                }
                // ...
            }
        };

        textView11=(TextView) findViewById(R.id.textView11);
        mName=(EditText) findViewById(R.id.editText3);
        mPhone=(EditText) findViewById(R.id.editText4);
        mUser=(EditText) findViewById(R.id.editText6); //email
        mPass=(EditText) findViewById(R.id.editText2);
        EditText mCountryode=(EditText) findViewById(R.id.editText9);
        mCountryode.setText("+92");

        regCreateBtn=(Button) findViewById(R.id.regCreateBtn);

        regCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=mName.getText().toString();
                String Phone=mPhone.getText().toString();
                String User=mUser.getText().toString();
                String Pass=mPass.getText().toString();
                duplicate=mPass.getText().toString();


                //new JSONTask().execute("http://abdullahahmed-test.apigee.net/account/v1/register/" + Name + "/" + Phone + "/" + User);


                if (!TextUtils.isEmpty(Name)||!TextUtils.isEmpty(User)||!TextUtils.isEmpty(Pass)||!TextUtils.isEmpty(Phone))
                {

                    mProgressDialog.setTitle("Registering User");
                    mProgressDialog.setMessage("Please wait while we create your  account");
                    mProgressDialog.show();
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    createAccount(Name,Phone,User,Pass,duplicate);
                }


            }
        });



    }


    private void createAccount(final String name, final String phone, final String user, String pass, final String duplicate) {

        mAuth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {

                            new JSONTask().execute("http://abdullahahmed-test.apigee.net/account/v1/register/" + name + "/" +"92"+ phone + "/" + user);
                           // DatabaseReference newRef = database.push();
                            String user_id=mAuth.getCurrentUser().getUid();
                            DatabaseReference myRef=mdatabaseR.child(user_id);  

                            //DatabaseReference myRef = database.getReference().child(user_id);
                            myRef.child("name").setValue(name);
                            myRef.child("phone").setValue(phone);
                            myRef.child("ID").setValue(duplicate);

                            mProgressDialog.dismiss();


                            Intent mainA =new Intent(registerActivity.this,MainActivity.class);
                            startActivity(mainA);
                            finish();
                        }

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            mProgressDialog.hide();
                            Toast.makeText(registerActivity.this, "There is an error creating your account.",Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });
    }

    public class JSONTask extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... params) {
            BufferedReader reader = null;
            HttpURLConnection connection = null;
            try {
                URL url=new URL(params[0]);
                connection=(HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream IS=connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(IS));
                StringBuffer buffer=new StringBuffer();
                String line="";
                while ((line= reader.readLine())!=null)
                {
                    buffer.append(line);
                }
                //Toast.makeText(registerActivity.this,line,Toast.LENGTH_LONG).show();
                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection!=null)
                {
                    connection.disconnect();
                }
                try {
                    if (reader!=null)
                    {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(registerActivity.this,s,Toast.LENGTH_LONG).show();
            //textView11.setText(s);
        }
    }


}


