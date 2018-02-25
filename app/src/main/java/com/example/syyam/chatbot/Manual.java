package com.example.syyam.chatbot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Manual extends Fragment {



    public Manual() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_manual, container, false);

        View v= inflater.inflate(R.layout.fragment_manual, container, false);
        TextView mtextView01 = (TextView) v.findViewById(R.id.textView01);
        TextView mtextView02 = (TextView) v.findViewById(R.id.textView02);

        mtextView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMA=new Intent(getActivity(),manageAccounts.class);
                startActivity(intentMA);
            }
        });
        mtextView02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentB=new Intent(getActivity(),benificiary.class);
                startActivity(intentB);
            }
        });
        return v;


    }

}
