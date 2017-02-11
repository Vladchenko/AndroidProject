package com.example.vladislav.androidtest.activities;


import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.entities.BankDetails;
import com.example.vladislav.androidtest.fragments.BankOfficeListFragment;
import com.example.vladislav.androidtest.fragments.DetailedInfoFragment;

public class FragmentsActivityPortrait extends AppCompatActivity implements BankOfficeListFragment.BankOfficeCallbacks {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_portrait);

        fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag("bank_office_list_tag") == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(null);
            transaction.add(R.id.fragment_container, new BankOfficeListFragment(),"bank_office_list_tag").commit();
        }
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
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container,fragment).commit();

    }
}