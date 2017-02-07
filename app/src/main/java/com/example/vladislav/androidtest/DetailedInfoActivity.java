package com.example.vladislav.androidtest;

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

import com.example.vladislav.androidtest.entities.BankDetails;

public class DetailedInfoActivity extends AppCompatActivity {

    String temp;
    TextView textView;
    private Button button;
    final Context context = this;

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

        button = (Button) findViewById(R.id.qualityEstimation_button);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Оцените качество, плиз");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Click yes to exit!")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                DetailedInfoActivity.this.finish();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });


    }
}
