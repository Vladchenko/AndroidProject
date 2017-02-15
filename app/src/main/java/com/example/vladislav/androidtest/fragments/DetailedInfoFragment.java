package com.example.vladislav.androidtest.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.entities.BankDetails;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailedInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailedInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailedInfoFragment extends Fragment {

    private CharSequence[] estimationGroup = {"1", "2", "3", "4", "5"};
    private OnFragmentInteractionListener mListener;
    private BankDetails bankOffice;
    private String estimationMark;
    private TextView textView;

    public DetailedInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            bankOffice = (BankDetails) getArguments().getParcelable("bankOffice");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.detailed_info_fragment, container, false);

        textView = (TextView) view.findViewById(R.id.address_text_view);
        textView.setText(bankOffice.getAddress());

        textView = (TextView) view.findViewById(R.id.distance_text_view);
        textView.setText(bankOffice.getDistance());

        textView = (TextView) view.findViewById(R.id.extra_office_text_view);
        textView.setText(bankOffice.getAddress());

        textView = (TextView) view.findViewById(R.id.telephoneN_text_view);
        textView.setText(bankOffice.getPhoneNumber());

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
                        .setSingleChoiceItems(estimationGroup, -1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                dialog.dismiss();// dismiss the alertbox after chose option
                                estimationMark = (String)estimationGroup[item];
//                                intent.putExtra("estimationMark",estimationMark);
//                                setResult(RESULT_OK, intent);
                                mListener.onEstimatingBank(estimationMark);
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

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

}
