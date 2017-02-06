package com.example.vladislav.androidtest;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vladislav.androidtest.entities.BankDetails;

import java.util.List;

/**
 * Created by vladislav on 06.02.17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder2>{

    private List<BankDetails> list;

    public RecyclerViewAdapter(List<BankDetails> list) {
        this.list = list;
    }

    @Override
    public ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        ViewHolder2 viewHolder2 = new ViewHolder2(v);
        return viewHolder2;
    }

    @Override
    public void onBindViewHolder(ViewHolder2 holder, int position) {
        BankDetails bankDetails = list.get(position);
        holder.addressTextView.setText(bankDetails.getAddress());
        holder.distanceTextView.setText(bankDetails.getDistance());
        holder.extraOfficeTextView.setText(bankDetails.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {

        TextView addressTextView;
        TextView extraOfficeTextView;
        TextView distanceTextView;
        TextView telephoneNTextView;

        public ViewHolder2(View itemView) {
            super(itemView);
            addressTextView = (TextView)itemView.findViewById(R.id.address_text_view);
            extraOfficeTextView = (TextView)itemView.findViewById(R.id.extra_office_text_view);
            distanceTextView = (TextView)itemView.findViewById(R.id.distance_text_view);
            telephoneNTextView = (TextView)itemView.findViewById(R.id.telephoneN_text_view);
            // No need to pass an image, it's already present in an activity.
        }


    }

}