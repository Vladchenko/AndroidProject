package com.example.vladislav.androidtest.BanksOfficesList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.vladislav.androidtest.datasource.BanksDetailsOperating;
import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.datasource.RecyclerViewAdapter;

/**
 * Created by vladislav on 05.02.17.
 */

public class BankOfficeListActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    BanksDetailsOperating banksDetailsOperating = new BanksDetailsOperating();

    public BankOfficeListActivity() {

    }

    public BankOfficeListActivity(BanksDetailsOperating banksDetailsOperating ) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bank_office_list_activity);
//        mList = banksDetailsOperating.getmBanksDetails();
//        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
//        mRecyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
//                        // Invoke an activity with detailed info of a clicked bank
//                        Intent intent = new Intent(BankOfficeListActivity.this,DetailedInfoActivity.class);
//                        intent.putExtra(DetailedInfoActivity.EXTRA_BANK, mList.get(position));
////                        startActivity(intent);
//                        // Getting a result for
//                        startActivityForResult(intent, 1);  // 1 is some request code.
//                    }
//                })
//        );
//        mAdapter = new RecyclerViewAdapter(mList);
//        initRecyclerView();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String estimationMark = data.getStringExtra("estimationMark");
//                System.out.println("estimationMark = " + estimationMark);
                Toast.makeText(getApplicationContext(),
                        "Вы дали оценку " + estimationMark, Toast.LENGTH_SHORT).show();

            }
        }
    }

    void initRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

}
