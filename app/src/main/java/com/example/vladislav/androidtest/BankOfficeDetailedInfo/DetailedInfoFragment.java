package com.example.vladislav.androidtest.BankOfficeDetailedInfo;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
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

import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.beans.BankDetails;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailedInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailedInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailedInfoFragment extends Fragment {

    private CharSequence[] mEstimationGroup = {"1", "2", "3", "4", "5"};
    private OnFragmentInteractionListener mListener;
    private BankDetails mBankDetails;
    private String mEstimationMark;
    private TextView mTextView;
//    private Layout mDistanceLayout;

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

/*
    public static DetailedInfoFragment newInstance(Parcelable parcelable) {
        Bundle args = new Bundle();
        args.putParcelable("bankOffice",parcelable);
        DetailedInfoFragment fragment = new DetailedInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }
*/
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBankDetails = getArguments().getParcelable("bankOffice");
        }

    }

    // Remove this method, if further operating is doing fine there.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.detailed_info_fragment, container, false);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextView = (TextView) view.findViewById(R.id.address_text_view);
        mTextView.setText(mBankDetails.getAddress());

        mTextView = (TextView) view.findViewById(R.id.distance_text_view);
        mTextView.setText(mBankDetails.getDistance());

        mTextView = (TextView) view.findViewById(R.id.extra_office_text_view);
        mTextView.setText(mBankDetails.getName());

        mTextView = (TextView) view.findViewById(R.id.telephoneN_text_view);
        mTextView.setText(mBankDetails.getPhoneNumber());


        mTextView = (TextView) view.findViewById(R.id.monday_hours_text_view);
        mTextView.setText(mBankDetails.getWorkingHours());
        mTextView = (TextView) view.findViewById(R.id.tuesday_hours_text_view);
        mTextView.setText(mBankDetails.getWorkingHours());
        mTextView = (TextView) view.findViewById(R.id.wednesday_hours_text_view);
        mTextView.setText(mBankDetails.getWorkingHours());
        mTextView = (TextView) view.findViewById(R.id.thursday_hours_text_view);
        mTextView.setText(mBankDetails.getWorkingHours());
        mTextView = (TextView) view.findViewById(R.id.friday_hours_text_view);
        mTextView.setText(mBankDetails.getWorkingHours());

        Button button = (Button) view.findViewById(R.id.qualityEstimation_button);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        view.getContext());

                // set title
                alertDialogBuilder.setTitle("Пожалуйста оцените качество работы нашего филиала.");

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
                        .setSingleChoiceItems(mEstimationGroup, -1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                dialog.dismiss();// dismiss the alertbox after chose option
                                mEstimationMark = (String) mEstimationGroup[item];
//                                intent.putExtra("mEstimationMark",mEstimationMark);
//                                setResult(RESULT_OK, intent);
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
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"));
//                startActivity(intent);
                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            1
                    );
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse(
//                            "tel:12345678901"
                            "tel:" +
                                    (String) ((TextView) view.findViewById(R.id.telephoneN_text_view)).getText())));
                }
            }
        });

        RelativeLayout mDistanceLayout = (RelativeLayout) view.findViewById(R.id.directionDistance);
        // Clicking on a distance layout and launching a google maps app.
        mDistanceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");

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
