package com.example.vladislav.androidtest;


import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vladislav.androidtest.beans.BankDetails;
import com.example.vladislav.androidtest.BanksOfficesList.BankOfficeListFragment;
import com.example.vladislav.androidtest.BanksOfficesList.BankOfficeListFragment.BankOfficeCallbacks;
import com.example.vladislav.androidtest.BankOfficeDetailedInfo.DetailedInfoFragment;

public class FragmentsActivity extends AppCompatActivity implements BankOfficeCallbacks,
        DetailedInfoFragment.OnFragmentInteractionListener {

    private FragmentManager mFragmentManager;
    private String mEstimationMark;
    private TextView emptyTextView;
//    DetailedInfoFragment.OnDataPass dataPasser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_activity);


        emptyTextView = (TextView) findViewById(R.id.emptyText);


        mFragmentManager = getSupportFragmentManager();
        if (mFragmentManager.findFragmentByTag("bank_office_list_tag") == null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
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

    /**
     * Method is launched when a bank's office list's element selected.
     * bankOffice bean is put into a bundle and passed into a fragment container.
     **/
    @Override
    public void onBankOfficeSelected(BankDetails bankOffice) {
// TODO: fragmentTransaction replace

        Fragment fragment = new DetailedInfoFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("bankOffice", bankOffice);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // If the screen is now in portrait mode, we can show the
            // dialog in-line with the list so we don't need this activity.
//            Toast.makeText(this, "Landscape mode entered", Toast.LENGTH_SHORT).show();
            transaction.addToBackStack(null);
            if(mFragmentManager.getBackStackEntryCount() > 2) {
                mFragmentManager.popBackStack(); // remove one (you can also remove more)
            }
            transaction.replace(R.id.fragment_container, fragment).commit();
        } else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            emptyTextView.setVisibility(View.GONE);
//            transaction.addToBackStack("teststack");
            transaction.replace(R.id.rightContainer, fragment).commit();
        }


    }

    /**
     * When providing a feedback on a bank's office work, we put a received estimation mark and put
     * it to a field of this class.
     * @param estimationMark
     */
    @Override
    public void onEstimatingBank(String estimationMark) {
        this.mEstimationMark = estimationMark;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
       // mFragmentManager.popBackStack("teststack", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            emptyTextView.setVisibility(View.GONE);
            System.out.println(getFragmentManager().getBackStackEntryAt(0).getName());
        }
    }

    /**
     * This method fires, when user clicks "back" on a previous activity.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int count = mFragmentManager.getBackStackEntryCount();
//        if (count == 0){
//            finish();
//        }

        if (mEstimationMark != null) {
            Toast.makeText(getApplicationContext(),
                    "Вы дали оценку " + mEstimationMark, Toast.LENGTH_SHORT).show();
            mEstimationMark = null;
        }
    }

}