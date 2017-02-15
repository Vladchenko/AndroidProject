package com.example.vladislav.androidtest.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
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

import java.util.Collections;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BankOfficeCallbacks} interface
 * to handle interaction events.
 * Use the {@link BankOfficeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BankOfficeListFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<BankDetails>> {

    private BankOfficeCallbacks mListener;

    private View rootView;
    private TextView textView;
    private DownloadingTask task;
    private ProgressBar progressBar;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private LoaderManager loaderManager;
    private BankOfficesLoader.BanksDataSourceCallbacks bankOfficeCallbacks;
    private List<BankDetails> list = Collections.emptyList();
//    BanksDetailsOperating banksDetailsOperating = new BanksDetailsOperating();

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

//        Log.d("Fragment created", "Fragment created");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setVisibility(INVISIBLE);
        progressBar.setVisibility(VISIBLE);
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
        Bundle bundle = new Bundle();
        BankOfficesLoader bankDetailsLoader = new BankOfficesLoader(new BankOfficesLoader.BanksDataSourceCallbacks() {
            @Override
            public void onDownloadComplete(List<BankDetails> list) {
                adapter.update(list);
                BankOfficeListFragment.this.list = list;
                progressBar.setVisibility(GONE);
                mRecyclerView.setVisibility(VISIBLE);
                textView.setVisibility(GONE);
            }
        }, this.getContext(), bundle);
        loaderManager = this.getLoaderManager();
//
        loaderManager.initLoader(0, null, this);
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

        adapter = new RecyclerViewAdapter(list);
        initRecyclerView(rootView.getContext());

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        task.cancel(true);
    }

    void initRecyclerView(Context context) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public Loader<List<BankDetails>> onCreateLoader(int id, Bundle args) {
        return new BankOfficesLoader(bankOfficeCallbacks, this.getContext(), args);
    }

    @Override
    public void onLoadFinished(Loader<List<BankDetails>> loader, List<BankDetails> data) {
        adapter.update(data);
        BankOfficeListFragment.this.list = data;
    }

    @Override
    public void onLoaderReset(Loader<List<BankDetails>> loader) {

    }



    public interface BankOfficeCallbacks {
        void onBankOfficeSelected(BankDetails bankOffice);
    }

}
