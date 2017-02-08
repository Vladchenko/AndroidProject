package com.example.vladislav.androidtest.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.vladislav.androidtest.BanksDetailsOperating;
import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.RecyclerViewAdapter;
import com.example.vladislav.androidtest.entities.BankDetails;
import com.example.vladislav.androidtest.listeners.RecyclerItemClickListener;

import java.util.List;

/**
 * Created by vladislav on 05.02.17.
 */

public class BankOfficeListActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private String estimationMark;
    private List<BankDetails> list;

    BanksDetailsOperating banksDetailsOperating = new BanksDetailsOperating();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = banksDetailsOperating.getBanksDetails();
        setContentView(R.layout.bank_office_list_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // Invoke an activity with detailed info of a clicked bank
                        Intent intent = new Intent(BankOfficeListActivity.this,DetailedInfoActivity.class);
                        intent.putExtra(DetailedInfoActivity.EXTRA_BANK, list.get(position));
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
