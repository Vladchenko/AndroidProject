package com.example.vladislav.androidtest.BankOfficeDetailedInfo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vladislav.androidtest.BanksOfficesList.BankOfficeListActivity;
import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.beans.BankDetails;
import com.example.vladislav.androidtest.database.DBBanksContract;
import com.example.vladislav.androidtest.database.DBHelper;
import com.example.vladislav.androidtest.datasource.Consts;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailedInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailedInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailedInfoFragment extends Fragment {

//    public static final boolean GET_DATA_FROM_DB = true;

    private CharSequence[] mEstimationGroup = {"1", "2", "3", "4", "5"};
    private OnFragmentInteractionListener mListener;
    private BankDetails mBankDetails;
    private String mEstimationMark;
    private TextView mTextView;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private DBHelper mDbHelper;
    private Cursor mCursor = null;

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onEstimatingBank(String estimationMark);
    }

    public DetailedInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mBankDetails = getArguments().getParcelable(Consts.BANK_DETAILS_PARCELABLE);
        }

        mSharedPreferences = getContext().getSharedPreferences(
                Consts.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);

    }

    // Remove this method, if further operating is doing fine there.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.detailed_info_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextView = (TextView) view.findViewById(R.id.address_text_view);
        mTextView.setText(mBankDetails.getmAddress());

        mTextView = (TextView) view.findViewById(R.id.distance_text_view);
        mTextView.setText(mBankDetails.getmDistance());

        mTextView = (TextView) view.findViewById(R.id.extra_office_text_view);
        mTextView.setText(mBankDetails.getmName());

        mTextView = (TextView) view.findViewById(R.id.telephoneN_text_view);
        mTextView.setText(mBankDetails.getmPhoneNumber());

        mTextView = (TextView) view.findViewById(R.id.monday_hours_text_view);
        mTextView.setText(mBankDetails.getmWorkingHours());
        mTextView = (TextView) view.findViewById(R.id.tuesday_hours_text_view);
        mTextView.setText(mBankDetails.getmWorkingHours());
        mTextView = (TextView) view.findViewById(R.id.wednesday_hours_text_view);
        mTextView.setText(mBankDetails.getmWorkingHours());
        mTextView = (TextView) view.findViewById(R.id.thursday_hours_text_view);
        mTextView.setText(mBankDetails.getmWorkingHours());
        mTextView = (TextView) view.findViewById(R.id.friday_hours_text_view);
        mTextView.setText(mBankDetails.getmWorkingHours());

        Button button = (Button) view.findViewById(R.id.qualityEstimation_button);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        view.getContext());

                // set title
                alertDialogBuilder.setTitle(Consts.BANK_DEPARTMENT_ESTIMATION_MESSAGE);

                // set dialog message
                alertDialogBuilder
//                        .setMessage("Click yes to exit!")
                        .setCancelable(true)
                        .setSingleChoiceItems(mEstimationGroup, -1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                dialog.dismiss();// dismiss the alertbox after chose option
                                mEstimationMark = (String) mEstimationGroup[item];
                                mEditor = mSharedPreferences.edit();
                                mEditor.putInt(Consts.ESTIMATION_MARK + mBankDetails.getmID(), Integer.parseInt(mEstimationMark));
                                System.out.println(Consts.ESTIMATION_MARK+mBankDetails.getmID() + ":" + mEstimationMark);
                                mEditor.commit();
                                mListener.onEstimatingBank(mEstimationMark);
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

        ImageView mImageViewPhoneSet = (ImageView) view.findViewById(R.id.phoneSet_image_view);
        // Clicking on a phoneset and making a call
        mImageViewPhoneSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            1
                    );
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse(
                            "tel:"
                                    + (String) ((TextView) view.findViewById(R.id.telephoneN_text_view)).getText())));
                }
            }
        });

        RelativeLayout mDistanceLayout = (RelativeLayout) view.findViewById(R.id.directionDistance);
        // Clicking on a distance layout and launching a google maps app.
        mDistanceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse(Consts.GOOGLE_MAPS_URI);

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage(Consts.GOOGLE_MAPS_PACKAGE);

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement BankOfficeCallbacks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
