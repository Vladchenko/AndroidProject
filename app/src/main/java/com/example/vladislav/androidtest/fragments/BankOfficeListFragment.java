package com.example.vladislav.androidtest.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vladislav.androidtest.BanksDetailsOperating;
import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.RecyclerViewAdapter;
import com.example.vladislav.androidtest.activities.DetailedInfoActivity;
import com.example.vladislav.androidtest.activities.FragmentsActivity;
import com.example.vladislav.androidtest.entities.BankDetails;
import com.example.vladislav.androidtest.listeners.RecyclerItemClickListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BankOfficeListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BankOfficeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BankOfficeListFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private List<BankDetails> list;
    BanksDetailsOperating banksDetailsOperating = new BanksDetailsOperating();

    public BankOfficeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_bank_office_list, container, false);

        list = banksDetailsOperating.getBanksDetails();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(rootView.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
//                         Invoke an activity with detailed info of a clicked bank
                        Intent intent = new Intent(rootView.getContext(),DetailedInfoActivity.class);
                        intent.putExtra(DetailedInfoActivity.EXTRA_BANK, list.get(position));
                        startActivity(intent);
//                         Getting a result for
                        startActivityForResult(intent, 1);  // 1 is some request code.
                    }
                })
        );

        adapter = new RecyclerViewAdapter(list);
        initRecyclerView(rootView.getContext());

        // Inflate the layout for this fragment
        return rootView;
    }

    void initRecyclerView(Context context){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(adapter);
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
        void onFragmentInteraction(Uri uri);
    }
}
