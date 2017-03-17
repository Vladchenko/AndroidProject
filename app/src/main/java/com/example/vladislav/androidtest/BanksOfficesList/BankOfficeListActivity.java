package com.example.vladislav.androidtest.BanksOfficesList;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.beans.BankDetails;
import com.example.vladislav.androidtest.BanksOfficesList.BankOfficeListFragment.BankOfficeCallbacks;
import com.example.vladislav.androidtest.BankOfficeDetailedInfo.DetailedInfoFragment;
import com.example.vladislav.androidtest.Consts;

public class BankOfficeListActivity extends AppCompatActivity implements BankOfficeCallbacks,
        DetailedInfoFragment.OnFragmentInteractionListener {

    private FragmentManager mFragmentManager;
    private String mEstimationMark;
    private TextView emptyTextView;
    private Fragment fragment;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_activity);

        emptyTextView = (TextView) findViewById(R.id.empty_bank_details_text_view);
        mFragmentManager = getSupportFragmentManager();

        if (mFragmentManager.findFragmentByTag(Consts.BANKS_DETAILS_LIST_TAG) == null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.addToBackStack(null);
            transaction.add(R.id.fragment_container, new BankOfficeListFragment(), Consts.BANKS_DETAILS_LIST_TAG).commit();
        }

        mSharedPreferences = this.getSharedPreferences(
                Consts.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);

    }

    /**
     * Method is invoked when a bank's office list's element selected.
     * bankOffice bean is put into a bundle and passed into a fragment container.
     **/
    @Override
    public void onBankOfficeSelected(BankDetails bankOffice) {
// TODO: fragmentTransaction replace

        fragment = new DetailedInfoFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(Consts.BANK_DETAILS_PARCELABLE, bankOffice);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // If the screen is now in portrait mode, we can show the
            // dialog in-line with the list so we don't need this activity.
//            Toast.makeText(this, "Landscape mode entered", Toast.LENGTH_SHORT).show();
            transaction.addToBackStack(null);
            if (mFragmentManager.getBackStackEntryCount() > 2) {
                mFragmentManager.popBackStack(); // remove one (you can also remove more)
            }
            transaction.replace(R.id.fragment_container, fragment).commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            emptyTextView.setVisibility(View.GONE);
//            transaction.addToBackStack("teststack");
            transaction.replace(R.id.bank_details_container, fragment).commit();
        }

    }

    /**
     * When providing a feedback on a bank's office work, we put a received estimation mark and put
     * it to a field of this class.
     *
     * @param estimationMark
     */
    @Override
    public void onEstimatingBank(String estimationMark) {
        this.mEstimationMark = estimationMark;
    }

    /**
     * This method fires, when user clicks "back" on a previous activity.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int count = mFragmentManager.getBackStackEntryCount();
        if (count == 0) {
            finish();
        }

        if (mEstimationMark != null) {
            Toast.makeText(getApplicationContext(),
                    Consts.TOAST_AFTER_ESTIMATION_MESSAGE + mEstimationMark,
                    Toast.LENGTH_SHORT).show();
            mEstimationMark = null;
        }

    }

}