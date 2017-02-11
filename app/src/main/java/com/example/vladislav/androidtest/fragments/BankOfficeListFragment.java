package com.example.vladislav.androidtest.fragments;

import android.app.Activity;
import android.content.Context;
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
import com.example.vladislav.androidtest.entities.BankDetails;
import com.example.vladislav.androidtest.listeners.RecyclerItemClickListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BankOfficeCallbacks} interface
 * to handle interaction events.
 * Use the {@link BankOfficeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BankOfficeListFragment extends Fragment {

    private BankOfficeCallbacks mListener;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private List<BankDetails> list;
    BanksDetailsOperating banksDetailsOperating = new BanksDetailsOperating();

    public BankOfficeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (BankOfficeCallbacks) activity;
        } catch (Exception ex) {
            throw new ClassCastException("Activity should implement BankOfficeCallbacks");
        }
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
                    @Override
                    public void onItemClick(View view, int position) {
                        mListener.onBankOfficeSelected(list.get(position));
                    }
                })
        );

        adapter = new RecyclerViewAdapter(list);
        initRecyclerView(rootView.getContext());

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void initRecyclerView(Context context) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(adapter);
    }

    public interface BankOfficeCallbacks {
        void onBankOfficeSelected(BankDetails bankOffice);
    }
}
