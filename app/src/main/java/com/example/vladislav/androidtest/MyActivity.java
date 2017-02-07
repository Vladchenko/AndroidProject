package com.example.vladislav.androidtest;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.vladislav.androidtest.entities.BankDetails;

import java.util.List;

/**
 * Created by vladislav on 05.02.17.
 */

public class MyActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private String estimationMark;

    List<BankDetails> list;

    BanksDetailsOperating banksDetailsOperating = new BanksDetailsOperating();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = banksDetailsOperating.getBanksDetails();
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
//                        System.out.println("Clicked position #" + position);
                        // Invoke an activity with detailed info of a clicked bank
                        Intent intent = new Intent(MyActivity.this,DetailedInfoActivity.class);
                        intent.putExtra("bankDetails", list.get(position));
//                        startActivity(intent);
                        // Getting a result for
                        startActivityForResult(intent, 1);  // 1 is some request code.
                    }
                })
        );
        adapter = new RecyclerViewAdapter(list);
        initRecyclerView();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                estimationMark = data.getStringExtra("estimationMark");
                System.out.println("estimationMark = " + estimationMark);
            }
        }
    }

    void initRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

}
