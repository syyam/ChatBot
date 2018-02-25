package com.example.syyam.chatbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class manageAccounts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_accounts);

        final String[] stuffs={"Bill Payment Day","Pay Day"};
        ListAdapter mListA=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,stuffs           );
        ListView mListView= (ListView) findViewById(R.id.mListView);
        mListView.setAdapter(mListA);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String stuff=String.valueOf(parent.getItemAtPosition(position));

                if(stuff=="Bill Payment Day")
                {
                    Intent BillA=new Intent(manageAccounts.this,PushNotification.class);
                    BillA.putExtra("string",stuffs);
                    startActivity(BillA);
                }
                if (stuff=="Pay Day")
                {
                    //Intent BillA=new Intent(manageAccounts.this,trying.class);
                    //BillA.putExtra("string",stuffs);
                    //startActivity(BillA);
                }
            }
        });
    }
}
