package com.example.vladislav.androidtest;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vladislav.androidtest.entities.BankDetails;

public class DetailedInfoActivity extends AppCompatActivity {

    String temp;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_info);
        Intent intent = getIntent();
        Parcelable bankDetails = (Parcelable) intent.getParcelableExtra("bankDetails");

        temp = ((BankDetails) bankDetails).getAddress();
        textView = (TextView)findViewById(R.id.address_text_view);
        textView.setText(temp);

        temp = ((BankDetails) bankDetails).getName();
        TextView textView2;
        textView2 = (TextView)findViewById(R.id.extra_office_text_view);
        textView2.setText(temp);

        temp = ((BankDetails) bankDetails).getPhoneNumber();
        textView = (TextView)findViewById(R.id.telephoneN_text_view);
        textView.setText(temp);
    }
}
