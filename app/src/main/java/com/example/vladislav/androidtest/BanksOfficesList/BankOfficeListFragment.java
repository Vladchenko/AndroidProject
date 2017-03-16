package com.example.vladislav.androidtest.BanksOfficesList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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

//import com.example.vladislav.androidtest.datasource.DownloadingTask;
import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.datasource.Consts;
import com.example.vladislav.androidtest.datasource.RecyclerViewAdapter;
import com.example.vladislav.androidtest.beans.BankDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class BankOfficeListFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<BankDetails>> {

    private BankOfficeCallbacks mListener;

    private View mRootView;
//    private View mRootView2;
    private TextView mTextView;
//    private TextView mTextView2;
//    private DownloadingTask task;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private List<BankDetails> mList = Collections.emptyList();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

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

        mRootView = inflater.inflate(R.layout.bank_office_list_fragment, container, false);
        mProgressBar = (ProgressBar) mRootView.findViewById(R.id.progressBar);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(mRootView.getContext(),
                        new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        mListener.onBankOfficeSelected(mList.get(position));
                        mEditor = mSharedPreferences.edit();
                        mEditor.putInt(Consts.BANK_LIST_INDEX, position);
                        mEditor.commit();
                    }
                })
        );
        mTextView = (TextView) mRootView.findViewById(R.id.textView);

        mAdapter = new RecyclerViewAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        mSharedPreferences = getContext().getSharedPreferences(
                Consts.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);

        // Inflate the layout for this fragment
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setVisibility(GONE);
        mProgressBar.setVisibility(VISIBLE);
        mTextView.setVisibility(VISIBLE);
//        task = new DownloadingTask(new DownloadingTask.BanksDataSourceCallbacks() {
//            @Override
//            public void onDownloadComplete(List<BankDetails> mList) {
//                mAdapter.update(mList);
//                BankOfficeListFragment.this.mList = mList;
//                mProgressBar.setVisibility(GONE);
//                mRecyclerView.setVisibility(VISIBLE);
//                mTextView.setVisibility(GONE);
//            }
//        });
//        task.execute();

        // Loading the data by force. In this case, loading is done every time an activity created.
        // But we need it to load only once.
//        getLoaderManager().initLoader(0, null, this).forceLoad();

            getLoaderManager().initLoader(0, null, this);
//        }
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
        mAdapter.update(data);
        mList = data;
        mProgressBar.setVisibility(GONE);
        mRecyclerView.setVisibility(VISIBLE);
        mTextView.setVisibility(GONE);

//        int defaultValue = -1;
//        int highScore = mSharedPreferences.getInt(Consts.ESTIMATION_MARK, defaultValue);
//        System.out.println("estimation_mark is: " + highScore);
//        int bankPosition = mSharedPreferences.getInt(Consts.BNK_LIST_INDEX, defaultValue);
//        System.out.println("bank_position is: " + bankPosition);
//        if (highScore > -1) {
//            ((TextView) findViewById())
//                    .setText("123");
//        }

//        System.out.println(mList.get(10/* position of a clicked bank */).getmEstimationMark());
//        mList.get().setmEstimationMark();

    }

    @Override
    public void onLoaderReset(Loader<List<BankDetails>> loader) {
        mAdapter.update(new ArrayList()); // mList seems perform good here, why not use it ?
    }

    public interface BankOfficeCallbacks {
        void onBankOfficeSelected(BankDetails bankOffice);
    }

}
