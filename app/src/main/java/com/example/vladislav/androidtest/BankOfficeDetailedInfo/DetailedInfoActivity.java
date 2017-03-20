package com.example.vladislav.androidtest.BankOfficeDetailedInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.beans.BankDetails;
import com.example.vladislav.androidtest.Consts;

public class DetailedInfoActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mButton;
    private final Context mContext = this;
    private CharSequence[] mEstimationGroup = {"1", "2", "3", "4", "5"};
    private String mEstimationMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {

        setContentView(R.layout.detailed_info_activity);
        Intent intent = getIntent();

        // Getting a detailed bank info from an intent got from a BankOfficeListActivity.
        Parcelable extraBank = (Parcelable) intent.getParcelableExtra(Consts.EXTRA_BANK);

        ((TextView) findViewById(R.id.address_text_view)).
                setText(((BankDetails) extraBank).getmAddress());

        ((TextView) findViewById(R.id.distance_text_view)).
                setText(((BankDetails) extraBank).getmDistance());

        ((TextView) findViewById(R.id.extra_office_text_view)).
                setText(((BankDetails) extraBank).getmName());

        ((TextView) findViewById(R.id.telephoneN_text_view)).
                setText(((BankDetails) extraBank).getmPhoneNumber());

        mButton = (Button) findViewById(R.id.qualityEstimation_button);

        return getWindow().getDecorView().getRootView();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
