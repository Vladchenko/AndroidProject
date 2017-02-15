package com.example.vladislav.androidtest.activities;


import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.entities.BankDetails;
import com.example.vladislav.androidtest.fragments.BankOfficeListFragment;
import com.example.vladislav.androidtest.fragments.BankOfficeListFragment.BankOfficeCallbacks;
import com.example.vladislav.androidtest.fragments.DetailedInfoFragment;

import java.util.List;

public class FragmentsActivity extends AppCompatActivity implements BankOfficeCallbacks,
        DetailedInfoFragment.OnFragmentInteractionListener {

//    BankOfficeLoaderCallbacks<List<BankDetails>>

    private FragmentManager fragmentManager;
    private String estimationMark;

//    DetailedInfoFragment.OnDataPass dataPasser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_activity);

        fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag("bank_office_list_tag") == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(null);
            transaction.add(R.id.fragment_container, new BankOfficeListFragment(), "bank_office_list_tag").commit();
        }

        // Shows a small window with some info
//        Toast.makeText(this, "BankOfficeListActivity", Toast.LENGTH_SHORT).show();
//        Log.d("Activity created","Activity created");
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        System.out.println("!");
//        // Checks the orientation of the screen
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onBankOfficeSelected(BankDetails bankOffice) {
// TODO: fragmentTransaction replace

        Fragment fragment = new DetailedInfoFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("bankOffice", bankOffice);
        fragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // If the screen is now in portrait mode, we can show the
            // dialog in-line with the list so we don't need this activity.
//            Toast.makeText(this, "Landscape mode entered", Toast.LENGTH_SHORT).show();
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.fragment_container, fragment).commit();

    }

    @Override
    public void onEstimatingBank(String estimationMark) {
        this.estimationMark = estimationMark;

    }

//    This method fires, when user clicks "back" on a previous activity.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (estimationMark != null) {
            Toast.makeText(getApplicationContext(),
                    "Вы дали оценку " + estimationMark, Toast.LENGTH_SHORT).show();
            estimationMark = null;
        }
    }

}