package com.example.vladislav.androidtest.activities;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.vladislav.androidtest.BanksDetailsOperating;
import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.RecyclerViewAdapter;
import com.example.vladislav.androidtest.entities.BankDetails;
import com.example.vladislav.androidtest.fragments.BankOfficeListFragment;
import com.example.vladislav.androidtest.fragments.DetailedInfoFragment;
import com.example.vladislav.androidtest.listeners.RecyclerItemClickListener;

import java.util.List;

public class FragmentsActivity extends AppCompatActivity implements BankOfficeListFragment.OnFragmentInteractionListener,
    DetailedInfoFragment.OnFragmentInteractionListener {

    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        fragment = new BankOfficeListFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.activity_fragments, fragment).commit();

        // Add the fragment to the 'fragment_container' FrameLayout
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.activity_fragments, fragment).commit();

//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//        // Replace whatever is in the fragment_container view with this fragment,
//        // and add the transaction to the back stack so the user can navigate back
//        transaction.replace(R.id.activity_fragments, fragment2);
//        transaction.addToBackStack(null);
//
//        // Commit the transaction
//        transaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}