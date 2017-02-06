package com.example.vladislav.androidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

//    private RecyclerView recyclerViewer;

    public MainActivity() {
        BanksDetailsOperating banksDetailsOperating = new BanksDetailsOperating();
        banksDetailsOperating.printBanksDetailsByDefault();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
    }
}
