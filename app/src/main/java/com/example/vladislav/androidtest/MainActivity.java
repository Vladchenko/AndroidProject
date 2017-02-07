package com.example.vladislav.androidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public MainActivity() {
//        BanksDetailsOperating banksDetailsOperating = new BanksDetailsOperating();
//        banksDetailsOperating.printBanksDetailsByDefault();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bank_office_list_activity);
    }
}
