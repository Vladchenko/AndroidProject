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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String estimationMark = data.getStringExtra("estimationMark");
                Toast.makeText(getApplicationContext(),
                        "Вы дали оценку " + estimationMark, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
