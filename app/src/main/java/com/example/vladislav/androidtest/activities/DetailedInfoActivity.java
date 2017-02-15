package com.example.vladislav.androidtest.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.entities.BankDetails;

public class DetailedInfoActivity extends AppCompatActivity {

    public static final String EXTRA_BANK = "com.example.vladislav.androidtest.EXTRA_BANK";

    private String temp;
    private TextView textView;
    private Button button;
    private final Context context = this;
    private CharSequence[] estimationGroup = {"1", "2", "3", "4", "5"};
    private String estimationMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_info_activity);
        Intent intent = getIntent();
        Parcelable extraBank = (Parcelable) intent.getParcelableExtra(EXTRA_BANK);

        temp = ((BankDetails) extraBank).getAddress();
        textView = (TextView) findViewById(R.id.address_text_view);
        textView.setText(temp);

        temp = ((BankDetails) extraBank).getDistance();
        textView = (TextView) findViewById(R.id.distance_text_view);
        textView.setText(temp);

        temp = ((BankDetails) extraBank).getName();
        textView = (TextView) findViewById(R.id.extra_office_text_view);
        textView.setText(temp);

        temp = ((BankDetails) extraBank).getPhoneNumber();
        textView = (TextView) findViewById(R.id.telephoneN_text_view);
        textView.setText(temp);

        button = (Button) findViewById(R.id.qualityEstimation_button);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Пожалуйста оцените качество, работы нашего филиала.");

                // set dialog message
                alertDialogBuilder
//                        .setMessage("Click yes to exit!")
                        .setCancelable(true)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // if this button is clicked, close
//                                // current activity
//                                DetailedInfoActivity.this.finish();
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // if this button is clicked, just close
//                                // the dialog box and do nothing
//                                dialog.cancel();
//                            }
//                        })
                        .setSingleChoiceItems(estimationGroup, -1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                dialog.dismiss();// dismiss the alertbox after chose option
                                Intent intent = new Intent();
                                estimationMark = (String)estimationGroup[item];
                                intent.putExtra("estimationMark",estimationMark);
                                setResult(RESULT_OK, intent);
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //
    }
}
