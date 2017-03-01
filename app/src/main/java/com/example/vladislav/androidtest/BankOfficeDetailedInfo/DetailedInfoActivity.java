package com.example.vladislav.androidtest.BankOfficeDetailedInfo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.beans.BankDetails;

public class DetailedInfoActivity extends AppCompatActivity {

    public static final String EXTRA_BANK = "com.example.vladislav.androidtest.EXTRA_BANK";

    private String mTemp;
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
        // Getting a detailed bank info from an intent got from a FragmentsActivity.
        Parcelable extraBank = (Parcelable) intent.getParcelableExtra(EXTRA_BANK);

        mTemp = ((BankDetails) extraBank).getAddress();
        mTextView = (TextView) findViewById(R.id.address_text_view);
        mTextView.setText(mTemp);

        mTemp = ((BankDetails) extraBank).getDistance();
        mTextView = (TextView) findViewById(R.id.distance_text_view);
        mTextView.setText(mTemp);

        mTemp = ((BankDetails) extraBank).getName();
        mTextView = (TextView) findViewById(R.id.extra_office_text_view);
        mTextView.setText(mTemp);

        mTemp = ((BankDetails) extraBank).getPhoneNumber();
        mTextView = (TextView) findViewById(R.id.telephoneN_text_view);
        mTextView.setText(mTemp);

        mButton = (Button) findViewById(R.id.qualityEstimation_button);

        // add mButton listener
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        mContext);

                // set title
                alertDialogBuilder.setTitle("Пожалуйста оцените качество, работы нашего филиала.");

                // set dialog message
                alertDialogBuilder
                        .setCancelable(true)
                        .setSingleChoiceItems(mEstimationGroup, -1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                dialog.dismiss();// dismiss the alertbox after chose option
                                Intent intent = new Intent();
                                mEstimationMark = (String) mEstimationGroup[item];
                                intent.putExtra("mEstimationMark", mEstimationMark);
                                setResult(RESULT_OK, intent);
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        return getWindow().getDecorView().getRootView();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //
    }
}
