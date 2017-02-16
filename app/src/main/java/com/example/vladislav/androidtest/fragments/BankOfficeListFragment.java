package com.example.vladislav.androidtest.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vladislav.androidtest.BankOfficesLoader;
import com.example.vladislav.androidtest.DownloadingTask;
import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.RecyclerViewAdapter;
import com.example.vladislav.androidtest.entities.BankDetails;
import com.example.vladislav.androidtest.listeners.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class BankOfficeListFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<BankDetails>> {

    private BankOfficeCallbacks mListener;

    private View rootView;
    private TextView textView;
    private DownloadingTask task;
    private ProgressBar progressBar;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private List<BankDetails> list = Collections.emptyList();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.bank_office_list_fragment, container, false);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        textView = (TextView) rootView.findViewById(R.id.textView);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(rootView.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        mListener.onBankOfficeSelected(list.get(position));
                    }
                })
        );

        adapter = new RecyclerViewAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mRecyclerView.setVisibility(INVISIBLE);
//        progressBar.setVisibility(VISIBLE);
//        task = new DownloadingTask(new DownloadingTask.BanksDataSourceCallbacks() {
//            @Override
//            public void onDownloadComplete(List<BankDetails> list) {
//                adapter.update(list);
//                BankOfficeListFragment.this.list = list;
//                progressBar.setVisibility(GONE);
//                mRecyclerView.setVisibility(VISIBLE);
//                textView.setVisibility(GONE);
//            }
//        });
//        task.execute();
        getLoaderManager().initLoader(0, null, this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        task.cancel(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Loader<List<BankDetails>> onCreateLoader(int id, Bundle args) {
        return new BankOfficesLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<List<BankDetails>> loader, List<BankDetails> data) {
        adapter.update(data);
//        progressBar.setVisibility(GONE);
//        mRecyclerView.setVisibility(VISIBLE);
//        textView.setVisibility(GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<BankDetails>> loader) {
        adapter.update(new ArrayList());
    }

    public interface BankOfficeCallbacks {
        void onBankOfficeSelected(BankDetails bankOffice);
    }

}
