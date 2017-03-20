package com.example.vladislav.androidtest.BanksOfficesList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import com.example.vladislav.androidtest.R;
import com.example.vladislav.androidtest.Consts;
import com.example.vladislav.androidtest.beans.BankDetails;
import com.example.vladislav.androidtest.database.DBHelper;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class BankOfficeListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private BankOfficeCallbacks mListener;

    private View mRootView;
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private List<BankDetails> mList;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    // Required empty public constructor
    public BankOfficeListFragment() { }

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
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new BankOfficesLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mList = new ArrayList<>(data.getCount());
        data.moveToFirst();
        int i = 0;
        while (i != data.getCount()) {
            mList.add(DBHelper.cursorToBankDetails(data));
            data.moveToNext();
            i++;
        }

        mAdapter.update(mList);
        mProgressBar.setVisibility(GONE);
        mRecyclerView.setVisibility(VISIBLE);
        mTextView.setVisibility(GONE);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.update(new ArrayList());
    }

    public interface BankOfficeCallbacks {
        void onBankOfficeSelected(BankDetails bankOffice);
    }

}
