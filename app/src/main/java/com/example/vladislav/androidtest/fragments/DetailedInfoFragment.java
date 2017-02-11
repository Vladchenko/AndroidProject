package com.example.vladislav.androidtest.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.entities.BankDetails;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailedInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailedInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailedInfoFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private BankDetails bankOffice;
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

        try {
//            BankDetails bankOffice = savedInstanceState.getParcelable("bankOffice");

            textView = (TextView) getView().findViewById(R.id.address_text_view);
            textView.setText(bankOffice.getAddress());

            textView = (TextView) getView().findViewById(R.id.distance_text_view);
            textView.setText(bankOffice.getDistance());

            textView = (TextView) getView().findViewById(R.id.extra_office_text_view);
            textView.setText(bankOffice.getAddress());

            textView = (TextView) getView().findViewById(R.id.telephoneN_text_view);
            textView.setText(bankOffice.getPhoneNumber());

        } catch (NullPointerException npe) {
            Log.e("DetailedInfoFragment", "Some NPE occured!");
        }

        return inflater.inflate(R.layout.fragment_detailed_info, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement BankOfficeCallbacks");
//        }
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
        void onFragmentInteraction(Uri uri);
    }
}
